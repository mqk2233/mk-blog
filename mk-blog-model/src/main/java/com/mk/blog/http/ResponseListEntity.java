package com.mk.blog.http;

import com.mk.blog.enums.ResponseEnum;
import lombok.Data;

import java.util.List;

/**
 * @author MK
 **/
@Data
public class ResponseListEntity<T> {
    private Integer code;

    private String msg;

    private List<T> data;

    private long total;

    private ResponseListEntity(List<T> data, long total) {
        this.code = ResponseEnum.SUCCESS.getCode();
        this.msg = ResponseEnum.SUCCESS.getMsg();
        this.data = data;
        this.total = total;
    }

    public static <V> ResponseListEntity<V> buildData(List<V> data, long total) {
        return new ResponseListEntity<>(data, total);
    }


}
