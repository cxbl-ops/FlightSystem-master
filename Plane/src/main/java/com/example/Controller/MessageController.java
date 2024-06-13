package com.example.Controller;

import cn.hutool.core.util.RandomUtil;
import com.example.Service.MessageService;
import com.example.vo.Code;
import com.example.vo.EmailMessage;
import com.example.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
//@CrossOrigin(origins = "http://localhost:8080")
public class MessageController {


    @Resource
    MessageService messageService;

    //*不用redis可以删去该自动装配
    @Resource
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("sendEmail/{getPeople}")
    public ResponseResult sendSimpleMail(@PathVariable("getPeople") String getPeople) {

        System.out.println(getPeople);

        //从Redis中获取code
        String RandomMath = redisTemplate.opsForValue().get(getPeople);
        System.out.println("redis根据输入的邮箱查询库中是否存在验证码，返回值:" + RandomMath);
        if (StringUtils.hasText(RandomMath)) {  //验证码还未过期，在库中，可以直接登录 这里为什么要加一个！导致直接逻辑短路
            log.info("应当结束");
            return new ResponseResult(Code.EMAIL_SEND_NONO, "验证码还未过期，请输入", RandomMath);
        } else if (RandomMath ==null || RandomMath ==""){
            //验证码过期或者第一次登录生成验证码
            RandomMath = RandomUtil.randomNumbers(6);
            System.out.println("随机验证码为" + RandomMath);
            // 构建一个邮件对象
            messageService.sendMail(RandomMath, getPeople);
            //设置有效时间,这里是30秒，
            redisTemplate.opsForValue().set(getPeople, RandomMath, 30, TimeUnit.SECONDS);
            return new ResponseResult(Code.EMAIL_SEND_OK, "验证码发送成功，60秒之后失效", RandomMath);

        }

        return new ResponseResult<>(Code.EMAIL_SEND_ERR, "验证码发送失败", RandomMath);
    }

    @PostMapping("/registerE")
    public ResponseResult registerE(@RequestBody EmailMessage emailMessage) {
        System.out.println("前端传递的邮箱号:" + emailMessage.getEmail());
        System.out.println("前端传递的验证码:" + emailMessage.getMath());
        //查询redis数据库中根据邮箱号查询验证码
        String s = redisTemplate.opsForValue().get(emailMessage.getEmail());
        if (emailMessage.getMath().equals(s)) {   //输入的验证码和redis数据库中生成的验证码相同时
            return new ResponseResult<>(Code.EMAIL_LOGIN_OK, "发送成功", s);
        }
        return new ResponseResult<>(Code.EMAIL_LOGIN_ERR, "验证码不一致", null);
    }


}
