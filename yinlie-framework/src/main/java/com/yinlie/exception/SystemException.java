package com.yinlie.exception;

import com.yinlie.enums.AppHttpCodeEnum;

/**
 * @author Oyerlicent
 * @create 2023-01-30 23:06
 **/
public class SystemException extends RuntimeException{
    private int code;
    private String msg;

    public int getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}
