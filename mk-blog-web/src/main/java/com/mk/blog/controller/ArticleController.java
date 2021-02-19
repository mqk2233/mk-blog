package com.mk.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mk.blog.IArticleService;
import com.mk.blog.http.ResponseListEntity;
import com.mk.blog.vo.ArticleListVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MK
 * @describe 文章表前端控制器
 * @date 2021-01-29 14:44:28
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService iArticleService;

    @GetMapping(value = {"authorization/get-article-list/{title}/{current}/{size}",
            "authorization/get-article-list/{current}/{size}"})
    @Operation(summary = "文章列表")
    public ResponseListEntity<ArticleListVo> getArticleAdminList(@PathVariable(required = false) String title,
                                                                 @PathVariable Long current,
                                                                 @PathVariable Long size) {
        IPage<ArticleListVo> page = new Page<>(current,size);
        page = iArticleService.articleList(title, null, null, page);
        return ResponseListEntity.buildData(page.getRecords(), page.getTotal());
    }
}

