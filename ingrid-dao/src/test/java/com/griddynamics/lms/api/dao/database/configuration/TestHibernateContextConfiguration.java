package com.griddynamics.lms.api.dao.database.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Test Hibernate Context Configuration
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Configuration
@EnableTransactionManagement
public class TestHibernateContextConfiguration {

    /**
     * Create Data Source
     *
     * @return Data Source
     */
    public DataSource dataSource() {
        SingleConnectionDataSource singleConnectionDataSource = new SingleConnectionDataSource();
        singleConnectionDataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        singleConnectionDataSource.setUrl("jdbc:hsqldb:mem:trapo");
        singleConnectionDataSource.setUsername("sa");
        singleConnectionDataSource.setAutoCommit(false);
        singleConnectionDataSource.setSuppressClose(true);
        return singleConnectionDataSource;
    }

    /**
     * Create Hibernate Properties
     *
     * @return Properties Factory Bean
     */
    @Bean
    public PropertiesFactoryBean hibernateProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.bytecode.use_reflection_optimizer", true);
        properties.put("hibernate.jdbc.use_streams_for_binary", true);
        properties.put("hibernate.jdbc.charSet", "utf-8");
        properties.put("hibernate.connection.charSet", "utf-8");
        properties.put("hibernate.connection.useUnicode", true);
        properties.put("hibernate.connection.autoReconnect", true);
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.cache.use_query_cache", false);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.id.new_generator_mappings", true);
        propertiesFactoryBean.setProperties(properties);
        return propertiesFactoryBean;
    }

    /**
     * Create Session Factory
     *
     * @return Session Factory Bean
     */
    @Bean
    public LocalSessionFactoryBean testSessionFactory(Properties hibernateProperties) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.setAnnotatedPackages(new String[]{"com.griddynamics.lms.api.domain"});
        sessionFactoryBean.setPackagesToScan("com.griddynamics.lms.api.domain");
        return sessionFactoryBean;
    }

    /**
     * Create Transaction Manager
     *
     * @param testSessionFactory Hibernate Session Factory
     * @return Transaction Manager
     */
    /**
     * Create Transaction Manager
     *
     * @param sessionFactory Session Factory
     * @return Hibernate Transaction Manager
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
