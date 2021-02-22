package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IRoleService;
import com.mk.blog.entity.Role;
import com.mk.blog.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author MK
 * @describe 角色表服务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
