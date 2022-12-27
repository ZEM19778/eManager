package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.repository.DiensteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class DiensteService implements DiensteServiceIn{

    @Autowired
    private DiensteRepository diensteRepository;

   @Override
    public List<Dienste> getAllDienste() {
        return diensteRepository.findAll();
    }
    public void saveDienste(Dienste dienste) {
        this.diensteRepository.save(dienste);
    }

    @Override
    public Dienste getDiensteById(long id) {
        Optional<Dienste> optional = diensteRepository.findById(id);
        Dienste dienste = null;
        if (optional.isPresent()) {
            dienste = optional.get();
        } else {
            throw new RuntimeException(" dienste not found for id :: " + id);
        }
        return dienste;
    }

}
