package com.shaber.bookmanager.mapper;

import com.shaber.bookmanager.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    //查
    //查询所有用户
    @Select("select * from tb_user")
    List<User> selectAllUsers();
    //查询所有用户（分页）
    @Select("select * from tb_user limit #{start},#{size}")
    List<User> selectAllUserPage(Map<String, Integer> map);
    //通过uid查询
    @Select("select * from tb_user where uid = #{uid}")
    User existsUid(String uid);
    //通过username查询
    @Select("select * from tb_user where username = #{username}")
    User existsUsername(String username);
    //用户username与password的对比
    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User existsUsernameAndPassword(String username, String password);

    //增
    //用户注册（添加用户）
    @Insert("insert into tb_user values(#{uid},#{username},#{password},#{img})")
    int register(Map<String, Object> map);

    //删
    //用户注销
    @Delete("delete from tb_user where uid = #{uid}")
    int deleteUser(String uid);

    //改
    //用户密码修改
    @Update("update tb_user set password = #{password} where username = #{username}")
    int updatePassword(String username, String password);
    //用户名修改
    @Update("update tb_user set username = #{newUsername} where username = #{username}")
    int updateUsername(@Param("username") String username,@Param("newUsername") String newUsername);
}
