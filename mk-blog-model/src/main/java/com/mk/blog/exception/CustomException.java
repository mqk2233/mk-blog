package com.mk.blog.exception;


import com.mk.blog.enums.ResponseEnum;

/**
 * @author MK
 * @describe 自定义异常
 **/
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 194906846739586856L;

    private final ResponseEnum responseEnum;

    public CustomException(ResponseEnum responseEnum) {
        super(responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getEnum() {
        return responseEnum;
    }
}
