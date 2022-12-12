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

    @Column(name = "datumvon")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumvon;
    @Column(name = "datumbis")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumbis;
    @Column(name = "addresse")
    private String addresse;

    @Column(name = "zeitvon")
    private Time zeitvon;

    @Column(name = "zeitbis")
    private Time zeitbis;

    @Override
    public String toString() {
        return "Dienste{" +
                "id=" + id +
                ", datumvon=" + datumvon +
                ", datumbis=" + datumbis +
                ", addresse='" + addresse + '\'' +
                ", zeitvon=" + zeitvon +
                ", zeitbis=" + zeitbis +
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

    public void setDatumvon(Date datumvon) {
        this.datumvon = datumvon;
    }

    public Date getDatumbis() {
        return datumbis;
    }

    public void setDatumbis(Date datumbis) {
        this.datumbis = datumbis;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public Time getZeitvon() {
        return zeitvon;
    }

    public void setZeitvon(Time zeitvon) {
        this.zeitvon = zeitvon;
    }

    public Time getZeitbis() {
        return zeitbis;
    }

    public void setZeitbis(Time zeitbis) {
        this.zeitbis = zeitbis;
    }
}

