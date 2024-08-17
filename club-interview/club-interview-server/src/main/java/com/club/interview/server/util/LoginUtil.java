package com.club.interview.server.util;


import com.club.interview.server.config.context.LoginContextHolder;

/**
 * 用户登录util
 *
 * @author: ChickenWing
 * @date: 2023/11/26
 */
public class LoginUtil {

    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }


}
