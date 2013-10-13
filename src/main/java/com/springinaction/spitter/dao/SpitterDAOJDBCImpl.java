package com.springinaction.spitter.dao;

import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.springinaction.spitter.dao.SQLStatements.*;

/**
 * The JDBC implements of {@link SpitterDAO}, making use of JDBC template.
 */
@Component("spitterDAOJDBC")
public class SpitterDAOJDBCImpl implements SpitterDAO {
    private static final Logger LOG = LoggerFactory.getLogger(SpitterDAOJDBCImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addSpitter(Spitter spitter) throws DAOException {
        jdbcTemplate.update(SQL_INSERT_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName());
        LOG.debug("New spitter added: {}", spitter);
    }

    @Override
    public void saveSpitter(Spitter spitter) throws DAOException {
        jdbcTemplate.update(SQL_UPDATE_SPITTER,
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFullName(),
                spitter.getUserId());
        LOG.debug("Spitter updated: {}", spitter);

    }

    @Override
    public Spitter getSpitter(String username) throws DAOException {
        Spitter spitter = jdbcTemplate.queryForObject(SQL_SELECT_SPITTER,
                new ParameterizedRowMapper<Spitter>() {
                    @Override
                    public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Spitter(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    }
                });
        LOG.debug("Spitter returned: {}", spitter);
        return spitter;
    }
}
