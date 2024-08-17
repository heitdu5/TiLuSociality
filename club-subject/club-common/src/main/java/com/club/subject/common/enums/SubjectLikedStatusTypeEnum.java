package com.club.subject.common.enums;

import lombok.Getter;

/**
 * 题目点赞枚举
 * 
 * @author: ChickenWing
 * @date: 2023/10/3
 */
@Getter
public enum SubjectLikedStatusTypeEnum {

    LIKED(1,"点赞"),
    UN_LIKED(0,"取消点赞");

    public int code;

    public String desc;

    SubjectLikedStatusTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static SubjectLikedStatusTypeEnum getByCode(int codeVal){
        for(SubjectLikedStatusTypeEnum resultCodeEnum : SubjectLikedStatusTypeEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }

}
