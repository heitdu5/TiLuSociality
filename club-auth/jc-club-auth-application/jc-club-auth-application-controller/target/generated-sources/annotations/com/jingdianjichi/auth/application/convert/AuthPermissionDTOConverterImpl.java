package com.jingdianjichi.auth.application.convert;

import com.jingdianjichi.auth.application.dto.AuthPermissionDTO;
import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-27T21:34:31+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_391 (Oracle Corporation)"
)
public class AuthPermissionDTOConverterImpl implements AuthPermissionDTOConverter {

    @Override
    public AuthPermissionBO convertDTOToBo(AuthPermissionDTO authPermissionDTO) {
        if ( authPermissionDTO == null ) {
            return null;
        }

        AuthPermissionBO authPermissionBO = new AuthPermissionBO();

        return authPermissionBO;
    }
}
