package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.Urlaub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlaubeRepository extends JpaRepository<Urlaub, Long> {
}
