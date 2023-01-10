package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.User;
import com.emanager.emanager_demo.UserNotFoundException;
import com.emanager.emanager_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceIn {
    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }


    public void saveUser(User user) {
        this.repo.save(user);
    }

    public void updateUser (User user){
        repo.save(user);
    }




    public User get(Long id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Konnte den User mit dier ID nicht finden" + id);

    }

    @Override
    public User getUserById(long id) {
        Optional<User> optional = repo.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for id :: " + id);
        }
        return user;
    }

    @Override
    public void deleteUsereById(long id) {
        this.repo.deleteById(id);
    }




}

