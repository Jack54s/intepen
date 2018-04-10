package com.jack.intepen.enums;

/**
 * Created by 11407 on 5/005.
 */
public enum ElderEnum {
    LIST_ELDER_ERROR(200, "获取老人列表失败！"),
    QUERY_ELDER_ERROR(201, "获取老人信息失败！"),
    ADD_ERROR(202, "新增老人失败！"),
    EDIT_ERROR(203, "编辑老人信息失败！"),
    DELETE_ERROR(204, "删除老人信息失败！"),
    QUERY_UNDISTRIBUTED_ELDER(205, "获取未分配护工老人失败！"),
    DISTRIBUTE_NURSE_ERROR(206, "分配护工失败！")
    ;

    private int code;
    private String error;

    ElderEnum(int code, String error){
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
