package com.springinaction.spitter.dao;

/**
 * Static holder for SQL statements.
 */
public final class SQLStatements {
    static final String SQL_INSERT_SPITTER =
            "insert into spitter_users (username, password, full_name) values (?, ?, ?)";
    static final String SQL_UPDATE_SPITTER =
            "update spitter_users set username = ?, password = ?, full_name = ?"
                    + "where user_id = ?";
    static final String SQL_SELECT_SPITTER =
            "select user_id, username, password, full_name from spitter_users where username = ?";

    private SQLStatements() {
    }

}
