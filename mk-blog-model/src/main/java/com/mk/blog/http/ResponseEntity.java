package com.mk.blog.http;

import com.mk.blog.enums.ResponseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * @author MK
 * @describe 请求返回对象
 **/
@Getter
public class ResponseEntity<T> {

    @Schema(description = "状态码")
    private final Integer code;

    @Schema(description = "消息")
    private final String msg;

    @Schema(description = "响应数据")
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
