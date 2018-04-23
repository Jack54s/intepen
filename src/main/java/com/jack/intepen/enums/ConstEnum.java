package com.jack.intepen.enums;

import org.springframework.http.HttpStatus;

/**
 * Created by 11407 on 19/019.
 */
public enum ConstEnum {
    NURSE(1, "Nurse"),
    FAMILY(2, "Family"),
    UNCOMPLETED(0, "Uncompleted"),
    COMPLETED(1, "Completed"),
    ONSCHEDAL(2, "Onschedal")
    ;

    private int code;
    private String error;

    ConstEnum(int code, String error){
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
