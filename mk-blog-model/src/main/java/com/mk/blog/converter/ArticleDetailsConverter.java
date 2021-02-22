package com.mk.blog.converter;

import com.mk.blog.entity.Article;
import com.mk.blog.vo.ArticleDetailsVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author MK
 */
@Mapper
public interface ArticleDetailsConverter {

    ArticleDetailsConverter INSTANCE = Mappers.getMapper(ArticleDetailsConverter.class);

    /**
     * 文章转Vo
     *
     * @param source 文章
     * @return {@link ArticleDetailsVo }
     * @author MK
     */
    ArticleDetailsVo toConvertVO(Article source);

    /**
     * vo转文章对象
     *
     * @param source vo
     * @return {@link Article }
     * @author MK
     */
    Article toConvert(ArticleDetailsVo source);
}
