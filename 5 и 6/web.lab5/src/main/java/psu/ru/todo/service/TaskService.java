package psu.ru.todo.service;

import org.springframework.stereotype.Service;
import psu.ru.todo.exception.ValidationException;
import psu.ru.todo.model.pojos.TaskPojo;
import psu.ru.todo.repository.TaskRepository;

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
        String oldNeme = taskRepository.getTaskById(taskPojo.getId()).getName();
        if (oldNeme.equals(taskPojo.getName().trim())) {
            return;
        }
        validationName(taskPojo);
        taskRepository.update(taskPojo);
    }

    public void add(TaskPojo taskPojo) {
        validationName(taskPojo);
        taskRepository.insert(taskPojo);
    }

    private void validationName(TaskPojo taskPojo) {
        ValidationException validationException = new ValidationException();
        if (checkName(taskPojo.getUserId(), taskPojo.getName())) {
            validationException.put("nameError", "задача с данным наименованием уже существует!");
        }
        validationException.isErrors();
    }

    public void delete(Long id) {
        taskRepository.delete(id);
    }

    public void changeStatus(TaskPojo taskPojo) {
        boolean isCompleted = !taskPojo.getIsCompleted();
        taskRepository.changeStatus(taskPojo.getId(), isCompleted);
    }

    public boolean checkName(UUID userId, String name) {
        return taskRepository.checkName(userId, name);
    }
}