/*
 * This file is generated by jOOQ.
*/
package org.jooq.util.maven.example.tables;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.jooq.util.maven.example.Indexes;
import org.jooq.util.maven.example.Keys;
import org.jooq.util.maven.example.Public;
import org.jooq.util.maven.example.tables.records.TaskRecord;


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
public class Task extends TableImpl<TaskRecord> {

    private static final long serialVersionUID = 1768888315;

    /**
     * The reference instance of <code>public.task</code>
     */
    public static final Task TASK = new Task();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TaskRecord> getRecordType() {
        return TaskRecord.class;
    }

    /**
     * The column <code>public.task.id</code>.
     */
    public final TableField<TaskRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('task_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.task.name</code>.
     */
    public final TableField<TaskRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.task.desc</code>.
     */
    public final TableField<TaskRecord, String> DESC = createField("desc", org.jooq.impl.SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.task.user_id</code>.
     */
    public final TableField<TaskRecord, UUID> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.task.is_completed</code>.
     */
    public final TableField<TaskRecord, Boolean> IS_COMPLETED = createField("is_completed", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * Create a <code>public.task</code> table reference
     */
    public Task() {
        this(DSL.name("task"), null);
    }

    /**
     * Create an aliased <code>public.task</code> table reference
     */
    public Task(String alias) {
        this(DSL.name(alias), TASK);
    }

    /**
     * Create an aliased <code>public.task</code> table reference
     */
    public Task(Name alias) {
        this(alias, TASK);
    }

    private Task(Name alias, Table<TaskRecord> aliased) {
        this(alias, aliased, null);
    }

    private Task(Name alias, Table<TaskRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.TASK_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TaskRecord, Long> getIdentity() {
        return Keys.IDENTITY_TASK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TaskRecord> getPrimaryKey() {
        return Keys.TASK_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TaskRecord>> getKeys() {
        return Arrays.<UniqueKey<TaskRecord>>asList(Keys.TASK_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<TaskRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TaskRecord, ?>>asList(Keys.TASK__TASK_USER_ID_FK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task as(String alias) {
        return new Task(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task as(Name alias) {
        return new Task(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(String name) {
        return new Task(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Task rename(Name name) {
        return new Task(name, null);
    }
}
