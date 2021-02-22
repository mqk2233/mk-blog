package com.mk.blog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IMenuService;
import com.mk.blog.entity.Menu;
import com.mk.blog.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author MK
 * @describe 菜单表服务实现类
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
