package com.relationship.center.models;

import java.util.UUID;

public class Problem {

    private java.util.UUID id;
    private String message;

    public Problem(String message) {
        this.id = UUID.randomUUID();
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
