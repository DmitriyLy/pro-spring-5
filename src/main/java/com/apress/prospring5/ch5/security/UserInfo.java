package com.apress.prospring5.ch5.security;

public class UserInfo {
    private final String userName;
    private final String password;


    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
