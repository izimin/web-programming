package psu.ru.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jooq.util.maven.example.tables.pojos.Task;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TaskPojo extends Task {
    public TaskPojo(Long id, String name, String desc, UUID userId) {
        super(id, name, desc, userId, false);
    }
}
