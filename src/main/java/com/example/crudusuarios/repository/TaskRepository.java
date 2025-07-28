package com.example.crudusuarios.repository;

import com.example.crudusuarios.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskModel,Long> {
}
