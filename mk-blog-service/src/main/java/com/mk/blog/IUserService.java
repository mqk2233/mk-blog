package com.mk.blog;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.blog.entity.User;

/**
 * @author MK
 * @describe 用户表服务类
 */
public interface IUserService extends IService<User> {


    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return {@link String }
     * @author MK
     */
    String login(String userName, String passWord);

    /**
     * 通过用户名查询一个用户
     *
     * @param userName 用户名
     * @return {@link User }
     * @author MK
     */
    User oneUser(String userName);
}
