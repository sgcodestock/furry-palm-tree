package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.framework.IGenericStringIdentifiedDomainDao;
import com.griddynamics.lms.api.dao.framework.Select;
import com.griddynamics.lms.api.dao.framework.impl.ormt.hibernate.naming.Naming;
import com.griddynamics.lms.api.domain.object.employee.feedback.FeedbackMessage;

import java.util.List;

/**
 * Feedback Dao Interface
 * Created by ituzikova on 29/06/15.
 */
public interface FeedbackDao extends IGenericStringIdentifiedDomainDao<FeedbackMessage> {

    @Select("FROM FeedbackMessage")
    List<FeedbackMessage> getMessages();

    @Select("SELECT DISTINCT fm FROM FeedbackMessage fm WHERE fm.id<:startIdMessage ORDER BY fm.id desc")
    List<FeedbackMessage> getMessages(@Naming ("startIdMessage") long startIdMessage,@Naming("length") int length);

    @Select("SELECT count(DISTINCT fm) FROM FeedbackMessage fm")
    long getMessagesCount();

    @Select("FROM FeedbackMessage fm WHERE fm.exportToJira IS NULL OR fm.exportToJira = false")
    List<FeedbackMessage> getNotExportedToJiraMsgs();
}
