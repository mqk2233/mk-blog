package com.mk.blog.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mk.blog.entity.User;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.mapper.RoleMapper;
import com.mk.blog.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author MK
 * @date 2021/1/6
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, s);
        User user = Optional.ofNullable(userMapper.selectOne(wrapper))
                .orElseThrow(()-> new CustomException(ResponseEnum.LOGIN_ERROR));
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassWord(),
                roleMapper.selectRoleByUserId(user.getId()).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}
