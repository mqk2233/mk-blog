package com.mk.blog.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mk.blog.ILabelService;
import com.mk.blog.converter.LabelConverter;
import com.mk.blog.entity.Label;
import com.mk.blog.http.ResponseEntity;
import com.mk.blog.http.ResponseListEntity;
import com.mk.blog.validator.groups.Insert;
import com.mk.blog.validator.groups.Update;
import com.mk.blog.vo.LabelListVo;
import com.mk.blog.vo.LabelVo;
import com.pig4cloud.plugin.idempotent.annotation.Idempotent;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author MK
 * @describe 标签表前端控制器
 */
@Validated
@RestController
@RequestMapping("/label")
public class LabelController {

    @Resource
    private ILabelService iLabelService;

    @GetMapping(value = "get-label-list")
    @Operation(summary = "获取标签列表")
    public ResponseEntity<List<LabelListVo>> getLabelList() {
        return ResponseEntity.buildData(iLabelService.labelList());
    }

    @GetMapping(value = {"authorization/get-label-page-list/{labelName}/{current}/{size}",
            "authorization/get-label-page-list/{current}/{size}"})
    @Operation(summary = "获取标签分页列表")
    public ResponseListEntity<LabelVo> getLabelPageList(@PathVariable(required = false) String labelName,
                                                        @PathVariable
                                                        @NotNull(message = "页数不能为空") Long current,
                                                        @PathVariable
                                                        @NotNull(message = "页长不能为空") Long size) {
        IPage<Label> page = new Page<>(current, size);
        page = iLabelService.lambdaQuery()
                .like(StrUtil.isNotBlank(labelName), Label::getLabelName, labelName)
                .orderByAsc(Label::getSort)
                .page(page);
        return ResponseListEntity.buildData(LabelConverter.INSTANCE.toConvertList(page.getRecords()), page.getTotal());
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PostMapping(value = "authorization/do-add-label")
    @Operation(summary = "新增标签")
    public ResponseEntity<LabelVo> doAddLabel(@RequestBody
                                              @Validated(Insert.class)
                                                      LabelVo vo) {
        return iLabelService.addLabel(vo) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @PutMapping(value = "authorization/do-edit-label")
    @Operation(summary = "编辑标签")
    public ResponseEntity<LabelVo> doEditLabel(@RequestBody
                                               @Validated(Update.class)
                                                       LabelVo vo) {
        return iLabelService.editLabel(vo) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }

    @Idempotent(key = "", info = "请勿重复请求")
    @DeleteMapping(value = "authorization/do-del-label/{id}")
    @Operation(summary = "删除标签")
    public ResponseEntity<LabelVo> doDelLabel(@PathVariable
                                              @NotNull(message = "标签id不能为空")
                                                      Long id) {
        return iLabelService.delLabel(id) ?
                ResponseEntity.buildSuccess() :
                ResponseEntity.buildError();
    }
}

