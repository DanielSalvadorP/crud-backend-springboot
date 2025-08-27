package com.example.crudusuarios.model;

import jakarta.persistence.*;
import com.example.crudusuarios.model.UserModel;

@Entity
@Table(name="Tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTask;

    @Column(name = "name_task")
    private String nameTask;

    @Column(name = "info_task")
    private String infoTask;

    @Column(name = "state_task")
    private String stateTask;

    public TaskModel(long idTask, String nameTask, String infoTask, String stateTask, UserModel idUser) {
        this.idTask = idTask;
        this.nameTask = nameTask;
        this.infoTask = infoTask;
        this.stateTask = stateTask;
        this.user = idUser;
    }

    public TaskModel() {
    }

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    public long getIdTask() {
        return idTask;
    }

    public void setIdTask(long idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getInfoTask() {
        return infoTask;
    }

    public void setInfoTask(String infoTask) {
        this.infoTask = infoTask;
    }

    public String getStateTask() {
        return stateTask;
    }

    public void setStateTask(String stateTask) {
        this.stateTask = stateTask;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
