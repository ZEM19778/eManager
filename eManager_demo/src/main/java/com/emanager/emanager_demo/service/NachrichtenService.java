package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Nachrichten;
import com.emanager.emanager_demo.repository.NachrichtenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class NachrichtenService implements NachrichtenServiceIn{

    @Autowired
    private NachrichtenRepository nachrichtenRepository;

    @Override
    public List<Nachrichten> getAllNachrichten() {
        return nachrichtenRepository.findAll();
    }


    public void saveNachrichten(Nachrichten nachrichten) {
        String date=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        nachrichten.setDatumzeit(date);
        this.nachrichtenRepository.save(nachrichten);
    }

    @Override
    public Nachrichten getNachrichtenById(long id) {
        Optional<Nachrichten> optional = nachrichtenRepository.findById(id);
        Nachrichten nachrichten = null;
        if (optional.isPresent()) {
            nachrichten = optional.get();
        } else {
            throw new RuntimeException(" Nachrichten not found for id :: " + id);
        }
        return nachrichten;
    }


    @Override
    public void deleteNachrichtById(long id) {
        this.nachrichtenRepository.deleteById(id);
    }



}