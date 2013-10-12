package com.springinaction.spitter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;

public class MainTest {
    @Test
    public void testRunMain() throws Exception {
        Main.main("/spring/test-application-context.xml");
    }
}
