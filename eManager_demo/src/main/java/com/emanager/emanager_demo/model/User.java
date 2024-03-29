package com.emanager.emanager_demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="m_mitarbeiter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="m_id")
    private Long id;

    @Column(name = "m_vorname")
    private String vorname;

    @Column(name="m_benutzername")
    private String username;
    @Column(name="m_passwort")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="m_rolle")
    private Role role;

    public User() {}

    public User(String username, String password, Role role, String vorname) {
        this.username = username;
        this.password = password;
        this.vorname = vorname;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
}

