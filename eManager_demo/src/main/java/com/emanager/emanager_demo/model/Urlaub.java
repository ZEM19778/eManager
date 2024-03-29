package com.emanager.emanager_demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="u_urlaub")
public class Urlaub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="u_id")
    private Long id;

    @Column(name="u_beginn")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginn;

    @Column(name="u_ende")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ende;

    @Column(name="u_genehmigt")
    private String genehmigt;

    @Column(name="u_beschreibung")
    private String beschreibung;

    @Column(name = "u_beantragt_name")
    private String beantragtMitarbeiter;


    @Override
    public String toString() {
        return "Urlaub{" +
                "id=" + id +
                ", beginn=" + beginn +
                ", ende=" + ende +
                ", genehmigt=" + genehmigt +
                ", beschreibung=" + beschreibung +
                ", beantragtMitarbeiter='" + beantragtMitarbeiter + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBeginn() {
        return beginn;
    }

    public void setBeginn(LocalDate beginn) {
        this.beginn = beginn;
    }

    public LocalDate getEnde() {
        return ende;
    }

    public void setEnde(LocalDate ende) {
        this.ende = ende;
    }

    public String getGenehmigt() {
        return genehmigt;
    }

    public void setGenehmigt(String genehmigt) {
        this.genehmigt = genehmigt;
    }

    public String getBeantragtMitarbeiter() {
        return beantragtMitarbeiter;
    }

    public void setBeantragtMitarbeiter(String beantragtMitarbeiter) {
        this.beantragtMitarbeiter = beantragtMitarbeiter;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
