package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.Urlaub;
import com.emanager.emanager_demo.repository.UrlaubeRepository;
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
public class UrlaubService implements UrlaubServiceIn{

    @Autowired
    private UrlaubeRepository urlaubeRepository;

    @Override
    public List<Urlaub> getAllUrlaub() {
        return urlaubeRepository.findAll();
    }
    public void saveUrlaub(Urlaub urlaub) {
        this.urlaubeRepository.save(urlaub);
    }

    @Override
    public void deleteUrlaubById(long id) {
        this.urlaubeRepository.deleteById(id);
    }

    @Override
    public Urlaub getUrlaubById(long id) {
        Optional<Urlaub> optional = urlaubeRepository.findById(id);
        Urlaub urlaub = null;
        if (optional.isPresent()) {
            urlaub = optional.get();
        } else {
            throw new RuntimeException(" urlaub not found for id :: " + id);
        }
        return urlaub;
    }

    @Override
    public List<Urlaub> findUrlaubByMitarbeiterLike(String nutzername) {
        return urlaubeRepository.findUrlaubByBeantragtMitarbeiter(nutzername);
    }



}