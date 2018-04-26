package com.jack.intepen.enums;

/**
 * Created by 11407 on 26/026.
 */
public enum RolesEnum {
    LIST_ROLES_ERROR(700, "列出角色错误！"),
    SET_ROLES_ERROR(701, "设置用户角色错误！"),
    REMOVE_ROLES_ERROR(702, "移除用户所有角色错误！"),
    ;

    private int code;
    private String error;

    RolesEnum(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
