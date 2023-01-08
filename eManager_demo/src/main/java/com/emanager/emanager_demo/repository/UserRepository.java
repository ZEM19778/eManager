package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
     public User findByUsername(String username);
}

