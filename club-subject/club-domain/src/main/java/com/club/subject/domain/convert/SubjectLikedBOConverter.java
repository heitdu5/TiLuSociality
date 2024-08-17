package com.club.subject.domain.convert;

import com.club.subject.domain.entity.SubjectLikedBO;
import com.club.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 题目点赞表 bo转换器
 *
 * @author yh
 * @since 2024-08-05 10:48:52
 */
@Mapper
public interface SubjectLikedBOConverter {

    SubjectLikedBOConverter INSTANCE = Mappers.getMapper(SubjectLikedBOConverter.class);

    SubjectLiked convertBOToEntity(SubjectLikedBO subjectLikedBO);

    List<SubjectLikedBO> convertListInfoToBO(List<SubjectLiked> subjectLikedList);
}
