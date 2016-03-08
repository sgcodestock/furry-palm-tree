package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.object.employee.photo.Photo;

import java.util.Set;

public interface PhotoDao extends IGenericStringIdentifiedDomainDao<Photo>  {

    @Select("FROM Photo p WHERE p.fileName = :fileName")
    Photo findPhotoByFileName(@Naming("fileName") String fileName);

    @Select("SELECT p.fileName FROM Photo p WHERE p.ldapName = :ldapName")
    Set<String> findPhotosByLdapName(@Naming("ldapName") String ldapName);
}
