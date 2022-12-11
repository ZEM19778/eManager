package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.Dienste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiensteRepository extends JpaRepository<Dienste, Long> {

}
