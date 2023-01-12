package com.udacity.jwdnd.course1.cloudstorage.mappers;


import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findById(Integer fileId);


    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFiles(Integer userId);


    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);


    @Select("SELECT * FROM FILES WHERE fileName = #{fileName}")
    File getFile(String fileName);

}
