package com.emanager.emanager_demo.model;


import com.emanager.emanager_demo.repository.BaustelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BaustelleConverter implements Converter<Long,Baustelle> {
    @Autowired
    private BaustelleRepository repository;
    @Override
    public Baustelle convert(Long source) {
        return repository.findById(source).get();
    }
}