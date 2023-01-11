package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.Dienste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiensteRepository extends JpaRepository<Dienste, Long> {
    public List<Dienste> findDiensteByMitarbeiter(String mitarbeiterName);
}
