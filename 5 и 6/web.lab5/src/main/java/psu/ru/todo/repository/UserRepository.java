package psu.ru.todo.repository;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import psu.ru.todo.model.pojos.UserPojo;

import java.util.UUID;

import static org.jooq.util.maven.example.tables.User.USER;
import static org.jooq.util.maven.example.tables.UserSession.USER_SESSION;

@Repository
public class UserRepository {
    private final DSLContext dslContext;

    public UserRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void insert(UserPojo userDto) {
        dslContext.insertInto(USER)
                .columns(USER.ID, USER.LOGIN, USER.PASSWORD, USER.SALT, USER.FIO)
                .values(UUID.randomUUID(), userDto.getLogin(), userDto.getPassword(), userDto.getSalt(), userDto.getFio())
                .execute();
    }

    public UserPojo getUserByLogin(String login) {
        return dslContext.selectFrom(USER)
                .where(USER.LOGIN.eq(login))
                .fetchOneInto(UserPojo.class);
    }

    public UserPojo getUserBySessionId(UUID sessionId) {
        return dslContext.select(USER.fields())
                .from(USER)
                .join(USER_SESSION).on(USER.ID.eq(USER_SESSION.USER_ID))
                .where(USER_SESSION.SESSION_ID.eq(sessionId))
                .fetchOneInto(UserPojo.class);
    }

    public void deleteSession(UUID sessionId) {
        dslContext.deleteFrom(USER_SESSION)
                .where(USER_SESSION.SESSION_ID.eq(sessionId))
                .execute();
    }

    public void insertSession(UUID userId, UUID sessionId) {
        dslContext.insertInto(USER_SESSION)
                .columns(USER_SESSION.SESSION_ID, USER_SESSION.USER_ID)
                .values(sessionId, userId)
                .execute();
    }
}
