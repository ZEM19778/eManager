package com.emanager.emanager_demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="dienste")
public class Dienste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="datumvon")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumvon;

    @Column(name="datumbis")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumbis;

    @Column(name="addresse")

    private String addresse;

    @Column(name="zeit_von")
    private Time zeit_von;

    @Column(name="zeit_bis")

    private Time zeit_bis;

    @Override
    public String toString() {
        return "Dienste{" +
                "id=" + id +
                ", datum_von=" + datumvon +
                ", datum_bis=" + datumbis +
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

    public Date getDatumvon() {
        return datumvon;
    }

    public void setDatumvon(Date datum_von) {
        this.datumvon = datum_von;
    }

    public Date getDatumbis() {
        return datumbis;
    }

    public void setDatumbis(Date datum_bis) {
        this.datumbis = datum_bis;
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

