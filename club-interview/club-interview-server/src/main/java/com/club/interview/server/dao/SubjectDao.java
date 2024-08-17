package com.club.interview.server.dao;

import com.club.interview.server.entity.po.SubjectCategory;
import com.club.interview.server.entity.po.SubjectInfo;
import com.club.interview.server.entity.po.SubjectLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SubjectDao {

    List<SubjectLabel> listAllLabel();

    List<SubjectCategory> listAllCategory();

    List<SubjectInfo> listSubjectByLabelIds(@Param("ids") List<Long> ids);
}

