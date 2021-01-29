package com.mk.blog.impl;

import com.mk.blog.entity.Menu;
import com.mk.blog.mapper.MenuMapper;
import com.mk.blog.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @describe 菜单表服务实现类
 * @author MK
 * @date 2021-01-29 14:45:33
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
