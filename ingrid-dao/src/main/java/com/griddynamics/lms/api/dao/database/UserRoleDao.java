package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.system.permissions.Role;
import com.griddynamics.lms.api.domain.system.permissions.UserRole;

public interface UserRoleDao extends IGenericStringIdentifiedDomainDao<UserRole> {

    @Select("FROM UserRole ur WHERE ur.role = :role AND ur.employee.ldapName = :ldapName")
    UserRole findUserRoleByEmployeeAndRole(@Naming("ldapName") String ldapName,
                                           @Naming("role") Role role);


}
