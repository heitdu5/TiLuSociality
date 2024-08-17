package com.club.subject.domain.entity;

import com.club.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目点赞消息(SubjectLikedMessage)实体类
 *
 * @author yh
 * @since 2024-08-05 10:48:52
 */
@Data
public class SubjectLikedMessage implements Serializable {


    /**
     * 题目id
     */
    private Long subjectId;


    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;


}

