package com.springinaction.spitter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext("spring/test-application-context.xml")
@RunWith(UnitilsJUnit4TestClassRunner.class)
public class MainTest {
    @Test
    public void testRunMain() throws Exception {
        Main.main();
    }
}
