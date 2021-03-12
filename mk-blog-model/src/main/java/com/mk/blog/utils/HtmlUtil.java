package com.mk.blog.utils;

import cn.hutool.core.collection.CollUtil;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.vo.AnchorVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author MK
 * @describe http工具类
 **/
public class HtmlUtil {

    private static final Integer[] grades = {1, 2, 3, 4, 5, 6};

    /**
     * 获取html文本正文
     *
     * @param html html代码
     * @return java.lang.String
     */
    public static String getText(String html) {
        String text = cn.hutool.http.HtmlUtil.cleanHtmlTag(html);
        if (text.length() > 204) {
            return text.substring(0, 200) + "...";
        } else {
            return text;
        }
    }

    /**
     * 通过文章Html提取文章的标题
     *
     * @param html 文章Html
     * @return {@link String }
     * @author MK
     */
    public static String getTitle(String html) {
        String a = "<h1>.*</h1>";
        Pattern p = Pattern.compile(a);
        Matcher m = p.matcher(html);
        if (m.find()) {
            String sub = html.substring(m.start(), m.end());
            return cn.hutool.http.HtmlUtil.cleanHtmlTag(sub);
        } else {
            throw new CustomException(ResponseEnum.PARAM_ERROR);
        }
    }

    /**
     * 获取Html目录树
     *
     * @param html Html文本
     * @return {@link List<AnchorVo> }
     * @author MK
     */
    public static List<AnchorVo> getCatalogTree(String html) {
        List<AnchorVo> list = new ArrayList<>();
        Arrays.stream(grades).forEach(item -> {
            String a = "<h" + item + ">.*</h" + item + ">";
            Pattern p = Pattern.compile(a);
            Matcher m = p.matcher(html);
            while (m.find()) {
                AnchorVo anchorVo = new AnchorVo();
                String sub = html.substring(m.start(), m.end());
                anchorVo.setGrade(item);
                anchorVo.setId(HtmlUtil.getId(sub));
                anchorVo.setTitle(cn.hutool.http.HtmlUtil.cleanHtmlTag(sub));
                anchorVo.setSort(HtmlUtil.getIdNum(anchorVo.getId()));
                list.add(anchorVo);
            }
        });
        list.sort(Comparator.comparing(AnchorVo::getSort));
        // 获取文章标题
        List<AnchorVo> parentList = list.stream()
                .filter(item -> item.getGrade().equals(1))
                .collect(Collectors.toList());
        return getAnchorVos(1, parentList, list);
    }

    /**
     * 对集合进行处理
     *
     * @param grade      等级
     * @param parentList 返回集合
     * @param list       要处理的数据集合
     * @return {@link List<AnchorVo> }
     * @author MK
     */
    private static List<AnchorVo> getAnchorVos(int grade, List<AnchorVo> parentList, List<AnchorVo> list) {
        if (CollUtil.isNotEmpty(parentList)) {
            for (int i = 0; i < parentList.size(); i++) {
                if (parentList.size() == i + 1) {
                    Integer maxSort = getEndIndex(grade, parentList.get(i).getSort(), list);
                    parentList.get(i).setChildList(getTree(grade, parentList.get(i).getSort(), maxSort == null ? Integer.MAX_VALUE : maxSort, list));
                } else {
                    parentList.get(i).setChildList(getTree(grade, parentList.get(i).getSort(), parentList.get(i + 1).getSort(), list));
                }
            }
        }
        return parentList;
    }

    /**
     * 获取同等级的下一个标题对象的排序号
     *
     * @param grade 标题等级
     * @param sort  排序号
     * @param list  要处理的数据集合
     * @return {@link Integer }
     * @author MK
     */
    private static Integer getEndIndex(int grade, int sort, List<AnchorVo> list) {
        return list.stream()
                .filter(item -> item.getGrade().equals(grade) && item.getSort() > sort)
                .findFirst()
                .orElseGet(AnchorVo::new)
                .getSort();
    }

    /**
     * 获取树
     *
     * @param grade   等级
     * @param minSort 最小排序号
     * @param maxSort 最大排序号
     * @param list    要转换为树的集合
     * @return {@link List<AnchorVo> }
     * @author MK
     */
    private static List<AnchorVo> getTree(Integer grade, Integer minSort, Integer maxSort, List<AnchorVo> list) {
        List<AnchorVo> childList = new ArrayList<>();
        //下级没有的话再下一级
        while (CollUtil.isEmpty(childList) && grade != 7) {
            grade += 1;
            Integer finalGrade = grade;
            childList = list.stream()
                    .filter(item -> item.getGrade().equals(finalGrade) && item.getSort() > minSort && item.getSort() < maxSort)
                    .collect(Collectors.toList());
        }
        return getAnchorVos(grade, childList, list);
    }

    /**
     * 获取标签id
     *
     * @param html html
     * @return {@link String }
     * @author MK
     */
    private static String getId(String html) {
        Matcher m = Pattern.compile("id=\".*_\\d+").matcher(html);
        if (m.find()) {
            return "#" + html.substring(m.start(), m.end()).substring(4);
        } else {
            return "";
        }
    }

    /**
     * 获取Html标签中id的数字
     *
     * @param id Html标签数字
     * @return {@link Integer }
     * @author MK
     */
    private static Integer getIdNum(String id) {
        Matcher m = Pattern.compile("_\\d+").matcher(id);
        if (m.find()) {
            return Integer.parseInt(id.substring(m.start(), m.end()).substring(1));
        } else {
            return 0;
        }
    }
}