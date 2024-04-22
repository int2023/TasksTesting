package com.example.demo;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;


    @Test
    public void createTask_TaskCreated() {
        Task task = new Task(1L,"title1","description1",
                LocalDate.of(2024,05,01),true);

        taskService.createTask(task);
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
    }

    @Test
    public void getAllTasks() {
        Task task = new Task(1L,"title1","description1",
                LocalDate.of(2024,05,01),true);
        Task task2 = new Task(2L,"title2","description2",
                LocalDate.of(2023,05,01),true);
        List<Task> tasks = List.of(task, task2);
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> actualTasks = taskService.getAllTasks();
        Assertions.assertEquals(tasks,actualTasks);
        Mockito.verify(taskRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getById_Exists() {
        Task task = new Task(1L,"title1","description1",
                LocalDate.of(2024,05,01),true);
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task actualTask = taskService.getByID(1L);
        Assertions.assertNotNull(actualTask);
        Assertions.assertEquals(task,actualTask);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(1L);
    }


    @Test
    public void getById_Is_Not_Exists() {
        Mockito.when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        Task actualTask = taskService.getByID(1L);
        Assertions.assertNull(actualTask);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(1L);
    }


    @Test
    public void deleteById() {
        taskService.deleteByID(1L);
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1L);
    }


    @Test
    public void updateTask_Task_Exists() {
        Task task = new Task(1L,"title1","description1",
                LocalDate.of(2024,05,01),true);
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        Mockito.when(taskRepository.existsById(1L)).thenReturn(true);

        boolean updated = taskService.updateTask(task,1L);
        Assertions.assertTrue(updated);
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
     }

    @Test
    public void updateTask_Task_Is_Not_Exists() {
        Task task = new Task(1L,"title1","description1",
                LocalDate.of(2024,05,01),true);
        Mockito.when(taskRepository.existsById(1L)).thenReturn(false);

        boolean updated = taskService.updateTask(task,1L);
        Assertions.assertFalse(updated);
        Mockito.verify(taskRepository, Mockito.times(0)).save(task);

    }


}
