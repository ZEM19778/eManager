package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.Nachrichten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NachrichtenRepository extends JpaRepository<Nachrichten, Long> {
}
