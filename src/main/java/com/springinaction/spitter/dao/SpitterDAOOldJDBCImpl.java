package com.springinaction.spitter.dao;

import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The pure JDBC implementation of {@link SpitterDAO}.
 */
@Component("spitterDAOOldJDBC")
public class SpitterDAOOldJDBCImpl implements SpitterDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SpitterDAOOldJDBCImpl.class);
    private static final String SQL_INSERT_SPITTER =
            "insert into spitter_users (username, password, full_name) values (?, ?, ?)";
    private static final String SQL_UPDATE_SPITTER =
            "update spitter_users set username = ?, password = ?, full_name = ?"
                    + "where user_id = ?";
    private static final String SQL_SELECT_SPITTER =
            "select user_id, username, password, full_name from spitter_users where user_id = ?";

    @Value("#{dataSource}")
    private DataSource dataSource;

    @Override
    public void addSpitter(Spitter spitter) throws DAOException {
        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(SQL_INSERT_SPITTER);
            pStmt.setString(1, spitter.getUsername());
            pStmt.setString(2, spitter.getPassword());
            pStmt.setString(3, spitter.getFullName());
            pStmt.execute();

            LOG.debug("New spitter added: {}", spitter);
        } catch (SQLException e) {
            LOG.error("Error when trying to insert new spitter " + spitter, e);
            throw new DAOException(e);
        } finally {
            try {
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                LOG.error("Error when closing connection", e);
                throw new DAOException(e);
            }
        }
    }

    @Override
    public void saveSpitter(Spitter spitter) throws DAOException {
        Connection conn = null;
        PreparedStatement pStmt = null;

        try {
            conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(SQL_UPDATE_SPITTER);
            pStmt.setString(1, spitter.getUsername());
            pStmt.setString(2, spitter.getPassword());
            pStmt.setString(3, spitter.getFullName());
            pStmt.setLong(4, spitter.getUserId());
            pStmt.execute();

            LOG.debug("Spitter updated: {}", spitter);
        } catch (SQLException e) {
            LOG.error("Error when trying to update spitter " + spitter, e);
            throw new DAOException(e);
        } finally {
            try {
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                LOG.error("Error when closing connection", e);
                throw new DAOException(e);
            }
        }
    }

    @Override
    public Spitter getSpitterById(long id) throws DAOException {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(SQL_SELECT_SPITTER);
            pStmt.setLong(1, id);
            rs = pStmt.executeQuery();
            Spitter spitter = null;
            if (rs.next()) {
                spitter = new Spitter();
                spitter.setUserId(rs.getLong("user_id"));
                spitter.setUsername(rs.getString("username"));
                spitter.setPassword(rs.getString("password"));
                spitter.setFullName(rs.getString("fullname"));
            }

            LOG.debug("Spitter returned: {}", spitter);

            return spitter;
        } catch (SQLException e) {
            LOG.error("Erro when fetching spitter for id = " + id, e);
            throw new DAOException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                LOG.error("Error when closing connection", e);
                throw new DAOException(e);
            }
        }
    }
}

