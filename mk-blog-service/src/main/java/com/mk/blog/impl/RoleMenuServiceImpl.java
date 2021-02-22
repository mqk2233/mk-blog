package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IRoleMenuService;
import com.mk.blog.entity.RoleMenu;
import com.mk.blog.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author MK
 * @describe 角色菜单中间表服务实现类
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

}
