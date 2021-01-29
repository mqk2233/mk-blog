package com.mk.blog.impl;

import com.mk.blog.entity.Role;
import com.mk.blog.mapper.RoleMapper;
import com.mk.blog.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @describe 角色表服务实现类
 * @author MK
 * @date 2021-01-29 14:45:44
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
