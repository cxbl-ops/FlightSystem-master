package com.example.vo;

import lombok.Getter;
import lombok.ToString;

/**
 * @author chyj0
 * @date 29/5/2024 下午5:27 17 27
 * @package_name com.example.vo
 */
@Getter
@ToString
//todo 异常枚举类
public enum RespBeanEnum {
    SUCCESS(200,"成功"),
    ERROR(500,"失败"),
    LOGIN_ERROR(500210,"用户名或密码错误"),
    LOGIN_OK(200211,"登录成功"),
    LOGIN_ERR(500211,"登录失败"),
    EMAIL_SEND_OK(200212,"邮箱发送成功"),
    EMAIL_SEND_ERR(500213,"邮箱发送失败"),
    BIND_ERROR(500214,"参数校验异常" ),
    USER_ALREADY_EXISTS(500215,"用户名已存在"),
    USER_ACCOUNT_EXISTS(500216,"账户已存在"),
    USER_LOGIN_FAILED(500217,"登陆失败"),
    USER_NOT_FOUND(500218,"用户不见了");






    private final Integer code;
    private final String message;

    RespBeanEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
