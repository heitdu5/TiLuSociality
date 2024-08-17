package com.club.subject.domain.service;


import com.club.subject.common.entity.PageResult;
import com.club.subject.domain.entity.SubjectLikedBO;

/**
 * 题目点赞表 领域service
 *
 * @author yh
 * @since 2024-08-05 10:48:52
 */
public interface SubjectLikedDomainService {

    /**
     * 添加 题目点赞表 信息
     */
    void add(SubjectLikedBO subjectLikedBO);

    /**
     * 更新 题目点赞表 信息
     */
    Boolean update(SubjectLikedBO subjectLikedBO);

    /**
     * 删除 题目点赞表 信息
     */
    Boolean delete(SubjectLikedBO subjectLikedBO);


    /**
     * 获取当前是否被点赞过
     * @param subjectId
     * @param userId
     * @return
     */
    Boolean isLiked(String subjectId, String userId);

    /**
     * 获取点赞数量
     * @param subjectId
     * @return
     */
    Integer getLikedCount(String subjectId);

    /**
     * 同步点赞数据
     */
    void syncLiked();

    PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO);

    void syncLikedByMsg(SubjectLikedBO subjectLikedBO);
}
