package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Termin;
import com.emanager.emanager_demo.repository.TermineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Override
    public List<Termin> getTermineInSpan(LocalDate start, LocalDate ende){
        Integer i = 0;
        List<Termin> alleTermine = termineRepository.findAll();
        List<Termin> gefilterteTermine = new ArrayList<>();
        for (Termin termin: alleTermine) {
            boolean frueher = termin.getDatum().isBefore(start);
            boolean spaeter = termin.getDatum().isAfter(ende);
            if(!frueher && !spaeter){
                gefilterteTermine.add(termin);
            }
        }
        return gefilterteTermine;
    }





}