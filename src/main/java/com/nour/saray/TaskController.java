package com.nour.saray;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @PostMapping("/task")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) {
        var task = taskRepository.findById(id);
        taskRepository.deleteById(id);
        return ResponseEntity.ok(task.get());
    }

}
