package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.object.Holiday;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by ylauresh on 8/11/15.
 */
public interface HolidayDao extends IGenericStringIdentifiedDomainDao<Holiday> {

    @Select("from Holiday h WHERE h.startDate >= :start_date ORDER BY h.startDate ASC")
    List<Holiday> findHolidaysFromDate(@Naming("start_date") LocalDate startDate);

    @Select("from Holiday h WHERE h.startDate BETWEEN :from_date AND :to_date ORDER BY h.startDate ASC")
    List<Holiday> findHolidaysFromDateToDate(@Naming("from_date") LocalDate fromDate, @Naming("to_date") LocalDate toDate);
}
