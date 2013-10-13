package com.springinaction.spitter.dao;

import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.springinaction.spitter.dao.SQLStatements.*;

/**
 * The old JDBC implementation of {@link SpitterDAO}.
 */
@Component("spitterDAOOldJDBC")
public class SpitterDAOOldJDBCImpl implements SpitterDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SpitterDAOOldJDBCImpl.class);
    @Autowired
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
    public Spitter getSpitter(String username) throws DAOException {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            pStmt = conn.prepareStatement(SQL_SELECT_SPITTER);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            Spitter spitter = null;
            if (rs.next()) {
                spitter = new Spitter(rs.getLong("user_id"), rs.getString("username"), rs.getString("password"),
                        rs.getString("full_name"));
            }

            LOG.debug("Spitter returned: {}", spitter);

            return spitter;
        } catch (SQLException e) {
            LOG.error("Error when fetching spitter for username = " + username, e);
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

