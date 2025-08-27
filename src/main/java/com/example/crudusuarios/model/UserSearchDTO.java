package com.example.crudusuarios.model;

public class UserSearchDTO {


    private long id_user;
    private String email;
    private String state;

    public UserSearchDTO(long id_user, String email, String state) {
        this.id_user = id_user;
        this.email = email;
        this.state = state;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
