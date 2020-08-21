package psu.ru.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jooq.util.maven.example.tables.pojos.User;

@Getter
@Setter
@AllArgsConstructor
public class UserPojo extends User {
    public UserPojo(String login, String password, String fio) {
        super(null, login, password, null, fio, true);
    }

    public UserPojo(String login, String password) {
        super(null, login, password, null, null, true);
    }
}
