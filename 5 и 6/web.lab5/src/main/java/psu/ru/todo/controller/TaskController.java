package psu.ru.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import psu.ru.todo.exception.NoAccessException;
import psu.ru.todo.exception.NotFoundException;
import psu.ru.todo.model.pojos.TaskPojo;
import psu.ru.todo.model.pojos.UserPojo;
import psu.ru.todo.service.TaskService;
import psu.ru.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public TaskPojo getById(
            HttpServletResponse response, HttpServletRequest request, @PathVariable Long id) {
        TaskPojo taskPojo = taskService.getById(id);
        if (taskPojo == null) {
            throw new NotFoundException("Задача не найдена!");
        }
        return taskPojo;
    }

    @GetMapping("/list")
    public List<TaskPojo> list(
            HttpServletResponse response, HttpServletRequest request) {
        UserPojo userPojo = userService.getUserBySessionId(userService.getSessionId(request));
        return taskService.list(userPojo.getId());
    }


    @PutMapping("/update")
    public void update(
            @RequestBody TaskPojo taskPojo,
            HttpServletResponse response, HttpServletRequest request
    ) {
        if (taskService.getById(taskPojo.getId()) == null) {
            throw new NotFoundException("Задача не найдена");
        }

        UUID sessionId = userService.getSessionId(request);

        UserPojo userPojo = userService.getUserBySessionId(sessionId);

        if (taskPojo.getUserId().compareTo(userPojo.getId()) != 0) {
            throw new NoAccessException("Нет доступа");
        }

        taskPojo.setUserId(userPojo.getId());
        taskService.save(taskPojo);
    }

    @PostMapping("/create")
    public void create(@RequestBody TaskPojo taskPojo, HttpServletResponse response, HttpServletRequest request) {
        UserPojo userPojo = userService.getUserBySessionId(userService.getSessionId(request));

        taskService.add(new TaskPojo(null, taskPojo.getName(), taskPojo.getDesc(), userPojo.getId()));
    }


    @DeleteMapping("/delete/{taskId}")
    public void del(
            @PathVariable Long taskId, HttpServletResponse response, HttpServletRequest request
    ) {
        TaskPojo taskPojo = taskService.getById(taskId);
        if (taskPojo == null) {
            throw new NotFoundException("Задача не найдена");
        }
        UUID sessionId = userService.getSessionId(request);
        UserPojo userPojo = userService.getUserBySessionId(sessionId);

        if (taskPojo.getUserId().compareTo(userPojo.getId()) != 0) {
            throw new NoAccessException("Нет доступа");
        }

        taskService.delete(taskId);
    }

    @PutMapping("/changeStatus/{taskId}")
    public void changeStatus(
            @PathVariable Long taskId,
            HttpServletResponse response, HttpServletRequest request
    ) {
        if (taskService.getById(taskId) == null) {
            throw new NotFoundException("Задача не найдена");
        }
        UUID sessionId = userService.getSessionId(request);

        UserPojo userPojo = userService.getUserBySessionId(sessionId);
        TaskPojo taskPojo = taskService.getById(taskId);

        if (taskPojo.getUserId().compareTo(userPojo.getId()) != 0) {
            throw new NoAccessException("Нет доступа");
        }

        taskService.changeStatus(taskPojo);
    }

//    @PostMapping("/delete/{taskId}")
//    public String delete(
//            @PathVariable Long taskId,
//            HttpServletResponse response, HttpServletRequest request,
//            Model model
//    ) {
//        UUID sessionId = userService.getSessionId(request);
//
//        if (sessionId == null) {
//            return "redirect:/login";
//        }
//
//        UserPojo userPojo = userService.getUserBySessionId(sessionId);
//        TaskPojo taskPojo = taskService.getById(taskId);
//
//        if (userPojo == null || taskPojo == null || !taskPojo.getUserId().equals(userPojo.getId())) {
//            return "redirect:/login";
//        }
//
//        taskService.delete(taskId);
//
//        return "redirect:/task/list";
//    }
}