package com.club.subject.infra.basic.service;

import com.club.subject.infra.basic.entity.SubjectLabel;


import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-05-24 16:57:03
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<SubjectLabel> batchQueryById(List<Long> labelIdList);

    List<SubjectLabel> queryByCondition(SubjectLabel subjectLabel);



}
