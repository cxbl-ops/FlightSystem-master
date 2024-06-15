package com.example.Controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONArray;
import com.example.Service.UserService;
import com.example.WeBaseUtil.api;
import com.example.vo.ResponseResult;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
        // 获取用户输入的用户名和密码
//        String username = user.getUsername();
//        String password = user.getPasswd();
//        // 从数据库中获取用户信息
//        if (username == null && password == null) {
//            // 用户不存在
//            return new ResponseResult<>(400, "用户名或密码不能为空");
//        }
//
//        // 获取数据库中存储的哈希密码
//        String hashedPassword = user.getPasswd();
//
//        // 使用 BCryptPasswordEncoder 对用户输入的密码进行哈希验证
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (encoder.matches(password, hashedPassword)) { //我其实也不知道为什么但是取反就完了
//            System.out.println("password：" + password);
//            System.out.println("hashedPassword：" + hashedPassword);

//            return new ResponseResult<>(200, "登录成功");
//        } else {
//            return new ResponseResult<>(400, "用户名或密码错误");
//        }
        userService.login(user);
        return new ResponseResult<>(200, "登录成功");
    }

    // 注册
    @PostMapping("/register")
    public ResponseResult<User> register(@RequestBody @Valid User user) {
        log.info("进行注册");
        // 对密码进行哈希操作

    log.info("用户信息：{}", user);
        // 保存用户到数据库或者其他数据存储中
        userService.register(user);
        return new ResponseResult<>(200, "注册成功");
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
