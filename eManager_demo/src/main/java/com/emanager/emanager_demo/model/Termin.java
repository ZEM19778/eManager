package com.emanager.emanager_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="t_termine")
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="t_id")
    private Long id;

    @Column(name="t_beschreibung")
    private String beschreibung;

    @Column(name="t_datum")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;

    @Column(name="t_beginn")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime beginn;

    @Column(name="t_ende")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime ende;

    @Column(name = "t_betrifft")
    private String betrifft;




    @Override
    public String toString() {
        return "Termin{" +
                "id=" + id +
                ", beschreibung='" + beschreibung + '\'' +
                ", datum=" + datum +
                ", beginn=" + beginn +
                ", ende=" + ende +
                ", betrifft='" + betrifft + '\'' +

                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getBeginn() {
        return beginn;
    }

    public void setBeginn(LocalTime beginn) {
        this.beginn = beginn;
    }

    public LocalTime getEnde() {
        return ende;
    }

    public void setEnde(LocalTime ende) {
        this.ende = ende;
    }

    public String getBetrifft() {
        return betrifft;
    }

    public void setBetrifft(String betrifft) {
        this.betrifft = betrifft;
    }


}
