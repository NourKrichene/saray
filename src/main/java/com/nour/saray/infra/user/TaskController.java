package com.nour.saray.infra.user;

import com.nour.saray.domain.ports.user.TaskService;
import com.nour.saray.infra.user.mapper.TaskDTOMapper;
import com.nour.saray.infra.user.model.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(TaskDTOMapper.toUser(taskService.create(TaskDTOMapper.toDomain(taskDTO))));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks().stream().map(TaskDTOMapper::toUser).toList());
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(TaskDTOMapper.toUser(taskService.update(id, TaskDTOMapper.toDomain(taskDTO))));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

}
