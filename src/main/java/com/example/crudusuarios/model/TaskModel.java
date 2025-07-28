package com.example.crudusuarios.model;

import jakarta.persistence.*;
import com.example.crudusuarios.model.UserModel;

@Entity
@Table(name="Tasks")
public class TaskModel {

    @Id
    @GeneratedValue
    private long id_task;

    private String name_task;
    private String info_task;
    private String state_task;

    public TaskModel(long id_task, String name_task, String info_task, String state_task, UserModel id_user) {
        this.id_task = id_task;
        this.name_task = name_task;
        this.info_task = info_task;
        this.state_task = state_task;
        this.id_user = id_user;
    }

    public TaskModel() {
    }

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private UserModel id_user;

    public long getId_task() {
        return id_task;
    }

    public void setId_task(long id_task) {
        this.id_task = id_task;
    }

    public String getName_task() {
        return name_task;
    }

    public void setName_task(String name_task) {
        this.name_task = name_task;
    }

    public String getInfo_task() {
        return info_task;
    }

    public void setInfo_task(String info_task) {
        this.info_task = info_task;
    }

    public String getState_task() {
        return state_task;
    }

    public void setState_task(String state_task) {
        this.state_task = state_task;
    }

    public UserModel getId_user() {
        return id_user;
    }

    public void setId_user(UserModel id_user) {
        this.id_user = id_user;
    }
}
