package com.springinaction.spitter.dao;

import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;
import org.unitils.mock.MockUnitils;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.unitils.mock.ArgumentMatchers.any;
import static org.unitils.mock.ArgumentMatchers.anyInt;

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

    @Test(expected = DAOException.class)
    public void testAddSpitterSQLExceptionThrown() throws Exception {
        dataSourceMock.raises(new SQLException("test exception")).getConnection();

        spitterDAO.addSpitter(new Spitter("johndoe", "jdpassword", "John Doe"));

        dataSourceMock.assertInvoked().getConnection();
        MockUnitils.assertNoMoreInvocations();
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
        MockUnitils.assertNoMoreInvocations();
    }
}
