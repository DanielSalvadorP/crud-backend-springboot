package com.example.crudusuarios.controller;

import com.example.crudusuarios.model.TaskModel;
import com.example.crudusuarios.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<TaskModel> getAllTask(){
        return taskRepository.findAll();
    }

    @GetMapping(path = "/id/{id_task}")
    public Optional<TaskModel> findTaskById(@PathVariable("id_task") long id_task){
        return taskRepository.findById(id_task);
    }

    @PostMapping("/createtask")
    public TaskModel createTask(@RequestBody TaskModel TaskMdl){
        return taskRepository.save(TaskMdl);
    }

    @PutMapping(path = "/updatetask/{id_task}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable long id_task, @RequestBody TaskModel taskMdl){
        Optional<TaskModel> mdlTask = taskRepository.findById(id_task);

        if (mdlTask.isPresent()){
            TaskModel mdl = mdlTask.get();
            mdl.setId_task(taskMdl.getId_task());
            mdl.setInfo_task(taskMdl.getInfo_task());
            mdl.setInfo_task(taskMdl.getName_task());
            mdl.setState_task(taskMdl.getState_task());
            mdl.setId_user(taskMdl.getId_user());

            taskRepository.save(taskMdl);
            return  ResponseEntity.ok(taskMdl);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/dtt/{id_task}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id_task){
        if(!taskRepository.existsById(id_task)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada: "+ id_task);
        }

        taskRepository.deleteById(id_task);
        return ResponseEntity.ok("Tarea eliminada");
    }
}
