package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.Urlaub;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UrlaubServiceIn {

    List<Urlaub> getAllUrlaub();
    void saveUrlaub(Urlaub urlaub);
    Urlaub getUrlaubById(long id);

    void deleteUrlaubById(long id);


    @Query("SELECT u from Urlaub u where u.beantragtMitarbeiter like %:nutzername%")
    List<Urlaub> findUrlaubByMitarbeiterLike(@Param("nutzername") String nutzername);
}
