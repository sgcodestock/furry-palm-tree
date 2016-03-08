package com.griddynamics.lms.api.dao.database;

import com.griddynamics.lms.api.dao.database.configuration.TestDatabaseDaoContextConfiguration;
import com.griddynamics.lms.api.dao.framework.tw.TWRunnable;
import com.griddynamics.lms.api.dao.framework.tw.TransactionWrapper;
import com.griddynamics.lms.api.domain.object.employee.lastsyncdate.LastSynchronizationTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by pglogowski on 08/02/16.
 */
@ContextConfiguration(classes = {TestDatabaseDaoContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LastSynchronizationTimeDaoTest {

    @Autowired
    private LastSynchronizationTimeDao underTest;

    @Autowired
    protected TransactionWrapper transactionWrapper;

    @Test
    public void testSaveAndGetLastSynchronizationTime() throws ExecutionException, InterruptedException {
        transactionWrapper.run(new TWRunnable<Object>() {
            @Override
            public Object run() throws InterruptedException {
                Date firstPersistExpectedDate = new Date(1451637045000L);
                Date afterUpdateExpectedDate = new Date(1451637046000L);

                // Start with no instance in DB.
                Assert.assertTrue(underTest.findAll().isEmpty());

                // Add new instance
                LastSynchronizationTime toPersist = new LastSynchronizationTime();
                toPersist.setLastSynchronizeTime(firstPersistExpectedDate);
                underTest.makePersistent(toPersist);

                // One instance on database
                List<LastSynchronizationTime> lastSynchronizationTimes = underTest.findAll();
                Assert.assertFalse(lastSynchronizationTimes.isEmpty());
                Assert.assertEquals("Date retrieved from DB is different than expected !",
                        firstPersistExpectedDate,
                        lastSynchronizationTimes.iterator().next().getLastSynchronizeTime());

                // Update instance
                LastSynchronizationTime lastSynchronizationTime = underTest.findAll().iterator().next();
                lastSynchronizationTime.setLastSynchronizeTime(afterUpdateExpectedDate);

                Assert.assertEquals("Date retrieved from DB after update is different than expected !",
                        afterUpdateExpectedDate,
                        lastSynchronizationTimes.iterator().next().getLastSynchronizeTime());

                return null;
            }


        });
    }

}
