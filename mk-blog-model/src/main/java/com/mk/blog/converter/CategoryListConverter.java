package com.mk.blog.converter;

import com.mk.blog.entity.Category;
import com.mk.blog.vo.CategoryListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
 */
@Mapper
public interface CategoryListConverter {

    CategoryListConverter INSTANCE = Mappers.getMapper(CategoryListConverter.class);

    /**
     * 分类集合转listVo集合
     *
     * @param source 分类集合
     * @return {@link List <CategoryListVo> }
     * @author MK
     */
    List<CategoryListVo> toConvertVO(List<Category> source);
}
