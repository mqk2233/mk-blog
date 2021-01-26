package com.mk.blog.impl;

import com.mk.blog.entity.User;
import com.mk.blog.mapper.UserMapper;
import com.mk.blog.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author MK
 * @date 2021-01-10 20:13:02
 * @describe 用户表服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



    public void login(String userName, String passWord, String verificationCode) {

    }
}
