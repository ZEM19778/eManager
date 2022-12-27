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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datum;

    @Column(name="t_beginn")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime beginn;

    @Column(name="t_ende")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime ende;
}
