package com.peteryu.filestorage.model;

import com.peteryu.filestorage.annotation.ValidPassword;

public class User {
    private Integer userId;
    private String userName;
    private String salt;

    @ValidPassword
    private String passWord;

    private String firstName;
    private String lastName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
