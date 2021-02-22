package com.mk.blog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author MK
 */
@Data
public class LoginVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$", message = "用户名需要字母开头，长度在5-16之间，允许字母数字下划线")
    private String userName;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$", message = "密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-10之间")
    private String passWord;

    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空")
    @Size(max = 4, min = 4, message = "验证码必须为4位数字和字母的组合")
    private String verificationCode;
}
