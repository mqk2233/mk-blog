package com.mk.blog.http;

import com.mk.blog.enums.ResponseEnum;
import lombok.Getter;

/**
 * @author MK
 **/
@Getter
public class ResponseEntity<T> {
    private final Integer code;

    private final String msg;

    private T data;


    private ResponseEntity(T data) {
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private ResponseEntity(ResponseEnum responseEnum) {
        this.msg = responseEnum.getMsg();
        this.code = responseEnum.getCode();
    }

    private ResponseEntity(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public static <V> ResponseEntity<V> buildSuccess() {
        return new ResponseEntity<>(ResponseEnum.SUCCESS);
    }

    public static <V> ResponseEntity<V> buildError() {
        return new ResponseEntity<>(ResponseEnum.UNKNOWN_ERROR);
    }


    public static <V> ResponseEntity<V> buildData(V data) {
        return new ResponseEntity<>(data);
    }

    public static <V> ResponseEntity<V> buildByEnum(ResponseEnum responseEnum) {
        return new ResponseEntity<>(responseEnum);
    }

    public static <V> ResponseEntity<V> buildByMsg(String msg, int code) {
        return new ResponseEntity<>(msg, code);
    }
}
