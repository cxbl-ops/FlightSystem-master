package com.example.Service.impl;

import com.example.Mapper.UserMapper;
import com.example.Service.UserService;
import com.example.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author vir
 * @date 2024/5/13 上午9:36 09 36
 * @package_name com.example.Service.impl
 */

@Service
public class UserServiceimpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    public  UserServiceimpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public boolean register(User user) {
        boolean existingUser = userMapper.register(user);
       return  !existingUser;

    }
}
