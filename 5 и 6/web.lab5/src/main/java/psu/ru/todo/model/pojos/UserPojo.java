package psu.ru.todo.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jooq.util.maven.example.tables.pojos.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo extends User {
    private String confirmPassword;
    public UserPojo(String login, String password, String confirmPassword, String fio) {
        super(null, login, password, null, fio, true);
        this.confirmPassword = confirmPassword;
    }
    public UserPojo(String login, String password, String fio) {
        super(null, login, password, null, fio, true);
    }
    public UserPojo(String login, String password) {
        super(null, login, password, null, null, true);
    }
}
