package com.emanager.emanager_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name="nachrichten")
public class Nachrichten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nachricht")
    private String nachricht;



    @Column(name = "datumzeit",nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime datumzeit;

    @Override
    public String toString() {
        return "Nachrichten{" +
                "id=" + id +
                ", nachricht='" + nachricht + '\'' +
                ", datumzeit=" + datumzeit +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public LocalDateTime getDatumzeit() {
        return datumzeit;
    }

    public void setDatumzeit(LocalDateTime datumzeit) {
        this.datumzeit = datumzeit;
    }
}
