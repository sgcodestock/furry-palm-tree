package com.griddynamics.lms.api.dao.database.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Liquibase Context Configuration
 *
 * Created by ylauresh on 7/21/15.
 */
@Configuration
public class LiquibaseContextConfiguration {

    @Value("${liquibase.contexts}")
    private String liquibaseContexts;

    @Value("${liquibase.changelog}")
    private String liquibaseChangelog;

    @Autowired
    DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() throws PropertyVetoException {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(liquibaseChangelog);
        liquibase.setContexts(liquibaseContexts);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
