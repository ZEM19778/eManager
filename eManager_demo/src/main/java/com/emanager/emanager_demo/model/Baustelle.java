package com.emanager.emanager_demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "b_baustelle")
public class Baustelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="b_id")
    private Long id;

    @Column (name="b_bezeichnung")
    private String bezeichung;
}
