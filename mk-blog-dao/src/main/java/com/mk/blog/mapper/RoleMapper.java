package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mk.blog.entity.Role;

import java.util.List;

/**
 * @author MK
 * @describe 角色表mapper类
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户id查询对应角色名
     *
     * @param userId 用户id
     * @return {@link List<String> }
     * @author MK
     */
    List<String> selectRoleByUserId(Long userId);
}
