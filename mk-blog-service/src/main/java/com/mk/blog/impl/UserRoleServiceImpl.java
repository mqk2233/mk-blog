package com.mk.blog.impl;

import com.mk.blog.entity.UserRole;
import com.mk.blog.mapper.UserRoleMapper;
import com.mk.blog.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @describe 用户角色中间表服务实现类
 * @author MK
 * @date 2021-01-29 14:46:35
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
