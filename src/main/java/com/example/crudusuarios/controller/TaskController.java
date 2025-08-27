package com.example.crudusuarios.controller;

import com.example.crudusuarios.Service.TaskService;
import com.example.crudusuarios.model.TaskModel;
import com.example.crudusuarios.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.JsonPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskModel> getAllTask(){
        return taskService.getAllTasks();
    }

    @GetMapping(path = "/{id_task}")
    public ResponseEntity<TaskModel> findTaskById(@PathVariable long id_task){
        return taskService.findTaskById(id_task).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/nameTask/{name_task}")
    public List<TaskModel> findTaskByName(@PathVariable String name_task){
        return  taskService.findTaskByName(name_task);
    }

    @GetMapping(path = "/infoTask/{info_task}")
    public List<TaskModel> findTaskByInfo(@PathVariable String info_task){
        return taskService.findTaskByInfo(info_task);
    }

    @GetMapping(path = "/stateTask/{state_task}")
    public List<TaskModel> findTaskByState(@PathVariable String state_task){
        return taskService.findTaskByState(state_task);
    }

    @GetMapping(path = "/userTask/{id_user}")
    public List<TaskModel> findTaskByIdUser(@PathVariable long id_user){
        return taskService.findTaskByuser(id_user);
    }

    @PostMapping("/createtask")
    public TaskModel createTask(@RequestBody TaskModel TaskMdl){
        return taskService.crateTask(TaskMdl);
    }

    //Update
    @PutMapping(path = "/updatetask/{id_task}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable long id_task, @RequestBody TaskModel taskMdl){
        return taskService.updateTask(id_task,taskMdl)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/actulizardatotask/{id_task}")
    public ResponseEntity<TaskModel> updateData(@PathVariable long id_task, @RequestBody TaskModel tm){
        Optional<TaskModel> opTm = taskRepository.findById(id_task);
        if (opTm.isPresent()){

        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/dltt/{id_task}")
    public ResponseEntity<String> deleteTask(@PathVariable long id_task){
        if(!taskRepository.existsById(id_task)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada: "+ id_task);
        }
        taskRepository.deleteById(id_task);
        return ResponseEntity.ok("Tarea eliminada");
    }
}
