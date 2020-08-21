package psu.ru.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import psu.ru.auth.model.TaskPojo;
import psu.ru.auth.model.UserPojo;
import psu.ru.auth.service.TaskService;
import psu.ru.auth.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) {
        UUID sessionId = userService.getSessionId(request);

        if (sessionId == null) {
            return "redirect:/login";
        }

        UserPojo userPojo = userService.getUserBySessionId(sessionId);

        if (userPojo == null) {
            return "redirect:/login";
        }

        List<TaskPojo> taskPojos = taskService.list(userPojo.getId());

        if (taskPojos.size() == 0) {
            model.addAttribute("isFound", false);
        } else {
            model.addAttribute("isFound", true);
            model.addAttribute("tasks", taskPojos);
        }
        model.addAttribute("userId", userPojo.getId());

        return "list";
    }

    @GetMapping("/edit/{taskId}")
    public String edit(
            @PathVariable("taskId") Long taskId,
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) {
        UUID sessionId = userService.getSessionId(request);

        if (sessionId == null) {
            return "redirect:/login";
        }

        UserPojo userPojo = userService.getUserBySessionId(sessionId);
        TaskPojo taskPojo = taskService.getById(taskId);

        if (userPojo == null || taskPojo == null || !userPojo.getId().equals(taskPojo.getUserId())) {
            return "redirect:/login";
        }

        model.addAttribute("task", taskPojo);

        return "edit";
    }

    @PostMapping("/update/{taskId}")
    public String update(
            @RequestParam(name="name", required=false) String name,
            @RequestParam(name="desc", required=false) String desc,
            @PathVariable("taskId") Long taskId,
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) {
        UUID sessionId = userService.getSessionId(request);

        if (sessionId == null) {
            return "redirect:/login";
        }

        UserPojo userPojo = userService.getUserBySessionId(sessionId);
        TaskPojo taskPojo = taskService.getById(taskId);

        if (userPojo == null || taskPojo == null || !userPojo.getId().equals(taskPojo.getUserId())) {
            return "redirect:/login";
        }

        model.addAttribute("task", taskPojo);
        model.addAttribute("name", name);
        model.addAttribute("desc", desc);

        if (name.isEmpty()) {
            model.addAttribute("isNameEmpty", true);
            return "edit";
        }

        if (taskService.checkName(userPojo.getId(), taskPojo.getId(), name)) {
            model.addAttribute("isError", true);
            return "edit";
        }

        taskService.save(new TaskPojo(taskId, name, desc, userPojo.getId()));

        return "redirect:/task/list";
    }

    @GetMapping("/add")
    public String add(
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) {
        if (userService.getSessionId(request) == null) {
            return "redirect:/login";
        }

        return "add";
    }

    @PostMapping("/create")
    public String create(
            @RequestParam(name="name", required=false) String name,
            @RequestParam(name="desc", required=false) String desc,
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) {
        UUID sessionId = userService.getSessionId(request);
        if (sessionId == null) {
            return "redirect:/login";
        }

        UserPojo userPojo = userService.getUserBySessionId(sessionId);

        model.addAttribute("name", name);
        model.addAttribute("desc", desc);

        if (name.isEmpty()) {
            model.addAttribute("isNameEmpty", true);
            return "add";
        }

        if (taskService.checkName(userPojo.getId(), -1L, name)) {
            model.addAttribute("isError", true);
            return "add";
        }

        taskService.add(new TaskPojo(null, name, desc, userPojo.getId()));

        return "redirect:/task/list";
    }

    @PostMapping("/delete/{taskId}")
    public String delete(
            @PathVariable Long taskId,
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) {
        UUID sessionId = userService.getSessionId(request);

        if (sessionId == null) {
            return "redirect:/login";
        }

        UserPojo userPojo = userService.getUserBySessionId(sessionId);
        TaskPojo taskPojo = taskService.getById(taskId);

        if (userPojo == null || taskPojo == null || !taskPojo.getUserId().equals(userPojo.getId())) {
            return "redirect:/login";
        }

        taskService.delete(taskId);

        return "redirect:/task/list";
    }
}