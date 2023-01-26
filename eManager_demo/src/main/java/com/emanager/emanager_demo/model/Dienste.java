package com.emanager.emanager_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="d_dienste")
public class Dienste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="d_id")
    private Long id;

    @Column(name = "d_datum")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumvon;

    @Column(name = "d_von")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime zeitvon;

    @Column(name = "d_bis")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime zeitbis;

    @ManyToOne
    @JoinColumn(name = "b_d_id", referencedColumnName = "b_id")
    private Baustelle addresse;


    @Column(name = "d_mitarbeiter")
    private String mitarbeiter;

    @Column(name = "d_dauer")
    private float dauer;

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

    public float getDauer() {return dauer;}

    public void setDauer(float dauer) {this.dauer=dauer;}

    public String getMitarbeiter() {return mitarbeiter;}

    public void setMitarbeiter(String mitarbeiter) {this.mitarbeiter=mitarbeiter;}

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


    public Baustelle getAddresse() {
        return addresse;
    }

    public void setAddresse(Baustelle addresse) {
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