package com.jack.intepen.enums;

/**
 * Created by 11407 on 24/024.
 */
public enum ThresholdEnum {
    LIST_ALL_THRESHOLD_ERROR(600, "列出所有的阈值失败！"),
    ADD_ERROR(601, "增加一个阈值项失败！"),
    EDIT_ERROR(602, "编辑阈值失败！"),
    DELETE_ERROR(603, "删除一个阈值失败！"),
    ;

    private int code;
    private String error;


    ThresholdEnum(int code, String error) {
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
