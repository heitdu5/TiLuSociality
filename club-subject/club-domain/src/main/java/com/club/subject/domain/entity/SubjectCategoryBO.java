package com.club.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目分类(SubjectCategory)实体类
 */
@Data
public class SubjectCategoryBO implements Serializable {
    private static final long serialVersionUID = 395210339829803670L;
/**
     * 主键
     */
    private Long id;
/**
     * 分类名称
     */
    private String categoryName;
/**
     * 分类类型
     */
    private Integer categoryType;
/**
     * 图标连接
     */
    private String imageUrl;
/**
    * 父级id
    */
    private Long parentId;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 标签bo数量
     */
    private List<SubjectLabelBO> labelBOList;


}

