package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiensteServiceIn {

    List<Dienste> getAllDienste();
    void saveDienste(Dienste dienste);
    Dienste getDiensteById(long id);

    @Query("SELECT d from Dienste d where d.mitarbeiter like %:nutzername%")
    List<Dienste> findDiensteByMitarbeiterLike(@Param("nutzername") String nutzername);
}
