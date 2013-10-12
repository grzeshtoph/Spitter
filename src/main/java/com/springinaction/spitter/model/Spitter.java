package com.springinaction.spitter.model;

import com.google.common.base.Objects;

/**
 * Standard DO for representing a single Spitter object.
 */
public class Spitter {
    private Long userId;
    private String username;
    private String password;
    private String fullName;

    public Spitter(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public Spitter(Long userId, String username, String password, String fullName) {
        this(username, password, fullName);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("userId", userId)
                .add("username", username)
                .add("password", password)
                .add("fullName", fullName)
                .toString();
    }
}
