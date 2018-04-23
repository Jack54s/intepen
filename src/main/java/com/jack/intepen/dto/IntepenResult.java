package com.jack.intepen.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 11407 on 30/030.
 */
@ApiModel(value = "intepenResult", description = "返回的数据对象")
public class IntepenResult<T> {

    @ApiModelProperty(value = "返回码")
    private int code;

    @ApiModelProperty(value = "返回的数据")
    private T data;

    @ApiModelProperty(value = "返回的错误信息")
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
