package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermineRepository extends JpaRepository<Termin, Long> {
}
