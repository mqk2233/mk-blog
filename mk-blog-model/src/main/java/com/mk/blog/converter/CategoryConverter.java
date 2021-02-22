package com.mk.blog.converter;

import com.mk.blog.entity.Category;
import com.mk.blog.vo.CategoryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
 */
@Mapper
public interface CategoryConverter {

    CategoryConverter INSTANCE = Mappers.getMapper(CategoryConverter.class);

    /**
     * 分类vo转实体
     *
     * @param source 分类vo
     * @return {@link List <CategoryVo> }
     * @author MK
     */
    Category toConvertVO(CategoryVo source);

    /**
     * 实体转vo
     *
     * @param source 实体对象
     * @return {@link CategoryVo }
     * @author MK
     */
    CategoryVo toConvert(Category source);
}
