package com.mk.blog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MK
 **/
public class HtmlUtil {

    /**
     * 获取html文本正文
     *
     * @param html html代码
     * @return java.lang.String
     */
    public static String getText(String html) {
        String text = html.replaceAll("<.*?>", "");
        if (text.length() > 204) {
            return text.substring(3, 200) + "...";
        } else {
            return text;
        }
    }

    /**
     * 去掉目录
     *
     * @param md md文本
     * @return java.lang.String
     */
    public static String removeCatalog(String md) {
        return md.replace("@[TOC]", "");
    }

    /**
     * 获取目录
     *
     * @param html html文本
     * @return java.lang.String
     */
    public static String getCatalog(String html) {
        Pattern pattern = Pattern.compile("^.*?</p>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(0).replace("Table of Contents", "目录");
        } else {
            return "";
        }
    }
}