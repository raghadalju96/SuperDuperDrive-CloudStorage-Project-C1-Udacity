package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username,password, userid) VALUES(#{url}, #{username}, #{password} , #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insertCredential(Credential credential);


    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    Credential getCredential(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
    void deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url= #{url} , username = #{username}, password = #{password} WHERE credentialid = #{credentialid}")
    Credential updateCredential(Integer credentialId);
}
