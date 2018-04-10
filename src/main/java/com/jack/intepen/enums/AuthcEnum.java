package com.jack.intepen.enums;

/**
 * Created by 11407 on 5/005.
 */
public enum AuthcEnum {
    SUCCESS(0, "成功"),
    ERROR(1, "未知错误"),
    UNKNOWN_ACCOUNT(101, "账号未注册！"),
    INCORRECT_PASSWORD(102, "密码错误！"),
    AUTHENTICATION_ERROR(103, "账号认证错误！"),
    TOKEN_EXISTS(104, "请勿重复登录！"),
    ACCOUNT_EXISTS(105, "账号已注册！"),
    ACCOUNT_UNLOGIN(106, "账号未登录！"),
    UNAUTHORIZING(107, "没有授权！"),
    PARAM_ERROR(108, "参数错误！")
    ;

    private int code;
    private String error;

    AuthcEnum(int code, String error){
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }
}
