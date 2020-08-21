package psu.ru.auth.repository;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import psu.ru.auth.model.TaskPojo;

import java.util.List;
import java.util.UUID;

import static org.jooq.util.maven.example.tables.Task.TASK;

@Repository
public class TaskRepository {
    private final DSLContext dslContext;

    public TaskRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<TaskPojo> list(UUID userId) {
        return dslContext.selectFrom(TASK)
                .where(TASK.USER_ID.eq(userId))
                .orderBy(TASK.ID)
                .fetchInto(TaskPojo.class);
    }

    public TaskPojo getTaskById(Long taskId) {
        return dslContext.selectFrom(TASK)
                .where(TASK.ID.eq(taskId))
                .fetchOneInto(TaskPojo.class);
    }

    public void update(TaskPojo taskPojo) {
        dslContext.update(TASK)
                .set(TASK.NAME, taskPojo.getName())
                .set(TASK.DESC, taskPojo.getDesc())
                .set(TASK.USER_ID, taskPojo.getUserId())
                .where(TASK.ID.eq(taskPojo.getId()).and(TASK.USER_ID.eq(taskPojo.getUserId())))
                .execute();
    }

    public void insert(TaskPojo taskPojo) {
        dslContext.insertInto(TASK)
                .columns(TASK.NAME, TASK.DESC, TASK.USER_ID)
                .values(taskPojo.getName(), taskPojo.getDesc(), taskPojo.getUserId())
                .execute();
    }

    public void delete(Long id) {
        dslContext.deleteFrom(TASK)
                .where(TASK.ID.eq(id))
                .execute();
    }

    public boolean checkName(UUID userId, Long taskId, String name) {
        return dslContext.fetchExists(
                dslContext.selectFrom(TASK)
                        .where(TASK.USER_ID.eq(userId)
                                .and(TASK.NAME.eq(name)
                                        .and(TASK.ID.notEqual(taskId))))
        );
    }

    public void changeStatus(Long id, boolean isCompleted) {
        dslContext.update(TASK)
                .set(TASK.IS_COMPLETED, isCompleted)
                .where(TASK.ID.eq(id))
                .execute();
    }
}
