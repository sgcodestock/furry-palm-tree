package com.griddynamics.lms.api.dao.database.configuration;

import com.griddynamics.lms.api.dao.database.*;
import com.griddynamics.lms.api.dao.framework.SessionAdapter;
import com.griddynamics.lms.api.dao.framework.impl.operations.OperationIntroductionAdvisor;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.BasicDaoImpl;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.SessionAdapter4;
import com.griddynamics.lms.api.domain.object.*;
import com.griddynamics.lms.api.domain.object.employee.Employee;
import com.griddynamics.lms.api.domain.object.employee.feedback.FeedbackMessage;
import com.griddynamics.lms.api.domain.object.employee.jira.issue.EmployeeMovedJiraIssue;
import com.griddynamics.lms.api.domain.object.employee.lastsyncdate.LastSynchronizationTime;
import com.griddynamics.lms.api.domain.object.employee.location.BackupLocationInfo;
import com.griddynamics.lms.api.domain.object.employee.location.Room;
import com.griddynamics.lms.api.domain.object.employee.photo.Photo;
import com.griddynamics.lms.api.domain.object.employee.vacation.EmployeeVacation;
import com.griddynamics.lms.api.domain.system.permissions.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Abstract Database Dao Context Configuration
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Configuration
public class AbstractDatabaseDaoContextConfiguration {

    /**
     * Create Session Adapter
     *
     * @param sessionFactory Session Factory
     * @return Session Adapter
     */
    @Bean
    public SessionAdapter sessionAdapter(SessionFactory sessionFactory) {
        SessionAdapter4 sessionAdapter = new SessionAdapter4();
        sessionAdapter.setSessionFactory(sessionFactory);
        return sessionAdapter;
    }

    /**
     * Create Operation Introduction Advisor
     *
     * @return Operation Introduction Advisor
     */
    @Bean
    public OperationIntroductionAdvisor operationIntroductionAdvisor() {
        return new OperationIntroductionAdvisor();
    }

    /**
     * Create Database Employee Dao
     *
     * @param sessionAdapter Session Adapter
     * @return Proxy Factory Bean For Database Employee Dao
     */
    @Bean
    @Autowired
    public DatabaseEmployeeDao databaseEmployeeDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{DatabaseEmployeeDao.class});
        BasicDaoImpl<Employee, String> basicDao = new BasicDaoImpl<>(Employee.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (DatabaseEmployeeDao) proxyFactoryBean.getObject();
    }

    /**
     * Create Backup Location Info Dao
     *
     * @param sessionAdapter Session Adapter
     * @return Backup Location Info Dao
     */
    @Bean
    @Autowired
    public BackupLocationInfoDao backupLocationInfoDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{BackupLocationInfoDao.class});
        BasicDaoImpl<BackupLocationInfo, String> basicDao = new BasicDaoImpl<>(BackupLocationInfo.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (BackupLocationInfoDao) proxyFactoryBean.getObject();
    }

    /**
     * Create Permission Dao
     *
     * @param sessionAdapter Session Adapter
     * @return Feedback Dao
     */
    @Bean
    @Autowired
    public FeedbackDao feedbackDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{FeedbackDao.class});
        BasicDaoImpl<FeedbackMessage, String> basicDao = new BasicDaoImpl<>(FeedbackMessage.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (FeedbackDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public PhotoDao photoDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{PhotoDao.class});
        BasicDaoImpl<Photo, String> basicDao = new BasicDaoImpl<>(Photo.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (PhotoDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public RoomDao roomDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{RoomDao.class});
        BasicDaoImpl<Room, String> basicDao = new BasicDaoImpl<>(Room.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (RoomDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public HolidayDao holidayDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{HolidayDao.class});
        BasicDaoImpl<Holiday, String> basicDao = new BasicDaoImpl<>(Holiday.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (HolidayDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public EmployeeVacationDao employeeVacationDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{EmployeeVacationDao.class});
        BasicDaoImpl<EmployeeVacation, String> basicDao = new BasicDaoImpl<>(EmployeeVacation.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (EmployeeVacationDao) proxyFactoryBean.getObject();

    }

    @Bean
    @Autowired
    public UserRoleDao userRoleDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{UserRoleDao.class});
        BasicDaoImpl<UserRole, String> basicDao = new BasicDaoImpl<>(UserRole.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (UserRoleDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public AccountDao accountDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{AccountDao.class});
        BasicDaoImpl<Account, String> basicDao = new BasicDaoImpl<>(Account.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (AccountDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public ResourceDao resourceDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{ResourceDao.class});
        BasicDaoImpl<Resource, String> basicDao = new BasicDaoImpl<>(Resource.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (ResourceDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public FaqDao faqDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{FaqDao.class});
        BasicDaoImpl<Faq, String> basicDao = new BasicDaoImpl<>(Faq.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (FaqDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public ImageDao imageDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{ImageDao.class});
        BasicDaoImpl<Image, String> basicDao = new BasicDaoImpl<>(Image.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (ImageDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public JiraIssueDao jiraIssueDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{JiraIssueDao.class});
        BasicDaoImpl<EmployeeMovedJiraIssue, String> basicDao = new BasicDaoImpl<>(EmployeeMovedJiraIssue.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (JiraIssueDao) proxyFactoryBean.getObject();
    }

    @Bean
    @Autowired
    public LastSynchronizationTimeDao lastSynchronizationTimeDao(SessionAdapter sessionAdapter, BeanFactory beanFactory) throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setInterceptorNames(new String[]{"operationIntroductionAdvisor"});
        proxyFactoryBean.setProxyInterfaces(new Class[]{LastSynchronizationTimeDao.class});
        BasicDaoImpl<LastSynchronizationTime, String> basicDao = new BasicDaoImpl<>(LastSynchronizationTime.class);
        basicDao.setSessionAdapter(sessionAdapter);
        proxyFactoryBean.setTarget(basicDao);
        proxyFactoryBean.setBeanFactory(beanFactory);
        return (LastSynchronizationTimeDao) proxyFactoryBean.getObject();
    }

}
