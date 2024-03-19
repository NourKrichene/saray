package com.nour.saray.infra.user;

import com.nour.saray.domain.ports.user.TaskService;
import com.nour.saray.infra.user.mapper.TaskDTOMapper;
import com.nour.saray.infra.user.model.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping()
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTaskDTO = TaskDTOMapper.toUser(taskService.create(TaskDTOMapper.toDomain(taskDTO)));
        return ResponseEntity.ok(createdTaskDTO);
    }

    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<TaskDTO> taskDTOs = taskService.getAllTasks().stream()
                .map(TaskDTOMapper::toUser)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTaskDTO = TaskDTOMapper.toUser(taskService.update(id, TaskDTOMapper.toDomain(taskDTO)));
        return ResponseEntity.ok(updatedTaskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
