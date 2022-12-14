package com.emanager.emanager_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
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

    @Column(name = "addresse")
    private String addresse;

    @Column(name = "zeitvon")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime zeitvon;

    @Column(name = "zeitbis")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime zeitbis;

    @Override
    public String toString() {
        return "Dienste{" +
                "id=" + id +
                ", datumvon=" + datumvon +
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


    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public LocalTime getZeitvon() {
        return zeitvon;
    }

    public void setZeitvon(LocalTime zeitvon) {
        this.zeitvon = zeitvon;
    }

    public LocalTime getZeitbis() {
        return zeitbis;
    }

    public void setZeitbis(LocalTime zeitbis) {
        this.zeitbis = zeitbis;
    }
}