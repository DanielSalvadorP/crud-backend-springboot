package com.example.crudusuarios.repository;

import com.example.crudusuarios.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel,Long> {

    Optional<TaskModel> findById(long id);

    List<TaskModel> findByNameTaskContainingIgnoreCase(String nameTask);

    List<TaskModel> findByInfoTaskContainingIgnoreCase(String infoTask);

    List<TaskModel> findByStateTask(String stateTask);

    List<TaskModel> findByUser_IdUser(long idUser);
}
