package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.ILabelService;
import com.mk.blog.converter.LabelConverter;
import com.mk.blog.entity.Label;
import com.mk.blog.mapper.LabelMapper;
import com.mk.blog.vo.LabelListVo;
import com.mk.blog.vo.LabelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MK
 * @describe 标签表服务实现类
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Resource
    private LabelMapper labelMapper;

    @Override
    public List<LabelListVo> labelList() {
        return labelMapper.selectLabelList();
    }

    @Override
    public boolean addLabel(LabelVo vo) {
        return this.save(LabelConverter.INSTANCE.toConvertVO(vo));
    }

    @Override
    public boolean editLabel(LabelVo vo) {
        return this.updateById(LabelConverter.INSTANCE.toConvertVO(vo));
    }

    @Override
    public boolean delLabel(Long id) {
        return this.removeById(id);
    }
}
