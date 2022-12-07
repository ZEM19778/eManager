package com.emanager.emanager_demo;


public interface UserServiceIn {
    void saveUser(User user);

    User getUserById(long id);

    void deleteUsereById(long id);

}

