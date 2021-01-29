package com.mk.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.User;

/**
 * @author MK
 * @date 2021-01-10 20:13:02
 * @describe 用户表服务类
 */
public interface IUserService extends IService<User> {


    void login(String userName, String passWord, String verificationCode);
}
