package com.club.subject.application.convert;

import com.club.subject.application.dto.SubjectAnswerDTO;
import com.club.subject.domain.entity.SubjectAnswerBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T23:38:11+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public SubjectAnswerBO convertDTOToBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }

    @Override
    public List<SubjectAnswerBO> convertListDTOToBO(List<SubjectAnswerDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( dtoList.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : dtoList ) {
            list.add( convertDTOToBO( subjectAnswerDTO ) );
        }

        return list;
    }
}