package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mk.blog.entity.Label;
import com.mk.blog.vo.LabelListVo;

import java.util.List;

/**
 * @author MK
 * @describe 标签表mapper类
 */
public interface LabelMapper extends BaseMapper<Label> {

    List<LabelListVo> selectLabelList();
}
