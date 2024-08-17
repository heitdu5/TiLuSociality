package com.club.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 题目标签表(SubjectLabel)实体类
 *
 * @author makejava
 * @since 2024-05-24 16:57:03
 */
@Data
public class SubjectLabel implements Serializable {
    private static final long serialVersionUID = -93469792850357995L;
/**
     * 主键
     */
    private Long id;
/**
     * 标签分类
     */
    private String labelName;
/**
     * 分类id
     */
    private Long categoryId;
/**
     * 排序
     */
    private Integer sortNum;
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

    private Integer isDeleted;



}

