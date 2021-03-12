package com.mk.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.Label;
import com.mk.blog.vo.LabelListVo;
import com.mk.blog.vo.LabelVo;

import java.util.List;

/**
 * @author MK
 * @describe 标签表服务类
 */
public interface ILabelService extends IService<Label> {

    /**
     * 标签列表
     *
     * @return {@link List<LabelListVo> }
     * @author MK
     */
    List<LabelListVo> labelList();

    /**
     * 新增标签
     *
     * @param vo 标签vo
     * @return {@link int }
     * @author MK
     */
    boolean addLabel(LabelVo vo);

    /**
     * 编辑标签
     *
     * @param vo 标签vo
     * @return {@link int }
     * @author MK
     */
    boolean editLabel(LabelVo vo);

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return {@link int }
     * @author MK
     */
    boolean delLabel(Long id);
}
