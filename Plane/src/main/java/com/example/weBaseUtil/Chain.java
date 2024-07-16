package com.example.weBaseUtil;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONArray;
import com.example.service.UserService;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;

/**
 * @author chyj0
 * @date 2024/6/11 上午9:35 09 35
 * @package_name com.example.WeBaseUtil
 * &#064;Project_name  FlightSystem-master
 */
@RequiredArgsConstructor
public class Chain {
    final UserService userService;


    public static Dict addUser(User user) {
        JSONArray _UserInfo = new JSONArray();
        _UserInfo.put(user.getUsername());
        _UserInfo.put(user.getAccount());
        _UserInfo.put(user.getPasswd());
        System.out.println("id:" + user.getId());
        return api.LocalSign("user",
                "0x523d6f265b82fb162f748fe30aa32f720127b76e",
                "adduser",
                _UserInfo,
                "[{\"constant\":false,\"inputs\":[{\"name\":\"_userName\",\"type\":\"string\"},{\"name\":\"_account\",\"type\":\"address\"},{\"name\":\"_passwd\",\"type\":\"string\"}],\"name\":\"adduser\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"_id\",\"type\":\"int256\"}],\"name\":\"getuser\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"address\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"name\":\"users\",\"outputs\":[{\"name\":\"id\",\"type\":\"int256\"},{\"name\":\"userName\",\"type\":\"string\"},{\"name\":\"account\",\"type\":\"address\"},{\"name\":\"passwd\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"id\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]");
    }
}
