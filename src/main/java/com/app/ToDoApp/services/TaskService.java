package com.app.ToDoApp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.app.ToDoApp.entity.Task;
import com.app.ToDoApp.repository.TaskRepository;
import com.app.ToDoApp.exception.TaskNotFoundException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id)); 
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
