package com.mk.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1**	信息，服务器收到请求，需要请求者继续执行操作
 * 2**	成功，操作被成功接收并处理
 * 3**	重定向，需要进一步的操作以完成请求
 * 4**	客户端错误，请求包含语法错误或无法完成请求
 * 5**	服务器错误，服务器在处理请求的过程中发生了错误
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    SUCCESS(200, "成功"), UNKNOWN_ERROR(-1, "未知错误"),

    TOKEN_NULL(100, "请求token为空"), TOKEN_ERROR(101, "无效的token"),
    LOGIN_ERROR(102, "用户名或密码错误"), PARAM_ERROR(103, "请求参数有误"),
    NO_PARAM(105, "缺少必要参数"), OPERATION_FAILED(106, "操作失败"),
    LOGIN_OVERTIME(107, "登录过期"),


    NOT_LOGGED(401, "未登录，请先登录"),
    ILLEGAL_REQUESTS(403, "非法请求"), VRIFY_CODE_INCORRECT(418, "验证码不正确"),

    USER_INCORRECT(419, "用户名或密码不正确"), VRIFY_CODE_INVALID(420, "验证码已失效"),;
    private Integer code;

    private String msg;
}
