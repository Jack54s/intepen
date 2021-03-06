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
    PARAM_ERROR(108, "参数错误！"),
    LIST_ERROR(109, "列出信息错误！"),
    EDIT_ERROR(110, "编辑信息错误！"),
    ADD_ERROR(111, "增加信息错误！"),
    REMOVE_ERROR(112, "删除信息错误！"),
    RESET_PASSWORD_ERROR(113, "更改密码错误！"),
    CAPTCHA_ERROR(114, "验证码错误！"),
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
