package com.jingdianjichi.auth.application.convert;


import com.jingdianjichi.auth.application.dto.AuthPermissionDTO;
import com.jingdianjichi.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限dto转换器
 *
 */
@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO convertDTOToBo(AuthPermissionDTO authPermissionDTO);



}
