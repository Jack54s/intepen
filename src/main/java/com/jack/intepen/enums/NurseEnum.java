package com.jack.intepen.enums;

/**
 * Created by 11407 on 6/006.
 */
public enum NurseEnum {
    LIST_NURSE_ERROR(400, "获取护工列表失败！"),
    QUERY_NURSE_ERROR(401, "获取护工信息失败！"),
    ADD_ERROR(202, "新增护工失败！"),
    EDIT_ERROR(203, "编辑护工信息失败！"),
    DELETE_ERROR(204, "删除护工信息失败！"),
    QUERY_ELDER_NURSED(205, "查询被看护的老人失败！")
    ;

    private int code;
    private String error;

    NurseEnum(int code, String error){
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
