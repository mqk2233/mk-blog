package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IUserRoleService;
import com.mk.blog.entity.UserRole;
import com.mk.blog.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author MK
 * @describe 用户角色中间表服务实现类
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
