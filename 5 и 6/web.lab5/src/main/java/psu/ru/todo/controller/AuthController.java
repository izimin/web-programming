package psu.ru.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psu.ru.todo.exception.ValidationException;
import psu.ru.todo.model.pojos.ContextPojo;
import psu.ru.todo.model.pojos.UserPojo;
import psu.ru.todo.service.ContextService;
import psu.ru.todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class AuthController {

    // Инжектим сервис пользователя
    private final UserService userService;
    private final ContextService contextService;

    public AuthController(UserService userService, ContextService contextService) {
        this.userService = userService;
        this.contextService = contextService;
    }

    // Попытка входа
    @PostMapping("/login")
    public ContextPojo login(@RequestBody UserPojo user, HttpServletResponse response, HttpServletRequest request) {
        userService.createSession(user, response, request);
        return contextService.getContext(userService.getUserByLogin(user.getLogin()));
    }

    // Выход
    @PostMapping("/logout")
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        userService.sessionClear(response, request);
    }

    // Регистрация пользователя
    @PostMapping("/registration")
    public ContextPojo registration(@RequestBody UserPojo user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        userService.add(user);
        return contextService.getContext(userService.getUserByLogin(user.getLogin()));
    }
}