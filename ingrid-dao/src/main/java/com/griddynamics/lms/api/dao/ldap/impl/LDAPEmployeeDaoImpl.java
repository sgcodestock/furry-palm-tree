package com.griddynamics.lms.api.dao.ldap.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.griddynamics.lms.api.dao.ldap.LDAPEmployeeDao;
import com.griddynamics.lms.api.domain.object.employee.Employee;
import com.griddynamics.lms.api.domain.object.employee.lastsyncdate.LastSynchronizationTime;
import com.griddynamics.lms.api.domain.object.employee.location.City;
import com.griddynamics.lms.api.domain.object.employee.location.Floor;
import com.griddynamics.lms.api.domain.object.employee.location.LocationInfo;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Ldap Employee Dao Implementation
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Component(value = "ldapEmployeeDao")
public class LDAPEmployeeDaoImpl extends AbstractLDAPDao implements LDAPEmployeeDao {

    private static final String EMPTY_BASE_DOMAIN = "";

    private static final String SEARCH_EXISTING_USERS = "(&(objectClass=person)(l=*)(!(cn=*[deleted]*))<mod_timestamp>)";

    private static final String SEARCH_NON_EXISTING_USERS = "(&(objectClass=person)(l=*)(cn=*[deleted]*)<mod_timestamp>)";

    private final AttributesMapper employeeAttributeMapper = new EmployeeAttributeMapper();

    /**
     * Get Employees From Ldap
     *
     * @return Employees From Ldap
     */
    @NotNull
    @Override
    public List<Employee> getAllEmployeesFromLdap(final LastSynchronizationTime lastSynchronizationTime) {
        String ldapSearchQuery = prepareLdapQuery(true, lastSynchronizationTime);
        List employees = getLdapTemplate().search(EMPTY_BASE_DOMAIN, ldapSearchQuery, employeeAttributeMapper);

        return filterOutEmployeesWithoutEmployeeType(employees);
    }

    @Override
    public List<Employee> getAllRemovedEmployeesFromLdap(final LastSynchronizationTime lastSynchronizationTime) {
        String ldapSearchQuery = prepareLdapQuery(false, lastSynchronizationTime);
        List employees = getLdapTemplate().search(EMPTY_BASE_DOMAIN, ldapSearchQuery, employeeAttributeMapper);

        return filterOutEmployeesWithoutEmployeeType(employees);
    }

    private List<Employee> filterOutEmployeesWithoutEmployeeType(List<Employee> employees) {
        return new ArrayList<>(Collections2.filter(employees, new Predicate<Employee>() {
            @Override
            public boolean apply(Employee o) {
                return StringUtils.isNotEmpty(o.getEmployeeType());
            }
        }));
    }

    private String prepareLdapQuery(final boolean forExistingUsers, final LastSynchronizationTime lastSynchronizationTime) {
        String modifyTimestamp = lastSynchronizationTime == null ? "" :
                getLdapLastModifyTimestampConverter().convertToLDAPModifyTimestampFormat(lastSynchronizationTime.getLastSynchronizeTime());
        String modifiedTimestampReplacement = modifyTimestamp.isEmpty() ? "" : "(modifyTimestamp>=" + modifyTimestamp + ")";
        return forExistingUsers ? SEARCH_EXISTING_USERS.replace("<mod_timestamp>", modifiedTimestampReplacement) : SEARCH_NON_EXISTING_USERS.replace("<mod_timestamp>", modifiedTimestampReplacement);

    }

    private class EmployeeAttributeMapper implements AttributesMapper {
        @Override
        public Object mapFromAttributes(Attributes attributes) throws NamingException {
            String uid = parseAttribute(attributes, "uid");
            String sn = parseAttribute(attributes, "sn");
            String givenName = parseAttribute(attributes, "givenName");
            String mail = parseAttribute(attributes, "mail");
            String skypeId = parseAttribute(attributes, "gdcskypeid");
            String employeeType = parseAttribute(attributes, "employeetype");
            String city = parseAttribute(attributes, "l");
            return buildEmployee(uid, sn, givenName, mail, skypeId, employeeType, city);
        }

        /**
         * Build Employee Fro Ldap Data
         */
        private Employee buildEmployee(String uid, String sn, String givenName, String mail, String skypeId, String employeeType, String city) {
            Employee employee = new Employee();
            employee.setLdapName(uid);
            employee.setFirstName(getValueIfCurrentEmpty(givenName));
            employee.setLastName(getValueIfCurrentEmpty(sn));
            employee.setMail(getValueIfCurrentEmpty(mail));
            employee.setSkypeId(getValueIfCurrentEmpty(skypeId));
            employee.setEmployeeType(getValueIfCurrentEmpty(employeeType));
            City c = City.convertFromGDLDAP(city);
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setCity(c);
            locationInfo.setFloor(Floor.getDefaultFloorByCity(c));
            employee.setLocationInfo(locationInfo);
            return employee;
        }

        /**
         * Parse Attribute From LDAP
         *
         * @param attributes    Attributes From LDAP
         * @param attributeName Attribute Name
         * @return Attribute String From LDAP
         */
        private String parseAttribute(Attributes attributes, String attributeName) throws NamingException {
            Attribute attribute = attributes.get(attributeName);
            if (attribute != null) {
                return (String) attribute.get();
            }
            return null;
        }

        /**
         * Get Value If Current Empty
         *
         * @param currentValue Current Value
         */
        private String getValueIfCurrentEmpty(String currentValue) {
            return (currentValue == null || currentValue.trim().isEmpty()) ? "" : currentValue;
        }
    }
}
