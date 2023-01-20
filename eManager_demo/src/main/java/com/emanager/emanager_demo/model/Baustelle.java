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
    private String bezeichnung;


    @Override
    public String toString() {
        return "Baustelle{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
