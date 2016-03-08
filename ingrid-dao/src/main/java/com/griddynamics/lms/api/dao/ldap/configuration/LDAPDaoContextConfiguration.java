package com.griddynamics.lms.api.dao.ldap.configuration;

import com.griddynamics.lms.api.dao.ldap.impl.LDAPLastModifyTimestampConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.ldap.authentication.SpringSecurityAuthenticationSource;

/**
 * LDAP Employee Dao Context Configuration
 *
 * @author Anton Kugurushev (akugurushev@griddynamics.com)
 */
@Configuration
@ComponentScan({"com.griddynamics.lms.api.dao.ldap.impl"})
public class LDAPDaoContextConfiguration {

    /**
     * LDAP Url
     */
    @Value("${ldap.url}")
    private String ldapUrl;

    /**
     * LDAP Base
     */
    @Value("${ldap.base}")
    private String ldapBase;

    /**
     * Used by LDAPLastModifyTimestampConverter
     */
    @Value("${ldap.lastSyncTimeTimeZone}")
    private String ldapLastModifyTimestampTimeZone;

    /**
     * Used by LDAPLastModifyTimestampConverter
     */
    @Value("${ldap.lastSyncTimeFormat}")
    private String ldapLastModifyTimestampFormat;

    /**
     * Create Spring Security Authentication Source
     *
     * @return Spring Security Authentication Source
     */
    @Bean
    public SpringSecurityAuthenticationSource springSecurityAuthenticationSource() {
        return new SpringSecurityAuthenticationSource();
    }

    /**
     * Create Ldap Context Source
     *
     * @return Ldap Context Source
     */
    @Bean
    public LdapContextSource ldapContextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(ldapUrl);
        ldapContextSource.setBase(ldapBase);
        ldapContextSource.setAnonymousReadOnly(true);
        return ldapContextSource;
    }

    /**
     * Create Ldap Template
     *
     * @param ldapContextSource Ldap Context Source
     * @return Ldap Template
     */
    @Bean
    public LdapTemplate ldapTemplate(LdapContextSource ldapContextSource) {
        return new LdapTemplate(ldapContextSource);
    }

    @Bean
    public LDAPLastModifyTimestampConverter ldapLastModifyTimestampConverter() {
        return new LDAPLastModifyTimestampConverter(ldapLastModifyTimestampFormat, ldapLastModifyTimestampTimeZone);
    }
}
