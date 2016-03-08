package com.griddynamics.lms.api.dao.ldap.impl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by pglogowski on 09/02/16.
 */
public class LDAPLastModifyTimestampConverterTest {
    @Test
    public void convertDateToLDAPLastModifyTimestamp() {
        LDAPLastModifyTimestampConverter underTest = new LDAPLastModifyTimestampConverter("YYYYMMddHHmmss'Z'", "GMT");
        Date testDate = getDateWithTimezone(TimeZone.getTimeZone("US/Eastern"), 2016, Calendar.FEBRUARY, 8, 1, 24, 29);

        Assert.assertEquals("Generated LDAP date is wrong !", "20160208062429Z", underTest.convertToLDAPModifyTimestampFormat(testDate));
    }


    private Date getDateWithTimezone(TimeZone timeZone, int year, int month, int dayOfMonth, int hour, int minutes, int seconds) {
        Calendar result = Calendar.getInstance(timeZone);
        result.set(Calendar.YEAR, year);
        result.set(Calendar.MONTH, month);
        result.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        result.set(Calendar.HOUR_OF_DAY, hour);
        result.set(Calendar.MINUTE, minutes);
        result.set(Calendar.SECOND, seconds);

        Date d = result.getTime();

        return result.getTime();
    }

}
