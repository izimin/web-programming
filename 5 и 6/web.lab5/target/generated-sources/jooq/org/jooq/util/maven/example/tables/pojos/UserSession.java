/*
 * This file is generated by jOOQ.
*/
package org.jooq.util.maven.example.tables.pojos;


import java.io.Serializable;
import java.util.UUID;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;


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
public class UserSession implements Serializable {

    private static final long serialVersionUID = -2011808539;

    private Long id;
    private UUID sessionId;
    private UUID userId;

    public UserSession() {}

    public UserSession(UserSession value) {
        this.id = value.id;
        this.sessionId = value.sessionId;
        this.userId = value.userId;
    }

    public UserSession(
        Long id,
        UUID sessionId,
        UUID userId
    ) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public UUID getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    @NotNull
    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserSession (");

        sb.append(id);
        sb.append(", ").append(sessionId);
        sb.append(", ").append(userId);

        sb.append(")");
        return sb.toString();
    }
}
