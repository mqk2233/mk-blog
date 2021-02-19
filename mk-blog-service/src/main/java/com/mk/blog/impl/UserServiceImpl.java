package com.mk.blog.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.blog.IRedisService;
import com.mk.blog.IUserService;
import com.mk.blog.constants.SystemConstant;
import com.mk.blog.entity.User;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.mapper.UserMapper;
import com.mk.blog.utils.BooleanUtil;
import com.mk.blog.utils.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author MK
 * @describe 用户表服务实现类
 * @date 2021-01-30 17:11:19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IRedisService iRedisService;
    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Resource
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(String userName, String passWord) {
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
        BooleanUtil.falseThrow(passwordEncoder.matches(passWord,
                Optional.ofNullable(userDetails)
                        .map(UserDetails::getPassword)
                        .orElseThrow(() -> new CustomException(ResponseEnum.USER_INCORRECT))),
                () -> new CustomException(ResponseEnum.USER_INCORRECT));
        iRedisService.del(SystemConstant.VERIFICATION_CODE);
        return JwtUtil.sign(userDetails.getUsername(), userDetails.getPassword());
    }

    @Override
    public User oneUser(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        return Optional.ofNullable(this.getOne(wrapper))
                .orElseThrow(() -> new CustomException(ResponseEnum.UNKNOWN_ERROR));
    }
}
