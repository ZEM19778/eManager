package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Termin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TermineServiceIn {

    List<Termin> getAllTermine();
    void saveTermin(Termin termin);
    Termin getTerminById(long id);

    void deleteTerminById(long id);

}
