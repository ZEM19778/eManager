package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;
import com.emanager.emanager_demo.model.Nachrichten;

import java.util.List;

public interface NachrichtenServiceIn {

    List<Nachrichten> getAllNachrichten();
    void saveNachrichten(Nachrichten nachrichten);
    Nachrichten getNachrichtenById(long id);


    void deleteNachrichtById(long id);

}