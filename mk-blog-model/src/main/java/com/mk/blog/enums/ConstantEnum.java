package com.mk.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author MK
 * @date 2021/1/12
 * 常量枚举
 */
@Getter
@AllArgsConstructor
public enum ConstantEnum {

    /**
     * 存放Token的Header Key
     */
    TOKEN("Authentication"),
    /**
     * Token前缀
     */
    TOKEN_PREFIX("Bearer")
    ;

    private final String name;
}
