package com.griddynamics.lms.api.dao.ldap.impl;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by pglogowski on 09/02/16.
 */
public class LDAPLastModifyTimestampConverter {
    private final DateFormat ldapDateFormat;

    public LDAPLastModifyTimestampConverter(final String ldapModifyLastSyncDateFormat, final String timeZoneId) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        ldapDateFormat = new SimpleDateFormat(ldapModifyLastSyncDateFormat);
        ldapDateFormat.setTimeZone(timeZone);
    }

    public String convertToLDAPModifyTimestampFormat(@NotNull final Date lastSynchronizationTime) {
        return ldapDateFormat.format(lastSynchronizationTime);
    }

}
