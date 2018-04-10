package com.jack.intepen.enums;

/**
 * Created by 11407 on 5/005.
 */
public enum FamilyEnum {
    UNKNOWN_ACCOUNT(300, "未找到此账号"),
    LIST_FAMILY_ERROR(301, "获取家属列表信息失败")
    ;

    private int code;
    private String error;

    FamilyEnum(int code, String error){
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
