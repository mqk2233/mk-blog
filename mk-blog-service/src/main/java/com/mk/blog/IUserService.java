package com.mk.blog;

import com.mk.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author MK
 * @describe 用户表服务类
 * @date 2021-01-30 17:11:19
 */
public interface IUserService extends IService<User> {


    /**
     * 用户登录
     *
     * @param userName         用户名
     * @param passWord         密码
     * @return {@link String }
     * @author MK
     * @date 2021/1/30 17:53
     */
    String login(String userName, String passWord);

    /**
     * 通过用户名查询一个用户
     *
     * @param userName 用户名
     * @return {@link User }
     * @author MK
     * @date 2021/2/19 9:59
     */
    User oneUser(String userName);
}
