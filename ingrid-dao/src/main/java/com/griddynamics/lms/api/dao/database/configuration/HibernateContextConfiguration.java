package com.griddynamics.lms.api.dao.database.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Hibernate Context Configuration
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Configuration
@EnableTransactionManagement
public class HibernateContextConfiguration {

    /**
     * Driver Class
     */
    @Value("${lms.driverClass}")
    private String driverClass;

    /**
     * JDBC Url
     */
    @Value("${lms.url}")
    private String jdbcUrl;

    /**
     * User Name
     */
    @Value("${lms.user}")
    private String userName;

    /**
     * User Password
     */
    @Value("${lms.password}")
    private String userPassword;

    @Value("${lms.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${lms.minPoolSize}")
    private Integer minPoolSize;

    @Value("${lms.initialPoolSize}")
    private Integer initialPoolSize;

    @Value("${lms.maxStatements}")
    private Integer maxStatements;

    @Value("${lms.maxStatementsPerConnection}")
    private Integer maxStatementsPerConnection;

    @Value("${lms.maxIdleTime}")
    private Integer maxIdleTime;

    @Value("${lms.maxConnectionAge}")
    private Integer maxConnectionAge;

    @Value("${lms.autoCommitOnClose}")
    private Boolean autoCommitOnClose;

    @Value("${lms.preferredTestQuery}")
    private String preferredTestQuery;

    @Value("${lms.testConnectionOnCheckout}")
    private Boolean testConnectionOnCheckout;

    @Value("${lms.testConnectionOnCheckin}")
    private Boolean testConnectionOnCheckIn;

    @Value("${lms.checkoutTimeout}")
    private Integer checkoutTimeout;

    @Value("${lms.idleConnectionTestPeriod}")
    private Integer idleConnectionTestPeriod;

    @Value("${lms.acquireIncrement}")
    private Integer acquireIncrement;

    @Value("${lms.acquireRetryAttempts}")
    private Integer acquireRetryAttempts;

    @Value("${lms.acquireRetryDelay}")
    private Integer acquireRetryDelay;

    @Value("${lms.breakAfterAcquireFailure}")
    private Boolean breakAfterAcquireFailure;

    @Value("${lms.unreturnedConnectionTimeout}")
    private Integer unReturnedConnectionTimeout;

    @Value("${lms.debugUnreturnedConnectionStackTraces}")
    private Boolean debugUnReturnedConnectionStackTraces;

    @Value("${lms.numHelperThreads}")
    private Integer numHelperThreads;

    @Value("${lms.maxAdministrativeTaskTime}")
    private Integer maxAdministrativeTaskTime;

    @Value("${lms.databaseDialect}")
    private String hibernateDialect;

    @Value("${lms.showSQL}")
    private Boolean hibernateShowSql;

    @Value("${lms.formatSQL}")
    private Boolean hibernateFormatSql;

    @Value("${lms.ddlAuto}")
    private String hibernateHbm2ddlAuto;

    @Value("${lms.order_inserts}")
    private Boolean hibernateOrderInserts;

    @Value("${lms.order_updates}")
    private Boolean hibernateOrderUpdates;

    @Value("${lms.jdbc.batch_size}")
    private Integer hibernateJdbcBatchSize;

    @Value("${lms.default_schema}")
    private String hibernateDefaultSchema;

    /**
     * Create Data Source
     *
     * @return Combo Pooled Data Source
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driverClass);
        comboPooledDataSource.setJdbcUrl(jdbcUrl);

        Properties properties = new Properties();
        properties.put("user", userName);
        properties.put("password", userPassword);
        comboPooledDataSource.setProperties(properties);

        comboPooledDataSource.setMaxPoolSize(maxPoolSize);
        comboPooledDataSource.setMinPoolSize(minPoolSize);
        comboPooledDataSource.setInitialPoolSize(initialPoolSize);
        comboPooledDataSource.setMaxStatements(maxStatements);
        comboPooledDataSource.setMaxStatementsPerConnection(maxStatementsPerConnection);
        comboPooledDataSource.setMaxIdleTime(maxIdleTime);
        comboPooledDataSource.setMaxConnectionAge(maxConnectionAge);
        comboPooledDataSource.setAutoCommitOnClose(autoCommitOnClose);
        comboPooledDataSource.setPreferredTestQuery(preferredTestQuery);
        comboPooledDataSource.setTestConnectionOnCheckout(testConnectionOnCheckout);
        comboPooledDataSource.setTestConnectionOnCheckin(testConnectionOnCheckIn);
        comboPooledDataSource.setCheckoutTimeout(checkoutTimeout);
        comboPooledDataSource.setIdleConnectionTestPeriod(idleConnectionTestPeriod);
        comboPooledDataSource.setAcquireIncrement(acquireIncrement);
        comboPooledDataSource.setAcquireRetryAttempts(acquireRetryAttempts);
        comboPooledDataSource.setBreakAfterAcquireFailure(breakAfterAcquireFailure);
        comboPooledDataSource.setUnreturnedConnectionTimeout(unReturnedConnectionTimeout);
        comboPooledDataSource.setDebugUnreturnedConnectionStackTraces(debugUnReturnedConnectionStackTraces);
        comboPooledDataSource.setNumHelperThreads(numHelperThreads);
        comboPooledDataSource.setMaxAdministrativeTaskTime(maxAdministrativeTaskTime);
        return comboPooledDataSource;
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
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.bytecode.use_reflection_optimizer", true);
        properties.put("hibernate.jdbc.use_scrollable_resultset", true);
        properties.put("hibernate.jdbc.use_streams_for_binary", true);
        properties.put("hibernate.jdbc.charSet", "utf-8");
        properties.put("hibernate.connection.charSet", "utf-8");
        properties.put("hibernate.connection.useUnicode", true);
        properties.put("hibernate.connection.autoReconnect", true);
        properties.put("hibernate.cache.use_second_level_cache", false);
        properties.put("hibernate.cache.use_query_cache", false);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.order_inserts", hibernateOrderInserts);
        properties.put("hibernate.order_updates", hibernateOrderUpdates);
        properties.put("hibernate.jdbc.batch_size", hibernateJdbcBatchSize);
        properties.put("hibernate.id.new_generator_mappings", true);
        properties.put("hibernate.jdbc.wrap_result_sets", true);
        properties.put("hibernate.statement_cache.size", 0);
        properties.put("hibernate.default_schema", hibernateDefaultSchema);
        propertiesFactoryBean.setProperties(properties);
        return propertiesFactoryBean;
    }

    /**
     * Create Session Factory
     *
     * @param hibernateProperties Hibernate Properties
     * @return Local Session Factory Bean
     */
    @Bean
    @DependsOn("liquibase")
    public LocalSessionFactoryBean sessionFactory(Properties hibernateProperties) throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setAnnotatedPackages(new String[]{
                "com.griddynamics.lms.api.domain",
                "com.griddynamics.lms.feedback"
        });
        sessionFactoryBean.setPackagesToScan("com.griddynamics.lms.api.domain",
                "com.griddynamics.lms.feedback");
        return sessionFactoryBean;
    }

    /**
     * Hibernate template configuration.
     *
     * @param sessionFactory Session factory.
     * @return bean.
     * @throws Exception When properties initialization was failed.
     */
    @Bean
    public HibernateTemplate hibernateTemplate(SessionFactory sessionFactory) throws Exception {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        hibernateTemplate.setCacheQueries(true);
        return hibernateTemplate;
    }

    /**
     * Create Transaction Manager
     *
     * @param sessionFactory Hibernate Session Factory
     * @return Transaction Manager
     */
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
