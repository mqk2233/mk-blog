package com.mk.blog.utils;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author MK
 * @describe 用于判断的工具类减少if
 */
public class BooleanUtil {

    /**
     * 如果两个字符串不相等就抛出异常
     *
     * @param val1              值1
     * @param val2              值2
     * @param exceptionSupplier 异常
     * @author MK
     */
    public static <X extends Throwable> void strNotEqualsThrow(String val1, String val2, Supplier<? extends X> exceptionSupplier) throws X {
        if (!StrUtil.equals(val1, val2)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 判断字符串数组里面任意一个为空或为null就抛出异常
     *
     * @param exceptionSupplier 异常
     * @param val               字符串数组
     * @author MK
     */
    public static <X extends Throwable> void strAnyIsBlankThrow(Supplier<? extends X> exceptionSupplier, String... val) throws X {
        if (Arrays.stream(val).anyMatch(StrUtil::isBlank)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 为否时抛出异常
     *
     * @param val               boolean
     * @param exceptionSupplier 异常
     * @author MK
     */
    public static <X extends Throwable> void falseThrow(boolean val, Supplier<? extends X> exceptionSupplier) throws X {
        if (!val) {
            throw exceptionSupplier.get();
        }
    }
}
