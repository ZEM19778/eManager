package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Termin;
import com.emanager.emanager_demo.repository.TermineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class TermineService implements TermineServiceIn{

    @Autowired
    private TermineRepository termineRepository;

    @Override
    public List<Termin> getAllTermine() {
        return termineRepository.findAll();
    }
    public void saveTermin(Termin termin) {
        this.termineRepository.save(termin);
    }

    @Override
    public void deleteTerminById(long id) {
        this.termineRepository.deleteById(id);
    }

    @Override
    public Termin getTerminById(long id) {
        Optional<Termin> optional = termineRepository.findById(id);
        Termin termin = null;
        if (optional.isPresent()) {
            termin = optional.get();
        } else {
            throw new RuntimeException(" termine not found for id :: " + id);
        }
        return termin;
    }



}