package com.example.exception;

import com.example.vo.RespBean;
import com.example.vo.RespBeanEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


/**
 * @author chyj0
 * @date 29/5/2024 下午5:26 17 26
 * @package_name com.example.exception
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //调试日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)//处理哪些异常
    public RespBean ExceptionHandler(Exception e,HttpServletRequest request){
        //打印日志
        logger.error("Request URL : {}，Exception : {}", request.getRequestURL(), e.getMessage(),e);

        if(e instanceof GlobalException){//之前自定义的异常
            GlobalException ex = (GlobalException) e;
            return RespBean.error(ex.getRespBeanEnum());
        }else if(e instanceof BindException){ //如果异常是绑定异常(比如没有通过参数校验注解抛出的异常)
            BindException be = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常" + be.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);

    }
}
