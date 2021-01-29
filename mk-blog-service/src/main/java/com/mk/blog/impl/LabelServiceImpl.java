package com.mk.blog.impl;

import com.mk.blog.entity.Label;
import com.mk.blog.mapper.LabelMapper;
import com.mk.blog.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @describe 标签表服务实现类
 * @author MK
 * @date 2021-01-29 14:45:23
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

}
