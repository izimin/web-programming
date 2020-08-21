package psu.ru.todo.service;

import org.springframework.stereotype.Service;
import psu.ru.todo.model.pojos.ContextPojo;
import psu.ru.todo.model.pojos.UserPojo;

@Service
public class ContextService {
    public ContextPojo getContext(UserPojo user) {
        return user == null ? null : new ContextPojo(user.getLogin(), user.getFio());
    }
}
