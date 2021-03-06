package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES " +
            "(filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Delete("DELETE FROM FILES " +
            "WHERE fileId = #{id}")
    int deleteFile(int id);

    @Select("SELECT * FROM FILES WHERE fileId = #{id}")
    @Results ({
            @Result(id = true, property = "fileId", column = "fileId"),
            @Result(property = "fileName", column = "filename"),
            @Result(property = "contentType", column = "contenttype"),
            @Result(property = "fileSize", column = "filesize"),
            @Result(property = "userId", column = "userid"),
            @Result(property = "fileData", column = "filedata")
    })
    File getFile(int id);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    @Results ({
            @Result(id = true, property = "fileId", column = "fileId"),
            @Result(property = "fileName", column = "filename"),
            @Result(property = "contentType", column = "contenttype"),
            @Result(property = "fileSize", column = "filesize"),
            @Result(property = "userId", column = "userid"),
            @Result(property = "fileData", column = "filedata")
    })
    List<File> getAllFiles(int userId);
}
