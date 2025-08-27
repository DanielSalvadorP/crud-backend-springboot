package com.example.crudusuarios.Service;

import com.example.crudusuarios.model.TaskModel;
import com.example.crudusuarios.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //CRUD
    //Read
    public List<TaskModel> getAllTasks(){
        return taskRepository.findAll();
    }

    //Create - Update
    public TaskModel crateTask(TaskModel task){
        if (task.getNameTask()==null || task.getNameTask().isBlank()){
            throw new IllegalArgumentException("El titulo de la tarea no puede ser vacío");
        }
        return taskRepository.save(task);
    }
    //Read
    //buscar por id
    public Optional<TaskModel> findTaskById(long id){
        return taskRepository.findById(id);
    }

    //buscar por titulo
    public List<TaskModel> findTaskByName(String nameTask){
        return taskRepository.findByNameTaskContainingIgnoreCase(nameTask);
    }

    //buscar por contenido
    public List<TaskModel> findTaskByInfo(String infoTask){
        return taskRepository.findByInfoTaskContainingIgnoreCase(infoTask);
    }
    //buscar por estado
    public List<TaskModel> findTaskByState(String stateTask){
        return taskRepository.findByStateTask(stateTask);
    }

    //buscar por numero de usuario
    public List<TaskModel> findTaskByuser(long idUser){
        return taskRepository.findByUser_IdUser(idUser);
    }

    //update
    public Optional<TaskModel> updateTask(long id_task, TaskModel newTask){
        return taskRepository.findById(id_task).map(task -> {
            task.setNameTask(newTask.getNameTask());
            task.setInfoTask(newTask.getInfoTask());
            task.setStateTask(newTask.getStateTask());
            task.setUser(newTask.getUser());
            return taskRepository.save(task);
        });
    }

    //Delete
    public void deleteTask(long id){
        taskRepository.deleteById(id);
    }

}
