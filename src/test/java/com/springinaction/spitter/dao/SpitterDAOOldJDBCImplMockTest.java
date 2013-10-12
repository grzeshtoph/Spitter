package com.springinaction.spitter.dao;

import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertNull;
import static org.unitils.mock.ArgumentMatchers.any;
import static org.unitils.mock.ArgumentMatchers.anyInt;
import static org.unitils.mock.ArgumentMatchers.anyLong;

/**
 * Mocked test case for {@link SpitterDAOOldJDBCImpl} to test special test cases.
 */
@SpringApplicationContext("spring/test-application-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class SpitterDAOOldJDBCImplMockTest {
    @TestedObject
    @SpringBean("spitterDAOOldJDBC")
    private SpitterDAO spitterDAO;
    @InjectIntoByType
    private Mock<DataSource> dataSourceMock;
    private Mock<Connection> connectionMock;
    private Mock<PreparedStatement> preparedStatementMock;
    private Mock<ResultSet> resultSetMock;

    @Test(expected = DAOException.class)
    public void testAddSpitterSQLExceptionThrown() throws Exception {
        dataSourceMock.raises(new SQLException("test exception")).getConnection();

        spitterDAO.addSpitter(new Spitter("johndoe", "jdpassword", "John Doe"));

        dataSourceMock.assertInvoked().getConnection();
    }

    @Test(expected = DAOException.class)
    public void testAddSpitterSQLExceptionFromFinallyThrown() throws Exception {
        dataSourceMock.returns(connectionMock).getConnection();
        connectionMock.returns(preparedStatementMock).prepareStatement(any(String.class));
        connectionMock.raises(new SQLException("test exception")).close();

        spitterDAO.addSpitter(new Spitter("johndoe", "jdpassword", "John Doe"));

        dataSourceMock.assertInvokedInSequence().getConnection();
        connectionMock.assertInvokedInSequence().prepareStatement(any(String.class));
        preparedStatementMock.assertInvokedInSequence().setString(anyInt(), any(String.class));
        preparedStatementMock.assertInvokedInSequence().setString(anyInt(), any(String.class));
        preparedStatementMock.assertInvokedInSequence().setString(anyInt(), any(String.class));
        preparedStatementMock.assertInvokedInSequence().execute();
        preparedStatementMock.assertInvokedInSequence().close();
        connectionMock.assertInvokedInSequence().close();
    }

    @Test(expected = DAOException.class)
    public void testSaveSpitterSQLExceptionThrown() throws Exception {
        dataSourceMock.raises(new SQLException("test exception")).getConnection();

        spitterDAO.saveSpitter(null);

        dataSourceMock.assertInvoked().getConnection();
    }

    @Test(expected = DAOException.class)
    public void testSaveSpitterSQLExceptionFromFinallyThrown() throws Exception {
        dataSourceMock.returns(connectionMock).getConnection();
        connectionMock.returns(preparedStatementMock).prepareStatement(any(String.class));
        connectionMock.raises(new SQLException("test exception")).close();

        spitterDAO.saveSpitter(new Spitter(null, null, null));

        dataSourceMock.assertInvokedInSequence().getConnection();
        connectionMock.assertInvokedInSequence().prepareStatement(any(String.class));
        preparedStatementMock.assertInvokedInSequence().setString(anyInt(), any(String.class));
        preparedStatementMock.assertInvokedInSequence().setString(anyInt(), any(String.class));
        preparedStatementMock.assertInvokedInSequence().setString(anyInt(), any(String.class));
        preparedStatementMock.assertInvokedInSequence().execute();
        preparedStatementMock.assertInvokedInSequence().close();
        connectionMock.assertInvokedInSequence().close();
    }

    @Test(expected = DAOException.class)
    public void testGetSpitterByIdSQLExceptionThrown() throws Exception {
        dataSourceMock.raises(new SQLException("test exception")).getConnection();

        spitterDAO.getSpitterById(1L);

        dataSourceMock.assertInvoked().getConnection();
    }

    @Test(expected = DAOException.class)
    public void testGetSpitterByIdSQLExceptionFromFinallyThrown() throws Exception {
        dataSourceMock.returns(connectionMock).getConnection();
        connectionMock.returns(preparedStatementMock).prepareStatement(any(String.class));
        preparedStatementMock.returns(resultSetMock).executeQuery();
        resultSetMock.returns(false).next();
        connectionMock.raises(new SQLException("test exception")).close();

        assertNull(spitterDAO.getSpitterById(1L));

        dataSourceMock.assertInvokedInSequence().getConnection();
        connectionMock.assertInvokedInSequence().prepareStatement(any(String.class));
        preparedStatementMock.assertInvokedInSequence().setLong(anyInt(), anyLong());
        preparedStatementMock.assertInvokedInSequence().executeQuery();
        resultSetMock.assertInvokedInSequence().close();
        preparedStatementMock.assertInvokedInSequence().close();
        connectionMock.assertInvokedInSequence().close();
    }
}
