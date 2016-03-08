package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.Update;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.object.employee.Employee;
import com.griddynamics.lms.api.domain.object.employee.location.City;
import com.griddynamics.lms.api.domain.system.permissions.UserRole;

import java.util.List;
import java.util.Set;

/**
 * Employee Dao Interface
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
public interface DatabaseEmployeeDao extends IGenericStringIdentifiedDomainDao<Employee> {

    /**
     * Find Dependent Employees
     *
     * @param ldapName ldap Name
     * @return Depended Employees for Employee with LDAP Name
     */
    @Select("SELECT e.employees FROM Employee e WHERE e.ldapName = :ldapName")
    List<Employee> findDependentEmployees(@Naming("ldapName") String ldapName);

    /**
     * Find Employee For LDAP Name
     *
     * @param ldapName ldap Name
     * @return Employee For LDAP Name
     */
    @Select("FROM Employee e WHERE e.ldapName = :ldapName")
    Employee findEmployeeByLDAPName(@Naming("ldapName") String ldapName);

    /**
     * Find Employees With given list of ldapNames
     *
     * @param ldapNames List of LDAP Names
     * @return All Employees With given LDAPNames
     */
    @Select("FROM Employee e WHERE e.ldapName IN (:ldapNames)")
    List<Employee> findEmployeesFromLDAPNames(@Naming("ldapNames") List<String> ldapNames);

    /**
     * Find All Employees From City
     *
     * @param city City
     * @return All Employees From City
     */
    @Select("FROM Employee e WHERE e.locationInfo IS NOT NULL AND e.locationInfo.city = :city")
    List<Employee> findAllEmployeesFromCity(@Naming("city") City city);
    
    /**
     * Find All Employees From City
     *
     * @param cities City
     * @return All Employees From Several Cities
     */
    @Select("FROM Employee e WHERE e.locationInfo IS NOT NULL AND e.locationInfo.city IN (:cities)")
    List<Employee> findAllEmployeesFromSeveralCities(@Naming("cities") List<City> cities);


    /**
     * Update the role of the user by ldap name
     *
     * @param ldapname ldapName
     * @param rights rights
     */

    @Update("permission_aggregators_and_permissions \n" +
            "SET permission_id=:rights\n" +
            "FROM employee, permission_aggregator\n" +
            "WHERE employee.ldap_name=:ldapname AND \n" +
            "employee.id = permission_aggregator.employee_id AND \n" +
            "permission_aggregators_and_permissions.permission_aggregator_id = permission_aggregator.id")
    void updateUserByRightsByLdapname (@Naming("ldapname") String ldapname, @Naming("rights") String rights);

    @Select("SELECT e.userRoles FROM Employee e WHERE e.ldapName = :ldapName")
    Set<UserRole> getUserRoles(@Naming("ldapName") String ldapName);

    @Select("SELECT elements(e.projects) FROM Employee e WHERE e.ldapName = :ldapName")
    Set<String> getProjects(@Naming("ldapName") String ldapName);

    @Select("SELECT elements(e.accounts) FROM Employee e WHERE e.ldapName = :ldapName")
    Set<String> getAccounts(@Naming("ldapName") String ldapName);

    @Select("FROM Employee e " +
            "INNER JOIN FETCH e.locationInfo l " +
            "INNER JOIN FETCH l.employeeMovedJiraIssue i " +
            "WHERE " +
            "i.jiraIssueCreated = :jiraIssueCreated")
    List<Employee> findEmployeesByJiraIssueCreatedStatus(@Naming("jiraIssueCreated") Boolean jiraIssueCreated);

}
