package jobs.Test;


import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import jobs.Application;
import jobs.ApplicationDAO;
import org.junit.jupiter.api.*;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationDaoTest {

    private MysqlConnectionPoolDataSource datasource;
    private ApplicationDAO appDao;

    Application application1;
    Application application2;
    Application application3;

    @BeforeAll
    void setup(){
        datasource=new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME_TEST);
        appDao = new ApplicationDAO(datasource);
        initializeTestApplications();
    }

    @Test
    void test1(){
        Assertions.assertEquals(0, appDao.getApplications().size());
        Assertions.assertEquals(0, appDao.getApplicationsForJob(12).size());
        Assertions.assertEquals(0, appDao.getApplicationsByEmployee("someone").size());

        appDao.addApplication(application1);

        //test if application is added at all
        Assertions.assertEquals(1, appDao.getApplications().size());
        //test if it is added on right job
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application1.getJobId()).size());
        //test if it is added with right employee
        Assertions.assertEquals(1, appDao.getApplicationsByEmployee(application1.getEmployee()).size());
        Assertions.assertEquals(0, appDao.getApplicationsByEmployee("gela").size());
        Assertions.assertEquals(0, appDao.getApplicationsForJob(12).size());

        appDao.addApplication(application2);
        Assertions.assertEquals(2, appDao.getApplications().size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application2.getJobId()).size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application1.getJobId()).size());
        Assertions.assertEquals(2,appDao.getApplicationsByEmployee("soso").size());
        Assertions.assertEquals(0, appDao.getApplicationsByEmployee("someone").size());

        appDao.addApplication(application3);
        Assertions.assertEquals(3, appDao.getApplications().size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application2.getJobId()).size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application1.getJobId()).size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application3.getJobId()).size());
        Assertions.assertEquals(2,appDao.getApplicationsByEmployee("soso").size());
        Assertions.assertEquals(1, appDao.getApplicationsByEmployee("soso2").size());

        //add ident application.
        appDao.addApplication(application3);
        //should'change anything
        Assertions.assertEquals(3, appDao.getApplications().size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application2.getJobId()).size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application1.getJobId()).size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application3.getJobId()).size());
        Assertions.assertEquals(2,appDao.getApplicationsByEmployee("soso").size());
        Assertions.assertEquals(1, appDao.getApplicationsByEmployee("soso2").size());
    }

    @Test
    void test2(){
        appDao.addApplication(application3);
        appDao.addApplication(application2);
        appDao.addApplication(application1);

        appDao.deleteApplication(application3.getJobId(), application3.getEmployee());
        Assertions.assertEquals(2, appDao.getApplications().size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application2.getJobId()).size());
        Assertions.assertEquals(1, appDao.getApplicationsForJob(application1.getJobId()).size());
        Assertions.assertEquals(2,appDao.getApplicationsByEmployee("soso").size());
        Assertions.assertEquals(0, appDao.getApplicationsByEmployee("someone").size());

        appDao.deleteApplication(application3.getJobId(), application3.getEmployee());
        appDao.deleteApplication(application2.getJobId(), application1.getEmployee());
        appDao.deleteApplication(application1.getJobId(), application1.getEmployee());
        Assertions.assertEquals(0, appDao.getApplications().size());
        Assertions.assertEquals(0, appDao.getApplicationsForJob(application2.getJobId()).size());
        Assertions.assertEquals(0, appDao.getApplicationsForJob(application1.getJobId()).size());
        Assertions.assertEquals(0,appDao.getApplicationsByEmployee("soso").size());
        Assertions.assertEquals(0, appDao.getApplicationsByEmployee("someone").size());
    }

    @Test
    void test3(){
        appDao.addApplication(application3);
        appDao.addApplication(application2);
        appDao.addApplication(application1);

        Application appl = appDao.getApplicationsByJobAndEmployee
                (application1.getJobId(),application1.getEmployee());

        Assertions.assertTrue(appl.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_WAITING));

        appDao.changeApplicationStatus(FlexibleJobsConstants.APPLICATION_STATUS_HIRED,
                application1.getEmployee(), application1.getJobId());
        appl = appDao.getApplicationsByJobAndEmployee
                (application1.getJobId(),application1.getEmployee());
        Assertions.assertTrue(appl.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_HIRED));
        Assertions.assertFalse(appl.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_WAITING));

        appDao.changeApplicationStatus(FlexibleJobsConstants.APPLICATION_STATUS_REJECTED,
                application2.getEmployee(), application2.getJobId());
        appl = appDao.getApplicationsByJobAndEmployee
                (application2.getJobId(),application2.getEmployee());
        Assertions.assertTrue(appl.getStatus().equals(FlexibleJobsConstants.APPLICATION_STATUS_REJECTED));
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
            stm.execute("TRUNCATE TABLE applications");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }



    private void initializeTestApplications(){
        int jobId = 1;
        String employee = "soso";
        String letter = "Random text (bla bla bla)";
        String date = new Date().toString();
        double bid = 1.2;
        String status = FlexibleJobsConstants.APPLICATION_STATUS_WAITING;
        application1 = new Application(jobId, employee, letter, date, bid, status);

        jobId = 2;
        letter = "Random text meore (bla bla bla)";
        date = new Date().toString();
        bid = 112;
        status = FlexibleJobsConstants.APPLICATION_STATUS_WAITING;
        application2 = new Application(jobId, employee, letter, date, bid, status);

        jobId = 3;
        employee = "soso2";
        letter = "Random text mesame (bla bla bla)";
        date = new Date().toString();
        bid = 27.8;
        status = FlexibleJobsConstants.APPLICATION_STATUS_WAITING;
        application3 = new Application(jobId, employee, letter, date, bid, status);
    }


}
