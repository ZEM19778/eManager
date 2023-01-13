package com.emanager.emanager_demo.service;


import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserServiceIn {
    void saveUser(User user);

    User getUserById(long id);

    void deleteUsereById(long id);


    @Query("SELECT d from User d where d.username like %:nutzername%")
    User finduserByMitarbeiterLike(@Param("nutzername") String nutzername);

}

