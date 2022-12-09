package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
