package com.griddynamics.lms.api.dao.ldap;

import com.griddynamics.lms.api.domain.object.employee.Employee;
import com.griddynamics.lms.api.domain.object.employee.lastsyncdate.LastSynchronizationTime;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Ldap User Dao Interface
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
public interface LDAPEmployeeDao {

    /**
     * Get Employees From Ldap
     *
     * @return Employees From Ldap
     */
    @NotNull
    List<Employee> getAllEmployeesFromLdap(final LastSynchronizationTime lastSynchronizationTime);

    @NotNull
    List<Employee> getAllRemovedEmployeesFromLdap(final LastSynchronizationTime lastSynchronizationTime);
}
