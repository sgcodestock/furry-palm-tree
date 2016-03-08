package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.object.Holiday;
import com.griddynamics.lms.api.domain.object.employee.location.City;
import com.griddynamics.lms.api.domain.object.employee.vacation.EmployeeVacation;

import java.util.Date;
import java.util.List;

/**
 * Created by ylauresh on 9/3/15.
 */
public interface EmployeeVacationDao extends IGenericStringIdentifiedDomainDao<EmployeeVacation> {

    @Select("from EmployeeVacation ev WHERE ev.employee.locationInfo.city = :city AND ev.endDate >= :date ORDER BY ev.startDate ASC")
    List<EmployeeVacation> findEmplVacationFromDate(@Naming("date") Date date, @Naming("city") City city);
}
