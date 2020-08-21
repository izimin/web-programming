package psu.ru.todo.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class ValidationException extends RuntimeException {

    private final Map<String, String> errors = new HashMap<>();

    @SuppressWarnings("unchecked")
    public void put(String name, String message) {
        errors.put(name, message);
    }

    public void isErrors() throws ValidationException {
        if (!errors.isEmpty()) {
            throw this;
        }
    }
}
