package com.springinaction.spitter.dao;

import com.springinaction.spitter.model.Spitter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import static junit.framework.Assert.assertNotNull;

/**
 * Test case for {@link SpitterDAOOldJDBCImpl}.
 */
@DataSet
@SpringApplicationContext("spring/test-application-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class SpitterDAOOldJDBCImplTest {
    @SpringBean("spitterDAOOldJDBC")
    private SpitterDAO spitterDAO;

    @Test
    public void testSpitterDAOExistence() throws Exception {
        assertNotNull(spitterDAO);
    }

    @Test
    @ExpectedDataSet
    public void testAddSpitter() throws Exception {
        spitterDAO.addSpitter(new Spitter("johndoe", "jdpassword", "John Doe"));
        spitterDAO.addSpitter(new Spitter("gregsmorag", "gspassword123", "Grzegorz Smorąg"));
        spitterDAO.addSpitter(new Spitter("jimcarrey", "jcpassword", "Jim Carrey"));
    }
}
