package com.mk.blog.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mk.blog.IArticleService;
import com.mk.blog.http.ResponseEntity;
import com.mk.blog.http.ResponseListEntity;
import com.mk.blog.vo.ArticleDetailsVo;
import com.mk.blog.vo.ArticleListVo;
import com.mk.blog.vo.TimeAisVo;
import com.pig4cloud.plugin.idempotent.annotation.Idempotent;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MK
 * @describe 文章表前端控制器
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService iArticleService;

    @GetMapping(value = {"get-article-list/{title}/{isDeleted}/{current}/{size}",
            "get-article-list/{isDeleted}/{current}/{size}",
            "get-article-list/{current}/{size}"})
    @Operation(summary = "文章列表")
    public ResponseListEntity<ArticleListVo> getArticleAdminList(@PathVariable(required = false) String title,
                                                                 @PathVariable(required = false) Integer isDeleted,
                                                                 @PathVariable Long current,
                                                                 @PathVariable Long size) {
        IPage<ArticleListVo> page = new Page<>(current, size);
        page = iArticleService.articleList(title, isDeleted, null, page);
        return ResponseListEntity.buildData(page.getRecords(), page.getTotal());
    }

    @GetMapping(value = "get-article-details/{id}")
    @Operation(summary = "通过文章id获取文章详情")
    public ResponseEntity<ArticleDetailsVo> getArticleDetails(@PathVariable Long id) {
        return ResponseEntity.buildData(iArticleService.articleById(id));
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PostMapping(value = "authorization/do-add-article")
    @Operation(summary = "新增文章")
    public ResponseEntity<ArticleDetailsVo> doAddArticle(@RequestBody ArticleDetailsVo articleDetailsVo) {
        return iArticleService.addArticle(articleDetailsVo) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PutMapping(value = "authorization/do-edit-article")
    @Operation(summary = "编辑文章")
    public ResponseEntity<ArticleDetailsVo> doEditArticle(@RequestBody ArticleDetailsVo articleDetailsVo) {
        return iArticleService.editArticle(articleDetailsVo) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @DeleteMapping(value = "authorization/do-del-article/{id}")
    @Operation(summary = "删除文章")
    public ResponseEntity<ArticleDetailsVo> doDelArticle(@PathVariable Long id) {
        return iArticleService.delArticle(id) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PutMapping(value = "authorization/do-release-article/{id}")
    @Operation(summary = "发布文章")
    public ResponseEntity<ArticleDetailsVo> doReleaseArticle(@PathVariable Long id) {
        return iArticleService.releaseArticle(id) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PutMapping(value = "authorization/do-recovery-article/{id}")
    @Operation(summary = "恢复文章")
    public ResponseEntity<ArticleDetailsVo> doRecoveryArticle(@PathVariable Long id) {
        return iArticleService.recoveryArticle(id) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @GetMapping(value = "get-time-ais")
    @Operation(summary = "恢复文章")
    public ResponseEntity<List<TimeAisVo>> getTimeAis() {
        return ResponseEntity.buildData(iArticleService.timeAis());
    }
}

