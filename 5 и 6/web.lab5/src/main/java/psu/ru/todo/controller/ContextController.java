package psu.ru.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import psu.ru.todo.model.pojos.ContextPojo;
import psu.ru.todo.model.pojos.UserPojo;
import psu.ru.todo.service.ContextService;
import psu.ru.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ContextController {

    private final ContextService contextService;
    private final UserService userService;

    public ContextController(ContextService contextService, UserService userService) {
        this.contextService = contextService;
        this.userService = userService;
    }

    // Попытка входа
    @GetMapping("/context")
    public ContextPojo login(HttpServletResponse response, HttpServletRequest request) {
        if (request.getCookies() == null) return null;
        return contextService.getContext(userService.getUserBySessionId(userService.getSessionId(request)));
    }

}
