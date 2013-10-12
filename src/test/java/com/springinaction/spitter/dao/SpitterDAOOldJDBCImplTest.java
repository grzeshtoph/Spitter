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
import static junit.framework.Assert.assertNull;
import static org.unitils.reflectionassert.ReflectionAssert.*;

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
        spitterDAO.addSpitter(new Spitter("gregsmorag", "gspassword123", "Grzegorz Smorąg"));
        spitterDAO.addSpitter(new Spitter("jimcarrey", "jcpassword", "Jim Carrey"));
    }

    @Test
    @ExpectedDataSet
    public void testSaveSpitter() throws Exception {
        spitterDAO.saveSpitter(new Spitter(1L, "johndoe-updated", "jdpassword-updated", "My John Doe"));
    }

    @Test
    public void testGetSpitterById() throws Exception {
        Spitter expectedSpitter = new Spitter(1L, "johndoe", "jdpassword", "John Doe");
        Spitter result = spitterDAO.getSpitterById(1L);
        assertReflectionEquals(expectedSpitter, result);
        assertNull(spitterDAO.getSpitterById(2L));
    }
}
