package com.emanager.emanager_demo.repository;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.Urlaub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlaubeRepository extends JpaRepository<Urlaub, Long> {

    public List<Urlaub> findUrlaubByBeantragtMitarbeiter(String beantragtMitarbeiter);
}
