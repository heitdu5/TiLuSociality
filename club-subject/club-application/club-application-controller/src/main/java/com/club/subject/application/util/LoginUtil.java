package com.club.subject.application.util;


import com.club.subject.common.context.LoginContextHolder;

public class LoginUtil {

    public static String getLoginId(){
        return LoginContextHolder.getLoginId();
    }
}
