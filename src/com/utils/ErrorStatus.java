package com.utils;

/**
 * 接口错误码
 * Created by pxc on 15/8/19.
 */
public enum ErrorStatus {

    ERROR("交互错误"),
    USER_NOT_EXISTS("用户名或密码错误"),
    INVALID_TOKEN("Token不合法"),
    INVALID_USER_TOKEN("Token不合法"),
    INVALID_SIGN("签名不正确"),
    INVALID_TICKET("票据不正确"),
    INVALID_PURVIEW("权限不合法");

    ErrorStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
