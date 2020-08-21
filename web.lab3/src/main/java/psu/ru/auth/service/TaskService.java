package psu.ru.auth.service;

import org.springframework.stereotype.Service;
import psu.ru.auth.model.TaskPojo;
import psu.ru.auth.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskPojo> list(UUID userId) {
        return taskRepository.list(userId);
    }

    public TaskPojo getById(Long taskId) {
        return taskRepository.getTaskById(taskId);
    }

    public void save(TaskPojo taskPojo) {
        taskRepository.update(taskPojo);
    }

    public void add(TaskPojo taskPojo) {
        taskRepository.insert(taskPojo);
    }

    public void delete(Long id) {
        taskRepository.delete(id);
    }

    public boolean checkName(UUID userId, Long taskId, String name) {
        return taskRepository.checkName(userId, taskId, name);
    }
}