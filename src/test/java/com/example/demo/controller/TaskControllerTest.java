package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createTask() throws Exception {
        Task task = new Task(1L, "title1", "description1",
                LocalDate.of(2024, 05, 01), true);
        String jsonTask = objectMapper.writeValueAsString(task);
        Mockito.when(taskService.createTask(task)).thenReturn(task);

        mockMvc.perform(
                        post("/task/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonTask))
                .andExpect(status().isCreated());

        Mockito.verify(taskService, Mockito.times(1))
                .createTask(task);
    }

    @Test
    public void getAllExistingTasks() throws Exception {
        Task task = new Task(1L, "title1", "description1",
                LocalDate.of(2024, 05, 01), true);
        Task task2 = new Task(2L, "title2", "description2",
                LocalDate.of(2023, 05, 01), true);
        List<Task> tasks = List.of(task, task2);

        String jsonTasks = objectMapper.writeValueAsString(tasks);

        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/task/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonTasks));

        Mockito.verify(taskService, Mockito.times(1))
                .getAllTasks();
    }

    @Test
    public void getTaskById_IfExists() throws Exception {
        Task task = new Task(1L, "title1", "description1",
                LocalDate.of(2024, 05, 01), true);
        String jsonTask = objectMapper.writeValueAsString(task);
        Mockito.when(taskService.getByID(1L)).thenReturn(task);

        mockMvc.perform(get("/task/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonTask));

        Mockito.verify(taskService, Mockito.times(1))
                .getByID(1L);
    }

    @Test
    public void getTaskById_IfNotExists() throws Exception {
        Mockito.when(taskService.getByID(1L)).thenReturn(null);

        mockMvc.perform(get("/task/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        Mockito.verify(taskService, Mockito.times(1))
                .getByID(1L);
    }


    @Test
    public void deleteTaskById() throws Exception {
        mockMvc.perform(delete("/task/{id}", 1L))
                .andExpect(status().isOk());
        Mockito.verify(taskService, Mockito.times(1))
                .deleteByID(1L);
    }

    @Test
    public void updateTask_ExistsTask() throws Exception {
        Task task = new Task(1L, "title1", "description1",
                LocalDate.of(2024, 05, 01), true);
        String jsonTask = objectMapper.writeValueAsString(task);
        Mockito.when(taskService.updateTask(task, 1L)).thenReturn(true);

        mockMvc.perform(put("/task/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTask))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        Mockito.verify(taskService, Mockito.times(1))
                .updateTask(task, 1L);
    }

    @Test
    public void updateTask_NotExistsTask() throws Exception {
        Task task = new Task(1L, "title1", "description1",
                LocalDate.of(2024, 05, 01), true);
        String jsonTask = objectMapper.writeValueAsString(task);
        Mockito.when(taskService.updateTask(task, 1L)).thenReturn(false);

        mockMvc.perform(put("/task/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTask))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        Mockito.verify(taskService, Mockito.times(1))
                .updateTask(task, 1L);
    }

}
