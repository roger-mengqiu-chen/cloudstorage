package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
    @Results({
         @Result(id = true, property = "credentialId", column = "credentialid")
         , @Result (property = "url", column = "url")
         , @Result (property = "username", column = "username")
         , @Result (property = "key", column = "key")
         , @Result (property = "password", column = "password")
         , @Result (property = "userId", column = "userid")
    })
    List<Credential> getCredentialOfUser(int userid);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{id}")
    @Results({
            @Result(id = true, property = "credentialId", column = "credentialid")
            , @Result (property = "url", column = "url")
            , @Result (property = "username", column = "username")
            , @Result (property = "key", column = "key")
            , @Result (property = "password", column = "password")
            , @Result (property = "userId", column = "userid")
    })
    Credential getCredentialById(int id);

    @Insert("INSERT INTO CREDENTIALS " +
            "(url, username, key, password, userid)" +
            "VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    int addCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{id}")
    int deleteCredentialById(int id);

    @Update("UPDATE CREDENTIALS" +
            "SET url = #{url}, username = #{username}, key = #{key}, password = #{password}" +
            "WHERE credentialid = #{credentialId}")
    int updateCredential(Integer credentialId, String url, String username, String key, String password);
}
