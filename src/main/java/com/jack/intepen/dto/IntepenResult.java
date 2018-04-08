package com.jack.intepen.dto;

/**
 * Created by 11407 on 30/030.
 */
public class IntepenResult<T> {

    private int code;

    private T data;

    private String error;

    public IntepenResult(int code, T data){
        this.code = code;
        this.data = data;
    }

    public IntepenResult(int code, String error){
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
