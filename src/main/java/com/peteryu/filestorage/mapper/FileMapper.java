package com.peteryu.filestorage.mapper;

import com.peteryu.filestorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("select * from files where userid = #{userId} and filename = #{fileName}")
    File getFile(int  userId, String fileName);

    @Select("select * from files where userid = #{userId}")
    List<File> getFiles(int userId);

    @Insert("insert into files(filename, contenttype, filesize, userid, filedata) " +
            "values(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "key")
    int insert(File file);

    @Delete("delete from files where filename = #{fileName} and userid = #{userId}")
    int delete(int userId, String fileName);
}
