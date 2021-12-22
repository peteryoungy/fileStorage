package com.peteryu.filestorage.mapper;

import com.peteryu.filestorage.model.User;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Select("select * from users where username = #{userName}")
    User getUser(String userName);

    @Insert("insert into users(username, firstname, lastname, password, salt) " +
            "values(#{userName}, #{firstName}, #{lastName}, #{passWord}, #{salt})")
    @Options(useGeneratedKeys= true, keyProperty="userId")
    int insert(User user);

}
