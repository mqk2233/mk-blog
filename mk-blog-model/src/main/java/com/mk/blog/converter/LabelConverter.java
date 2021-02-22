package com.mk.blog.converter;

import com.mk.blog.entity.Label;
import com.mk.blog.vo.LabelVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author MK
 */
@Mapper
public interface LabelConverter {

    LabelConverter INSTANCE = Mappers.getMapper(LabelConverter.class);

    /**
     * 标签vo转实体
     *
     * @param source 标签vo
     * @return {@link List <LabelVo> }
     * @author MK
     */
    Label toConvertVO(LabelVo source);

    /**
     * 实体集合转vo集合
     *
     * @param source 实体对象集合
     * @return {@link LabelVo }
     * @author MK
     */
    List<LabelVo> toConvertList(List<Label> source);

    /**
     * 实体转vo
     *
     * @param source 实体对象
     * @return {@link LabelVo }
     * @author MK
     */
    LabelVo toConvert(Label source);
}
