package com.emanager.emanager_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name="n_nachrichten")
public class Nachrichten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="n_id")
    private Long id;

    @Column(name = "n_nachricht")
    private String nachricht;

    @Column(name = "n_sender")
    private String sender;

    @Column(name = "n_datumzeit",nullable = false, updatable = false)
    private String datumzeit;

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

    public String getDatumzeit() {
        return datumzeit;
    }

    public void setDatumzeit(String datumzeit) {
        this.datumzeit = datumzeit;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setsenderAdmin(String sender) {
        this.sender = sender;
    }
}
