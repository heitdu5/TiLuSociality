package com.club.subject.infra.rpc;

import com.club.subject.infra.entity.UserInfo;
import com.jingdianjichi.auth.api.UserFeignService;
import com.jingdianjichi.auth.entity.AuthUserDTO;
import com.jingdianjichi.auth.entity.Result;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRpc {
    @Resource
    private UserFeignService userFeignService;

    public UserInfo userInfo(String username){
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(username);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()){
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setNickName(data.getNickName());
        userInfo.setUsername(data.getUserName());
        userInfo.setAvatar(data.getAvatar());
        return userInfo;
    }
}
