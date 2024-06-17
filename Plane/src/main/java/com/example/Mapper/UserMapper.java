package com.example.Mapper;

import com.example.vo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vir
 * @date 2024/5/13 上午9:39 09 39
 * @package_name com.example.Mapper
 */
@Mapper
public interface UserMapper {
   User login(User user);
   int register(User user);
   int updateUserInfo(User user);
   int delUserInfo(User user);
}
