package com.griddynamics.lms.api.dao.database.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Dao Context Configuration
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Configuration
@Import({HibernateContextConfiguration.class})
public class DatabaseDaoContextConfiguration extends AbstractDatabaseDaoContextConfiguration {
}
