package com.nour.saray.infra.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql("classpath:tasks-integration-test.sql"),
        @Sql(scripts = "classpath:clean-up-table.sql", executionPhase = AFTER_TEST_METHOD)
})
class TaskControllerIT {

    @Autowired
    MockMvc mockMvc;

    private static final String JSON_RESPONSE = """
            [{"id":"1","status":"DONE","name":"Title 1","creationDate":"2024-03-17T12:50:42.808546","description":"Description 1","priority":1},{"id":"2","status":"DONE","name":"Title 2","creationDate":"2024-03-10T20:51:56.208164","description":"Description 2","priority":2},{"id":"3","status":"NOT_DONE","name":"Title 3","creationDate":"2024-03-08T16:43:06.572297","description":"Description 3","priority":2}]""";

    @Test
    void shouldReturnTasks() throws Exception {
        mockMvc.perform(get("/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_RESPONSE));
    }

    @Test
    void shouldCreateTask() throws Exception {
        String task = """
                {
                    "name": "Title 4",
                    "description": "Description 4"
                }
                """;
        mockMvc.perform(post("/v1/tasks")
                .contentType("application/json")
                .content(task))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Title 4"))
                .andExpect(jsonPath("$.description").value("Description 4"))
                .andExpect(jsonPath("$.status").value("NOT_DONE"))
                .andExpect(jsonPath("$.priority").value(0))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        String task = """
                {"name": "Title 1 edited",
                    "description": "Description 1 edited",
                    "status": "IN_PROGRESS",
                    "priority": 1
                }
                """;
        mockMvc.perform(put("/v1/tasks/1")
                .contentType("application/json")
                .content(task))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Title 1 edited"))
                .andExpect(jsonPath("$.description").value("Description 1 edited"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void shouldUpdateTaskStatusUnchanged() throws Exception {
        String task = """
                {"name": "Title 2 edited",
                    "description": "Description 2 edited",
                    "status": "DONE",
                    "priority": 1
                }
                """;
        mockMvc.perform(put("/v1/tasks/2")
                        .contentType("application/json")
                        .content(task))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Title 2 edited"))
                .andExpect(jsonPath("$.description").value("Description 2 edited"))
                .andExpect(jsonPath("$.status").value("DONE"))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.id").value("2"));
    }

    @Test
    void shouldDeleteTask() throws Exception {
    mockMvc.perform(delete("/v1/tasks/1"))
            .andExpect(status().isOk());
    }

}
