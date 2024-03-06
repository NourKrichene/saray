package com.nour.saray.infra.user;

import com.nour.saray.domain.ports.user.TaskService;
import com.nour.saray.infra.user.mapper.TaskDTOMapper;
import com.nour.saray.infra.user.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks().stream().map(TaskDTOMapper::toUser).toList());
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(TaskDTOMapper.toUser(taskService.create(TaskDTOMapper.toDomain(task))));
    }

    @PatchMapping("/task")
    public ResponseEntity<TaskDTO> editTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(TaskDTOMapper.toUser(taskService.update(TaskDTOMapper.toDomain(task))));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable String id) {
        var task = taskService.getTaskById(id);
        if (task != null) {
            taskService.delete(id);
            return ResponseEntity.ok(TaskDTOMapper.toUser(task));
        } else {
            return ResponseEntity.ok(null);
        }
    }


}
