package com.club.subject.infra.basic.service;

import com.club.subject.common.entity.PageResult;
import com.club.subject.infra.basic.entity.SubjectInfoEs;

/**
 * @author Tellsea
 * @date 2024−08−02
 */
public interface SubjectEsService {



    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);

}