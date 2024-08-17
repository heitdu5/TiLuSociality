package com.club.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.club.subject.common.entity.PageResult;
import com.club.subject.common.enums.IsDeletedFlagEnum;
import com.club.subject.common.enums.SubjectLikedStatusTypeEnum;
import com.club.subject.common.util.LoginUtil;
import com.club.subject.domain.convert.SubjectLikedBOConverter;
import com.club.subject.domain.entity.SubjectLikedBO;
import com.club.subject.domain.entity.SubjectLikedMessage;
import com.club.subject.domain.redis.RedisUtil;
import com.club.subject.domain.service.SubjectLikedDomainService;
import com.club.subject.infra.basic.entity.SubjectInfo;
import com.club.subject.infra.basic.entity.SubjectLiked;
import com.club.subject.infra.basic.service.SubjectInfoService;
import com.club.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 题目点赞表 领域service实现了
 *
 * @author yh
 * @since 2024-08-05 10:48:52
 */
@Service
@Slf4j
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    @Resource
    private SubjectLikedService subjectLikedService;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private RedisUtil redisUtil;

    private static final String  SUBJECT_LIKED_KEY= "subject.liked";

    private static final String  SUBJECT_LIKED_COUNT_KEY= "subject.liked.count";

    private static final String  SUBJECT_LIKED_DETAIL_KEY= "subject.liked.detail";

    @Override
    public void add(SubjectLikedBO subjectLikedBO) {
        Integer status = subjectLikedBO.getStatus();
        Long subjectId = subjectLikedBO.getSubjectId();
        String likeUserId = subjectLikedBO.getLikeUserId();
//        String hashKey = buildSubjectLikedKey(subjectId,likeUserId);
//        redisUtil.putHash(SUBJECT_LIKED_KEY,hashKey,status);//存的点赞状态，如果是0说明他曾经也点过。这里不用来查询，而是同步数据库

        SubjectLikedMessage subjectLikedMessage = new SubjectLikedMessage();
        subjectLikedMessage.setSubjectId(subjectId);
        subjectLikedMessage.setStatus(status);
        subjectLikedMessage.setLikeUserId(likeUserId);
        //TODO:发送消息
        rocketMQTemplate.convertAndSend("subject-liked",JSON.toJSONString(subjectLikedMessage));

        String detailKey = SUBJECT_LIKED_DETAIL_KEY + "." + subjectId + "."  + likeUserId;//通过这个key判断这个人点了该题的赞
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;//点赞数量
        if (SubjectLikedStatusTypeEnum.LIKED.getCode() == status){
            redisUtil.increment(countKey,1);
            redisUtil.set(detailKey,"1");
        }else{
            Integer count = redisUtil.getInt(countKey);
            if (Objects.isNull(count) || count == 0){
                return ;
            }
            redisUtil.increment(countKey,-1);
            redisUtil.del(detailKey);
        }
    }

    private String buildSubjectLikedKey(String subjectId,String userId){
        return subjectId +  ":"  + userId;
    }

    @Override
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        return subjectLikedService.update(subjectLiked) > 0;
    }

    @Override
    public Boolean delete(SubjectLikedBO subjectLikedBO) {
        SubjectLiked subjectLiked = new SubjectLiked();
        subjectLiked.setId(subjectLikedBO.getId());
        subjectLiked.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        return subjectLikedService.update(subjectLiked) > 0;
    }

    @Override
    public Boolean isLiked(String subjectId, String userId) {
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + "." + subjectId + "."  + userId;//通过这个key判断这个人点了该题的赞
        return redisUtil.exist(detailKey);
    }

    @Override
    public Integer getLikedCount(String subjectId) {
        String countKey = SUBJECT_LIKED_COUNT_KEY + "." + subjectId;//点赞数量
        Integer count = redisUtil.getInt(countKey);
        if (Objects.isNull(count) || count<= 0){
                return 0;
        }
        return count;
    }

    @Override
    public void syncLiked() {
        Map<Object, Object> subjectLikedMap = redisUtil.getHashAndDelete(SUBJECT_LIKED_KEY);
        if (log.isInfoEnabled()) {
            log.info("syncLiked.subjectLikedMap:{}", JSON.toJSONString(subjectLikedMap));
        }
        if (MapUtils.isEmpty(subjectLikedMap)) {
            return;
        }
        //批量同步数据库
        List<SubjectLiked> subjectLikedList = new LinkedList<>();
        subjectLikedMap.forEach((key, val) -> {
            SubjectLiked subjectLiked = new SubjectLiked();
            String[] keyArr = key.toString().split(":");
            String subjectId = keyArr[0];
            String likedUser = keyArr[1];
            subjectLiked.setSubjectId(Long.valueOf(subjectId));
            subjectLiked.setLikeUserId(likedUser);
            subjectLiked.setStatus(Integer.valueOf(val.toString()));
            subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectLikedList.add(subjectLiked);
        });
        subjectLikedService.batchInsertOrUpdate(subjectLikedList);
    }

    @Override
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        PageResult<SubjectLikedBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectLikedBO.getPageNo());
        pageResult.setPageSize(subjectLikedBO.getPageSize());
        int start = (subjectLikedBO.getPageNo() - 1) * subjectLikedBO.getPageSize();
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        subjectLiked.setLikeUserId(LoginUtil.getLoginId());
        int count = subjectLikedService.countByCondition(subjectLiked);
        if (count == 0) {
            return pageResult;
        }
        List<SubjectLiked> subjectLikedList = subjectLikedService.queryPage(subjectLiked, start,
                subjectLikedBO.getPageSize());
        List<SubjectLikedBO> subjectInfoBOS = SubjectLikedBOConverter.INSTANCE.convertListInfoToBO(subjectLikedList);
        subjectInfoBOS.forEach(info -> {
            SubjectInfo subjectInfo = subjectInfoService.queryById(info.getSubjectId());
            info.setSubjectName(subjectInfo.getSubjectName());
        });
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public void syncLikedByMsg(SubjectLikedBO subjectLikedBO) {
        List<SubjectLiked> subjectLikedList = new LinkedList<>();
            SubjectLiked subjectLiked = new SubjectLiked();
            subjectLiked.setSubjectId(subjectLikedBO.getSubjectId());
            subjectLiked.setLikeUserId(subjectLikedBO.getLikeUserId());
            subjectLiked.setStatus(subjectLikedBO.getStatus());
            subjectLiked.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectLikedList.add(subjectLiked);
            subjectLikedService.batchInsertOrUpdate(subjectLikedList);
    }

}
