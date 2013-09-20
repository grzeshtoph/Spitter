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

    public Spitter() {
    }

    public Spitter(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
