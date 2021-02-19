package com.mk.blog.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.mk.blog.IRedisService;
import com.mk.blog.IUserService;
import com.mk.blog.constants.SystemConstant;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.http.ResponseEntity;
import com.mk.blog.utils.BooleanUtil;
import com.mk.blog.utils.IpUtil;
import com.mk.blog.vo.LoginVo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author MK
 * @describe 用户表前端控制器
 * @date 2021-01-30 17:11:19
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;
    @Resource
    private IRedisService iRedisService;

    @PostMapping("do-login")
    @Operation(summary = "用户登录")
    public ResponseEntity<String> doLogin(@RequestBody @Valid LoginVo loginVo,
                                          HttpServletRequest request) {
        String rightVerificationCode = (String) iRedisService.get(SystemConstant.VERIFICATION_CODE + IpUtil.getIpAddress(request));
        BooleanUtil.strAnyIsBlankThrow(() -> new CustomException(ResponseEnum.VRIFY_CODE_INVALID), rightVerificationCode);
        BooleanUtil.strNotEqualsThrow(rightVerificationCode, loginVo.getVerificationCode(),
                () -> new CustomException(ResponseEnum.VRIFY_CODE_INCORRECT));
        return ResponseEntity.buildData(iUserService.login(loginVo.getUserName(), loginVo.getPassWord()));
    }

    @GetMapping("get-verification-code")
    @Operation(summary = "获取验证码")
    public void getVerificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(80, 40, 4, 4);
        String code = lineCaptcha.getCode();
        String key = SystemConstant.VERIFICATION_CODE + IpUtil.getIpAddress(request);
        iRedisService.set(key, code, 60 * 5);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        lineCaptcha.write(outputStream);
    }

    @GetMapping("authorization/get-current-user")
    public ResponseEntity<?> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.buildData(iUserService.oneUser(user.getUsername()));
    }
}

