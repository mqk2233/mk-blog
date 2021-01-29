package com.mk.blog.impl;

import com.mk.blog.entity.Category;
import com.mk.blog.mapper.CategoryMapper;
import com.mk.blog.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @describe 类别表服务实现类
 * @author MK
 * @date 2021-01-29 14:45:11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
