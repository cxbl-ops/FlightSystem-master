package com.example.Service;

import com.example.vo.User;

/**
 * @author vir
 * @date 2024/5/13 上午9:36 09 36
 * @package_name com.example.Service
 */
public interface UserService {
   User selectUserInfo(User user);
   int addUserInfo(User user);
   int updateUserInfo(User user);
   int delUserInfo(User user);
   boolean login(String username, String account,String passwd);
}
