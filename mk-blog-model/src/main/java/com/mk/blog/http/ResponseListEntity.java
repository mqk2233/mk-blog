package com.mk.blog.http;

import com.mk.blog.enums.ResponseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author MK
 **/
@Data
public class ResponseListEntity<T> {

    @Schema(description = "状态码")
    private Integer code;

    @Schema(description = "消息")
    private String msg;

    @Schema(description = "响应数据")
    private List<T> data;

    @Schema(description = "数据总条数")
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
