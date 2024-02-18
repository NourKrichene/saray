package com.nour.saray.infra.user;

import com.nour.saray.domain.ports.user.TaskService;
import com.nour.saray.infra.user.mapper.TaskDTOMapper;
import com.nour.saray.infra.user.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks().stream().map(TaskDTOMapper::toUser).collect(Collectors.toList()));
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(TaskDTOMapper.toUser(taskService.create(TaskDTOMapper.toDomain(task))));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable String id) {
        var task = taskService.getTaskById(id);
        taskService.delete(id);
        return ResponseEntity.ok(TaskDTOMapper.toUser(task));
    }


}
