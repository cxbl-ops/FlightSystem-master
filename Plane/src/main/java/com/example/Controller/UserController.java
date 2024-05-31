package com.example.Controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONArray;
import com.example.Service.UserService;
import com.example.WeBaseUtil.api;
import com.example.vo.ResponseResult;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RequiredArgsConstructor
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    final UserService userService;
//todo 登录
    @PostMapping("/login")
    public ResponseResult<User> login(@RequestBody User user) {
        // 获取用户输入的用户名和密码
        String username = user.getUsername();
        String password = user.getPasswd();
        // 从数据库中获取用户信息
        if (username == null && password == null) {
            // 用户不存在
            return new ResponseResult<>(400, "用户名或密码不正确");
        }

        // 获取数据库中存储的哈希密码
        String hashedPassword = user.getPasswd();

        // 使用 BCryptPasswordEncoder 对用户输入的密码进行哈希验证
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, hashedPassword)) {
            System.out.println("password：" + password);
            System.out.println("hashedPassword：" + hashedPassword);



            return new ResponseResult<>(400, "用户名或密码错误");
        } else {
            
            return new ResponseResult<>(200, "登录成功");
        }
    }

//todo 注册
    @PostMapping("/register")
    public ResponseResult<User> register(@RequestBody @Valid User user) {
        // 对密码进行哈希操作

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(user.getPasswd());

        System.out.println("密码：" + user.getPasswd());
        JSONArray jsonArray = new JSONArray();
//        jsonArray.put(user.getId());
        jsonArray.put(user.getUsername());
        jsonArray.put(user.getAccount());
        jsonArray.put(user.getPasswd());
        System.out.println("id:" + user.getId());
        Dict getResult = api.LocalSign("user", "0xb70c66cfada8ad29088f4d099f56d0e94b2a5272", "adduser", jsonArray,

                "[{\"constant\":false,\"inputs\":[{\"name\":\"_userName\",\"type\":\"string\"},{\"name\":\"_account\",\"type\":\"address\"},{\"name\":\"_passwd\",\"type\":\"string\"}],\"name\":\"adduser\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"int256\"}],\"name\":\"getuser\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"name\":\"users\",\"outputs\":[{\"name\":\"id\",\"type\":\"int256\"},{\"name\":\"userName\",\"type\":\"string\"},{\"name\":\"account\",\"type\":\"address\"},{\"name\":\"passwd\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"id\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]");

        System.out.println("getResult："+ getResult);

//        return new  ResponseResult<>(null,"getResult:"+getResult);

       user.setPasswd(hashedPassword);

        System.out.println("hash：" + hashedPassword);


        if (userService.register(user)) {
            return new ResponseResult<>(400, "注册失败用户已有");
        }
        return new ResponseResult<>(200, "注册成功");

    }


    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

}
