package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("task")
public class TaskController {

    private TaskService taskService;

    @PostMapping("create")
    public ResponseEntity<Task> createNewTask(@RequestBody Task task) {
        return new ResponseEntity<Task>(taskService.createTask(task),HttpStatus.CREATED);
    }

    @GetMapping("all")
    public List<Task> getAllExistingTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getByID(id);
    }

    @DeleteMapping("{id}")
    public void deleteByID(@PathVariable Long id) {
        taskService.deleteByID(id);
    }

    @PutMapping("{id}")
    public boolean updateTask(@RequestBody Task task, @PathVariable Long id) {
       return taskService.updateTask(task, id);
    }

}
