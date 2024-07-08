package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author vir
 * @date 2024/5/13 上午9:37 09 37
 * @package_name com.example.vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int id;

    @NotEmpty
    @Email(message = "用户名必须是电子邮箱格式")
    private String username;

    @NotEmpty
    @Size(min = 42,message = "账户地址必须是“0x”开头,必须是42位")
    private String account;

    @NotEmpty
    @Length(min = 6,message = "密码必须是6位")
    private String passwd;
}
