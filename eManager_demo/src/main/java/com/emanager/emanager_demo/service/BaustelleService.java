package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Baustelle;
import com.emanager.emanager_demo.model.Termin;
import com.emanager.emanager_demo.repository.BaustelleRepository;
import com.emanager.emanager_demo.repository.TermineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaustelleService implements BaustelleServiceIn{


    @Autowired
    private BaustelleRepository baustelleRepository;

    @Override
    public List<Baustelle> getAllBaustelle() {
        return baustelleRepository.findAll();
    }
    public void saveBaustelle(Baustelle baustelle) {
        this.baustelleRepository.save(baustelle);
    }

    @Override
    public void deleteBaustelleById(long id) {
        this.baustelleRepository.deleteById(id);
    }


}
