package com.example.crudusuarios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Infouser")
public class UserModel {

    @Id
    @Column(name="id_user")
    private long id_user;

    private String first_name;
    private String last_name;
    private String email;
    private String state;

    //Getters y Setters


    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
