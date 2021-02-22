package com.mk.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.Category;
import com.mk.blog.vo.CategoryListVo;
import com.mk.blog.vo.CategoryVo;

import java.util.List;

/**
 * @author MK
 * @describe 类别表服务类
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 获取有效的分类列表
     *
     * @return {@link List<CategoryListVo> }
     * @author MK
     */
    List<CategoryListVo> categoryList();

    /**
     * 通过id查找分类
     *
     * @param id 分类id
     * @return {@link CategoryVo }
     * @author MK
     */
    CategoryVo categoryById(Long id);

    /**
     * 新增分类
     *
     * @param vo 分类vo
     * @return {@link int }
     * @author MK
     */
    boolean addCategory(CategoryVo vo);

    /**
     * 编辑分类
     *
     * @param vo 分类vo
     * @return {@link int }
     * @author MK
     */
    boolean editCategory(CategoryVo vo);

    /**
     * 删除分类
     *
     * @param id 分类id
     * @return {@link int }
     * @author MK
     */
    boolean delCategory(Long id);

    /**
     * 构建分类json树
     *
     * @param categoryList 分类集合
     * @param id           分类id
     * @return {@link List<CategoryListVo> }
     * @author MK
     */
    List<CategoryListVo> buildTree(List<CategoryListVo> categoryList, Long id);
}
