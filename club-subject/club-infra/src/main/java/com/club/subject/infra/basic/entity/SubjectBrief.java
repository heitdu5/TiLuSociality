package com.club.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 简答题(SubjectBrief)实体类
 *
 * @author makejava
 * @since 2024-05-25 17:54:16
 */
@Data
public class SubjectBrief implements Serializable {
    private static final long serialVersionUID = 188917014239403718L;
/**
     * 主键
     */
    private Long id;
/**
     * 题目id
     */
    private Integer subjectId;
/**
     * 题目答案
     */
    private String subjectAnswer;
/**
     * 创建人
     */
    private String createdBy;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 更新人
     */
    private String updateBy;
/**
     * 更新时间
     */
    private Date updateTime;
/**
     * 是否删除 0: 未删除 1: 已删除
     */
    private Integer isDeleted;

}

