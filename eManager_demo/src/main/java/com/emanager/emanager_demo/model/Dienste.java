package com.emanager.emanager_demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.util.Date;

@Entity
public class Dienste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datum_von;
    private Date datum_bis;
    private String addresse;
    private Time zeit_von;
    private Time zeit_bis;

    @Override
    public String toString() {
        return "Dienste{" +
                "id=" + id +
                ", datum_von=" + datum_von +
                ", datum_bis=" + datum_bis +
                ", addresse='" + addresse + '\'' +
                ", zeit_von=" + zeit_von +
                ", zeit_bis=" + zeit_bis +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum_von() {
        return datum_von;
    }

    public void setDatum_von(Date datum_von) {
        this.datum_von = datum_von;
    }

    public Date getDatum_bis() {
        return datum_bis;
    }

    public void setDatum_bis(Date datum_bis) {
        this.datum_bis = datum_bis;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public Time getZeit_von() {
        return zeit_von;
    }

    public void setZeit_von(Time zeit_von) {
        this.zeit_von = zeit_von;
    }

    public Time getZeit_bis() {
        return zeit_bis;
    }

    public void setZeit_bis(Time zeit_bis) {
        this.zeit_bis = zeit_bis;
    }
}

