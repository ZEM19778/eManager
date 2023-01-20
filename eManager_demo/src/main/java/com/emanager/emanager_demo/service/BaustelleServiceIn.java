package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Baustelle;
import com.emanager.emanager_demo.model.Termin;

import java.util.List;

public interface BaustelleServiceIn {

    List<Baustelle> getAllBaustelle();
    void saveBaustelle(Baustelle baustelle);

    void deleteBaustelleById(long id);
}
