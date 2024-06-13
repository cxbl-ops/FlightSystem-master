package com.example.Service.impl;

import cn.hutool.core.lang.Dict;
import com.example.Mapper.UserMapper;
import com.example.Service.UserService;
//import com.example.WeBaseUtil.Chain;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author vir
 * @date 2024/5/13 上午9:36 09 36
 * @package_name com.example.Service.impl
 */
@RequiredArgsConstructor
@Service
public class UserServiceimpl implements UserService {

    final UserMapper userMapper;


    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public boolean register(User user) {
        boolean existingUser = userMapper.register(user);
        return !existingUser;

    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

    @Override
    public int delUserInfo(User user) {
        return userMapper.delUserInfo(user);
    }

}
