package com.springinaction.spitter;

import com.springinaction.spitter.dao.SpitterDAO;
import com.springinaction.spitter.exceptions.DAOException;
import com.springinaction.spitter.model.Spitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Testing connection.
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) throws DAOException, SQLException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(args.length == 0 ?
                "/spring/application-context.xml" : args[0]);

        DataSource dataSource = ctx.getBean(DataSource.class);
        LOG.info("connected to {}", dataSource.getConnection().getMetaData().getURL());

        SpitterDAO spitterDAO = (SpitterDAO) ctx.getBean("spitterDAOOldJDBC");
        spitterDAO.addSpitter(new Spitter("johndoe", "johndoepass", "John Doe"));

        spitterDAO.saveSpitter(new Spitter(spitterDAO.getSpitter("johndoe").getUserId(), "johndoe1",
                "johndoepass1", "John Doe 1"));

        Spitter spitter = spitterDAO.getSpitter("johndoe1");
    }
}
