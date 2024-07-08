package com.example.exception;

import com.example.vo.RespBeanEnum;

/**
 * @author virgo
 * @date 2024/7/8 上午11:05 11 05
 * @package_name com.example.exception
 * @Project_name Plane
 */
public class NoLogException extends RuntimeException {
    private RespBeanEnum respBeanEnum;

    public NoLogException(RespBeanEnum respBeanEnum) {
        super(respBeanEnum.getMessage());
        this.respBeanEnum = respBeanEnum;
    }

    public RespBeanEnum getRespBeanEnum() {
        return respBeanEnum;
    }
}
