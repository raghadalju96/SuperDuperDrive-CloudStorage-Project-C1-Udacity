package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, userName,password, userId) VALUES(#{url}, #{userName}, #{password} , #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);


    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredential(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url= #{url} , userName = #{userName}, password = #{password} WHERE credentialId = #{credentialId}")
    Credential updateCredential(Integer credentialId);
}
