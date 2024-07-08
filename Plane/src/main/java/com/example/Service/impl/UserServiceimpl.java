package com.example.Service.impl;

import com.example.Mapper.UserMapper;
import com.example.Service.UserService;
import com.example.exception.NoLogException;
import com.example.until.HashUtil;
import com.example.vo.ResponseResult;
import com.example.vo.User;
import com.example.vo.RespBeanEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceimpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User selectUserInfo(User user) {
        return userMapper.findUserByUsername(user.getUsername()).orElse(null);
    }

    @Override
    public int addUserInfo(User user) {
        // 检查用户名和账户地址是否已存在
        userMapper.findUserByUsername(user.getUsername())
                .ifPresent(u -> {throw new NoLogException(RespBeanEnum.USER_ALREADY_EXISTS);});
        userMapper.findUserByAccount(user.getAccount())
                .ifPresent(u -> {throw new NoLogException(RespBeanEnum.USER_ACCOUNT_EXISTS);});

        // 对密码进行哈希加密
        String hashedPassword = HashUtil.hashPassword(user.getPasswd());
        user.setPasswd(hashedPassword);
        // 添加新用户
        int result = userMapper.addUserInfo(user);
        return result > 0 ? new ResponseResult<>(200, "注册成功").getCode() : new ResponseResult<>(400, "注册失败").getCode();
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

    @Override
    public int delUserInfo(User user) {
        // 假设 delUserInfo 方法接受 User 对象，否则需要修改为 int id 参数
        return userMapper.delUserInfo(user);
    }

    @Override
    public boolean login(String username, String account, String passwd) {
        // 根据用户名查找用户
        Optional<User> userByUsername = userMapper.findUserByUsername(username);
        if (userByUsername.isPresent()) {
            return HashUtil.verifyPassword(passwd, userByUsername.get().getPasswd());
        }

        // 如果未找到，则尝试根据账户查找用户
        Optional<User> userByAccount = userMapper.findUserByAccount(account);
        return userByAccount.map(u -> HashUtil.verifyPassword(passwd, u.getPasswd())).orElse(false);
    }
}
