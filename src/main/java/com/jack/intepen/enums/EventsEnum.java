package com.jack.intepen.enums;

/**
 * Created by 11407 on 20/020.
 */
public enum EventsEnum {
    LIST_ALL_EVENTS_ERROR(501, "查询所有事件错误！"),
    LIST_UNCOMPLETED_EVENTS_ERROR(502, "列出未完成事件错误！"),
    COMPLETE_EVENTS_ERROR(503, "完成事件失败！"),
    ;

    private int code;
    private String error;

    EventsEnum(int code, String error){
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
