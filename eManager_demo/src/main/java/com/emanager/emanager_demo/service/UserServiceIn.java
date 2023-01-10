package com.emanager.emanager_demo.service;


import com.emanager.emanager_demo.model.User;

public interface UserServiceIn {
    void saveUser(User user);

    User getUserById(long id);

    void deleteUsereById(long id);

    public abstract void updateUser(User user);

}

