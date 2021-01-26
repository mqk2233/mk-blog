package com.mk.blog.controller;


import com.mk.blog.IArticleService;
import com.mk.blog.vo.ArticleVo;
import com.pig4cloud.plugin.idempotent.annotation.Idempotent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MK
 * @date 2021-01-25 18:00:56
 * @describe 文章表前端控制器
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private IArticleService articleService;

    @Idempotent(key = "",expireTime = 10)
    @GetMapping("dd")
    public List<ArticleVo> dd(){
        return articleService.selectAll();
    }
}

