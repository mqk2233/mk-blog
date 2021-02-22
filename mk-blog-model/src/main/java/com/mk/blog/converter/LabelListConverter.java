package com.mk.blog.converter;

import com.mk.blog.entity.Label;
import com.mk.blog.vo.LabelListVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
 */
@Mapper
public interface LabelListConverter {

    LabelListConverter INSTANCE = Mappers.getMapper(LabelListConverter.class);

    /**
     * 标签集合转listVo集合
     *
     * @param source 标签集合
     * @return {@link List <LabelListVo> }
     * @author MK
     */
    List<LabelListVo> toConvertVO(List<Label> source);
}
