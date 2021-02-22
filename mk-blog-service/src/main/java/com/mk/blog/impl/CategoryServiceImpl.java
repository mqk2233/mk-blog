package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.ICategoryService;
import com.mk.blog.converter.CategoryConverter;
import com.mk.blog.converter.CategoryListConverter;
import com.mk.blog.entity.Category;
import com.mk.blog.mapper.CategoryMapper;
import com.mk.blog.vo.CategoryListVo;
import com.mk.blog.vo.CategoryVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MK
 * @describe 类别表服务实现类
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public List<CategoryListVo> categoryList() {
        List<CategoryListVo> categoryList = CategoryListConverter.INSTANCE.
                toConvertVO(this.lambdaQuery()
                        .list());
        List<CategoryListVo> categoryTreeList = new ArrayList<>();
        for (CategoryListVo category : categoryList) {
            category.setLabel(category.getCategoryName());
            if (category.getPid().equals(0L)) {
                category.setCategoryList(this.buildTree(categoryList, category.getId()));
                categoryTreeList.add(category);
            }
        }
        return categoryTreeList;
    }

    @Override
    public CategoryVo categoryById(Long id) {
        return CategoryConverter.INSTANCE.toConvert(this.getById(id));
    }

    @Override
    public boolean addCategory(CategoryVo vo) {
        return this.save(CategoryConverter.INSTANCE.toConvertVO(vo));
    }

    @Override
    public boolean editCategory(CategoryVo vo) {
        return this.updateById(CategoryConverter.INSTANCE.toConvertVO(vo));
    }

    @Override
    public boolean delCategory(Long id) {
        return this.removeById(id);
    }

    @Override
    public List<CategoryListVo> buildTree(List<CategoryListVo> categoryList, Long id) {
        List<CategoryListVo> categoryChildList = new ArrayList<>();
        for (CategoryListVo item : categoryList) {
            if (item.getPid().equals(id)) {
                List<CategoryListVo> categoryGrandsonList = this.buildTree(categoryList, item.getId());
                item.setCategoryList(categoryGrandsonList);
                categoryChildList.add(item);
            }
        }
        return categoryChildList;
    }
}
