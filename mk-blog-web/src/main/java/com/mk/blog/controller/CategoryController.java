package com.mk.blog.controller;


import com.mk.blog.ICategoryService;
import com.mk.blog.http.ResponseEntity;
import com.mk.blog.validator.groups.Insert;
import com.mk.blog.validator.groups.Update;
import com.mk.blog.vo.CategoryListVo;
import com.mk.blog.vo.CategoryVo;
import com.pig4cloud.plugin.idempotent.annotation.Idempotent;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author MK
 * @describe 类别表前端控制器
 */
@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private ICategoryService iCategoryService;

    @GetMapping("get-category-list")
    @Operation(summary = "获取有效的分类列表")
    public ResponseEntity<List<CategoryListVo>> getCategoryList() {
        return ResponseEntity.buildData(iCategoryService.categoryList());
    }

    @GetMapping(value = "authorization/get-category-details/{id}")
    @Operation(summary = "通过文章id获取文章详情")
    public ResponseEntity<CategoryVo> getArticleDetails(@PathVariable
                                                        @NotNull(message = "分类id不能为空")
                                                                Long id) {
        return ResponseEntity.buildData(iCategoryService.categoryById(id));
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PostMapping(value = "authorization/do-add-category")
    @Operation(summary = "新增分类")
    public ResponseEntity<CategoryVo> doAddCategory(@RequestBody
                                                    @Validated(Insert.class)
                                                            CategoryVo vo) {
        return iCategoryService.addCategory(vo) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PutMapping(value = "authorization/do-edit-category")
    @Operation(summary = "编辑分类")
    public ResponseEntity<CategoryVo> doEditCategory(@RequestBody
                                                     @Validated(Update.class)
                                                             CategoryVo vo) {
        return iCategoryService.editCategory(vo) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @DeleteMapping(value = "authorization/do-del-category/{id}")
    @Operation(summary = "删除分类")
    public ResponseEntity<CategoryVo> doDelCategory(@PathVariable
                                                    @NotNull(message = "分类id不能为空")
                                                            Long id) {
        return iCategoryService.delCategory(id) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }
}

