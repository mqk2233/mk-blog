package com.mk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mk.blog.entity.Role;

import java.util.List;

/**
 * @author MK
 * @date 2021-01-25 16:42:05
 * @describe 角色表mapper类
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户id查询对应角色名
     *
     * @param userId 用户id
     * @return {@link List<String> }
     * @author MK
     * @date 2021/1/10 20:36
     */
    List<String> selectRoleByUserId(Long userId);
}
