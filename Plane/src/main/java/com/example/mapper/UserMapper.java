package com.example.mapper;

import com.example.vo.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

/**
 * @author vir
 * @date 2024/5/13 上午9:39 09 39
 * @package_name com.example.Mapper
 */
@Mapper
public interface UserMapper {


    @Select("SELECT * FROM tb_user WHERE username = #{username} AND account = #{account} AND passwd = #{passwd}")
   User selectUserInfo(User user);

    @Insert("insert into tb_user(username,account,passwd) values (#{username},#{account},#{passwd})")
   int addUserInfo(User user);

    @Update("update tb_user set username=#{username},account=#{account},passwd=#{passwd} where id=#{id}")
   int updateUserInfo(User user);

    @Delete("delete from tb_user where id=#{id}")
   int delUserInfo(User user);

    @Select(" select * from tb_user where username=#{username} LIMIT 1")
    Optional<User> findUserByUsername(String username);

    @Select("select * from tb_user where account=#{account} LIMIT 1")
    Optional<User> findUserByAccount(String account);



}
