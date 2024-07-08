package com.example.Controller;

import com.example.Service.UserService;
import com.example.vo.ResponseResult;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    final UserService userService;


    // 登录
    @PostMapping("/login")
    public ResponseResult<User> login(@RequestBody User user) {
        log.info("进行登录");
        System.out.println(user);
        try {
            userService.selectUserInfo(user);
            // 登录成功
            return new ResponseResult(200, "登录成功");
        } catch (IllegalArgumentException e) {
            System.out.println("e:"+e);
            // 用户名不存在或密码错误
            return new ResponseResult(400, "用户名不存在或密码错误");
        } catch (Exception e) {
            // 其他异常情况
            return new ResponseResult(500, "登录失败");
        }
    }

    // 注册
    @PostMapping("/register")
    public ResponseResult register( @RequestBody  User user) {
        int resultCode = userService.addUserInfo(user);
        log.info("user:{}",user);
        switch (resultCode) {
            case 200:
                return new ResponseResult<>(200, "注册成功");
            case 400:
                return new ResponseResult<>(400, "用户名或账号已存在");
            default:
                return new ResponseResult<>(400, "注册失败");
        }

    }




        @PostMapping("/updateUserInfo")
public ResponseResult<User> updateUserInfo(@RequestBody User user) {
    if (userService.updateUserInfo(user) == 0) {
        return new ResponseResult<>(400, "更新失败");
    }
    return new ResponseResult<>(200, "更新成功");
}

@PostMapping("/delUserInfo")
public ResponseResult<User> delUserInfo(@RequestBody User user) {
    if (userService.delUserInfo(user) == 0) {
        return new ResponseResult<>(400, "删除失败");
    }
    return new ResponseResult<>(200, "删除成功");
}
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
