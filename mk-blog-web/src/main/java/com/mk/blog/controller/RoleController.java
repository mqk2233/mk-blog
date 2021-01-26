package com.mk.blog.controller;


import com.mk.blog.entity.Role;
import com.mk.blog.http.ResponseEntity;
import com.mk.blog.impl.RoleServiceImpl;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MK
 * @date 2021-01-25 16:42:05
 * @describe 角色表前端控制器
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleServiceImpl roleService;

    @PostMapping("add")
    public ResponseEntity<String> addRole(@RequestBody Role role) throws MissingServletRequestParameterException {
        throw new MissingServletRequestParameterException("ddd", "ddd");
    }
}

