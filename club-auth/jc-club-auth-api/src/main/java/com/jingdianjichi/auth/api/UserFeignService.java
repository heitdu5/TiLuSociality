package com.jingdianjichi.auth.api;

import com.jingdianjichi.auth.entity.AuthUserDTO;
import com.jingdianjichi.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户服务feign
 */
@FeignClient("yh-club-auth")
public interface UserFeignService {

    @RequestMapping("/user/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);

}
