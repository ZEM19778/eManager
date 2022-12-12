package com.emanager.emanager_demo.service;

import com.emanager.emanager_demo.model.Dienste;

import java.util.List;

public interface DiensteServiceIn {

    List<Dienste> getAllDienste();
    void saveDienste(Dienste dienste);
    Dienste getDiensteById(long id);
}
