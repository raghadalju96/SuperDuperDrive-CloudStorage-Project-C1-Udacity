package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper userMapper;

    private  HashService hashService;

    public UserService(UserMapper userMapper,  HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return this.userMapper.getUser(username) == null;
    }

    public int createUser(User user){

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodeSalt = Base64.getEncoder().encodeToString(salt);
        String hashPassword = hashService.getHashedValue(user.getPassword(), encodeSalt);

        return this.userMapper.insert(new User(null,user.getUsername(),encodeSalt,hashPassword,user.getFirstName(), user.getLastName()));

    }

    public User getUser(String username){
        return  this.userMapper.getUser(username);
    }
}
