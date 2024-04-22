package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public Task createTask(Task newTask) {
        return taskRepository.save(newTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getByID(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        return byId.orElse(null);
    }

    public void deleteByID(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean updateTask(Task task, Long id) {
        if (!taskRepository.existsById(id)) {
            return false;
        }
        task.setId(id);
        taskRepository.save(task);
        return true;
    }




}
