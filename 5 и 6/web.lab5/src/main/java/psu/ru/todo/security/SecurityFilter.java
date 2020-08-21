package psu.ru.todo.security;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import psu.ru.todo.model.pojos.UserPojo;
import psu.ru.todo.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@WebFilter(urlPatterns = "/*")
@AllArgsConstructor
public class SecurityFilter implements Filter {

    private final UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie sessionCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session_id")) {
                    if (null != cookie.getValue() && !cookie.getValue().isEmpty()) {
                        sessionCookie = cookie;
                    }
                }
            }
        }

        boolean auth = request.getRequestURI().contains("/login");
        boolean context = request.getRequestURI().contains("/context");

        boolean logout = request.getRequestURI().contains("/logout");
        boolean registration = request.getRequestURI().contains("/registration");

        if (auth || logout || registration || context) {
            chain.doFilter(req, resp);
        } else if (sessionCookie == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            UserPojo user = userService.getUserBySessionId(UUID.fromString(sessionCookie.getValue()));
            if (user != null) {
                chain.doFilter(req, resp);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    @Override
    public void destroy() {}
}
