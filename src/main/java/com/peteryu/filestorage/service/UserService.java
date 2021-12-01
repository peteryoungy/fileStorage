package com.peteryu.filestorage.service;

import com.peteryu.filestorage.mapper.UserMapper;
import com.peteryu.filestorage.model.User;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Random;

@Service
public class UserService {

    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }


    public boolean isDuplicate(String username){

        return userMapper.getUser(username) != null;
    }

    public int insertUser(User user){

        return userMapper.insert(user);
    }

    public void hashUserPassword(User user){
        Random random = new Random();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassWord(), encodedSalt);

        user.setSalt(encodedSalt);
        user.setPassWord(hashedPassword);
    }

    public User getUser(String userName){

        return userMapper.getUser(userName);
    }

    public String PasswordValid(User user){
        String password = user.getPassWord();

        if(password.length() == 0){
            return "password cannot be null";
        }
        if(password.length() < 8){
            return "password must be longer than 8";
        }

        return "";
    }
}
