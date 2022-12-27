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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date beginn;

    @Column(name="u_ende")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date ende;

    @Column(name="u_genehmigt")
    private Boolean genehmigt;

}
