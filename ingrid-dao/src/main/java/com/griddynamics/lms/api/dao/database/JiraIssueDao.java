package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.domain.object.employee.jira.issue.EmployeeMovedJiraIssue;

import java.util.Collection;
import java.util.List;

/**
 * @author Alexey Bakulin (abakulin@griddynamics.com)
 */
public interface JiraIssueDao extends IGenericStringIdentifiedDomainDao<EmployeeMovedJiraIssue> {
    @Select("FROM EmployeeMovedJiraIssue i WHERE i.jiraIssueCreated = FALSE OR i.jiraIssueCreated IS NULL")
    List<EmployeeMovedJiraIssue> findNotSendedIssues();
}
