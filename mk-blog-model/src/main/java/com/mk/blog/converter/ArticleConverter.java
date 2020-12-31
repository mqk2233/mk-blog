package com.mk.blog.converter;


import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
 * @date 2020/12/24
 */
@Mapper
public interface ArticleConverter {
    ArticleConverter INSTANCE = Mappers.getMapper(ArticleConverter.class);

    /**
     * Article转ArticleVo
     *
     * @param source 要转换的类
     * @return {@link ArticleVo }
     * @author MK
     * @date 2020/12/29 11:32
     */
    ArticleVo toConvertVO(Article source);

    /**
     * List<Article>转List<ArticleVo>
     *
     * @param source 要转换的集合
     * @return {@link List<ArticleVo> }
     * @author MK
     * @date 2020/12/29 11:32
     */
    List<ArticleVo> toConvertVOList(List<Article> source);
}
