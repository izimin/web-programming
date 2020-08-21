package psu.ru.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import psu.ru.auth.model.UserPojo;
import psu.ru.auth.service.TaskService;
import psu.ru.auth.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@Controller
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/login")
    public String login(HttpServletResponse response, HttpServletRequest request) {
        UUID sessionId = userService.getSessionId(request);
        if (sessionId == null) {
            return "login";
        }
        return "redirect:/task/list";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam(name="login", required=false) String login,
            @RequestParam(name="password", required=false) String password,
            HttpServletResponse response, HttpServletRequest request,
            Model model
    ) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UUID sessionId = userService.getSessionId(request);
        if (sessionId == null) {
            if (!userService.login(new UserPojo(login, password))) {
                model.addAttribute("isError", true);
                model.addAttribute("login", login);
                return "login";
            }
            sessionId = UUID.randomUUID();
            Cookie cookie = new Cookie("session_id", sessionId.toString());
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 12);
            response.addCookie(cookie);
        }

        UserPojo userPojo = userService.getUserByLogin(login);
        userService.addSession(userPojo.getId(), sessionId);

        model.addAttribute("tasks", taskService.list(userPojo.getId()));

        return "redirect:/task/list";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("session_id", null);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        UUID sessionId = userService.getSessionId(request);
        if (sessionId != null) {
            userService.removeSession(sessionId);
        }

        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(HttpServletResponse response, HttpServletRequest request) {
        UUID sessionId = userService.getSessionId(request);

        if (sessionId == null) {
            return "registration";
        }

        return "redirect:/task/list";
    }

    @PostMapping("/registration")
    public String registration(
            @RequestParam(name="login", required=false) String login,
            @RequestParam(name="password", required=false) String password,
            @RequestParam(name="password2", required=false) String password2,
            @RequestParam(name="fio", required=false) String fio,
            HttpServletResponse response, HttpServletRequest  request,
            Model model
    ) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UUID sessionId = userService.getSessionId(request);

        if (sessionId != null) {
            return "redirect:/task/list";
        }

        model.addAttribute("fio", fio);
        model.addAttribute("login", login);

        if (login.isEmpty()) {
            model.addAttribute("isLoginEmpty", true);
        }
        if (password.isEmpty()) {
            model.addAttribute("isPasswordEmpty", true);
        }
        if (password2.isEmpty()) {
            model.addAttribute("isPassword2Empty", true);
        }

        if (login.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            return "registration";
        }

        if (!userService.validateLogin(login)) {
            model.addAttribute("isErrorLogin", true);
            return "registration";
        } else if (!userService.validatePassword(password, password2)) {
            model.addAttribute("isErrorPass", "Пароли не совпадают!");
            return "registration";
        }
        userService.add(new UserPojo(login, password, fio));
        return "redirect:/login";
    }
}