package com.griddynamics.lms.api.dao.database.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Test Dao Context Configuration
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Configuration
@Import({TestHibernateContextConfiguration.class})
@ComponentScan(basePackages = {"com.griddynamics.lms.api.dao.database.test",
        "com.griddynamics.lms.api.dao.framework.tw"})
public class TestDatabaseDaoContextConfiguration extends AbstractDatabaseDaoContextConfiguration {
}
