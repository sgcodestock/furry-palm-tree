package com.griddynamics.lms.api.dao.ldap.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;

/**
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
public abstract class AbstractLDAPDao {

    /**
     * Ldap Template
     */
    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private LDAPLastModifyTimestampConverter ldapLastModifyTimestampConverter;

    /**
     * Get Ldap Template
     *
     * @return Ldap Template
     */
    protected LdapTemplate getLdapTemplate() {
        return ldapTemplate;
    }

    protected LDAPLastModifyTimestampConverter getLdapLastModifyTimestampConverter() {
        return ldapLastModifyTimestampConverter;
    }

}
