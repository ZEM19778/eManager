package com.emanager.emanager_demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="m_mitarbeiter")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="m_id")
    private Long id;

    @Column(name="m_benutzername")
    private String username;
    @Column(name="m_passwort")
    private String password;
    @Column(name="m_rolle")
    private String role;

    @OneToMany
    @JoinColumn(name="d_m_id", referencedColumnName = "m_id")
    private List<Dienste> dienste = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="n_m_id", referencedColumnName = "m_id")
    private List<Nachrichten> nachrichten = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="t_m_id", referencedColumnName = "m_id")
    private List<Termin> termine = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="u_m_id", referencedColumnName = "m_id")
    private List<Urlaub> urlaube = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="u_genehmigt_m_id", referencedColumnName = "m_id")
    private List<Urlaub> genehmigt = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
