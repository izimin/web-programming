/*
 * This file is generated by jOOQ.
*/
package org.jooq.util.maven.example.tables.records;


import java.util.UUID;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.util.maven.example.tables.Task;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TaskRecord extends UpdatableRecordImpl<TaskRecord> implements Record5<Long, String, String, UUID, Boolean> {

    private static final long serialVersionUID = 165163793;

    /**
     * Setter for <code>public.task.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.task.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.task.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.task.name</code>.
     */
    @NotNull
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.task.desc</code>.
     */
    public void setDesc(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.task.desc</code>.
     */
    @NotNull
    public String getDesc() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.task.user_id</code>.
     */
    public void setUserId(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.task.user_id</code>.
     */
    @NotNull
    public UUID getUserId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.task.is_completed</code>.
     */
    public void setIsCompleted(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.task.is_completed</code>.
     */
    public Boolean getIsCompleted() {
        return (Boolean) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, String, UUID, Boolean> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, String, UUID, Boolean> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Task.TASK.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Task.TASK.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Task.TASK.DESC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field4() {
        return Task.TASK.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field5() {
        return Task.TASK.IS_COMPLETED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID component4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component5() {
        return getIsCompleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getDesc();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value5() {
        return getIsCompleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskRecord value3(String value) {
        setDesc(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskRecord value4(UUID value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskRecord value5(Boolean value) {
        setIsCompleted(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskRecord values(Long value1, String value2, String value3, UUID value4, Boolean value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TaskRecord
     */
    public TaskRecord() {
        super(Task.TASK);
    }

    /**
     * Create a detached, initialised TaskRecord
     */
    public TaskRecord(Long id, String name, String desc, UUID userId, Boolean isCompleted) {
        super(Task.TASK);

        set(0, id);
        set(1, name);
        set(2, desc);
        set(3, userId);
        set(4, isCompleted);
    }
}
