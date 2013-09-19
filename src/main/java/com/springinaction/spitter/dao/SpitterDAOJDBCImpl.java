package com.springinaction.spitter.dao;

import com.springinaction.spitter.model.Spitter;

/**
 * The pure JDBC implementation of {@link SpitterDAO}.
 */
public class SpitterDAOJDBCImpl implements SpitterDAO {
    private static final String SQL_INSERT_SPITTER =
            "insert into spitter (username, password, fullname) values (?, ?, ?)";
    private static final String SQL_UPDATE_SPITTER =
            "update spitter set username = ?, password = ?, fullname = ?"
                    + "where id = ?";
    private static final String SQL_SELECT_SPITTER =
            "select id, username, fullname from spitter where id = ?";
    @Override
    public void addSpitter(Spitter spitter) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Spitter getSpitterById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
