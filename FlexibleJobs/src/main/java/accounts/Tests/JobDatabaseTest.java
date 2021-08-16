package accounts.Tests;

import accounts.AccountDao;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import jobs.Job;
import jobs.JobDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class JobDatabaseTest {

    private static MysqlConnectionPoolDataSource datasource;
    private static JobDatabase dao;
    private static Job job1;
    private static Job job2;


    @BeforeAll
    static void setUp() {
        datasource = new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME_TEST);
        dao = new JobDatabase(datasource);
        createTestObjects();
    }

    private static void createTestObjects() {
        String employer = "liziko";
        String heading = "butiki";
        String jobdescription = "tashpanduri";
        double budget = 2;
        String jobstatus = "active";
        int numApplications = 0;
        int jobId = 1;
        String dateposted = "15/02/2030";
        String jobduration = "for life";
        job1 = new Job(jobId, numApplications, jobstatus, employer, heading, jobdescription, budget, jobduration, dateposted);

        String employer1 = "chona";
        String heading1 = "skola";
        String jobdescription1 = "geebistvis";
        int jobId1 = 2;
        String jobstatus1 = "active";
        double budget1 = 10;
        String dateposted1 = "5/02/2050";
        String jobduration1 = "sadamdisa";
        job2 = new Job(jobId, numApplications, jobstatus1, employer1, heading1, jobdescription1, budget1, jobduration1, dateposted1);
    }

    @Test
    void testGetEmployeesNames() {
        Assertions.assertEquals(0, dao.getEmployeesNames(1).size());
        dao.addEmployeeToJob("liziko", 1);
        dao.addEmployeeToJob("chona", 1);
        Assertions.assertEquals(2, dao.getEmployeesNames(1).size());
        dao.addEmployeeToJob("tiko", 1);
        dao.addEmployeeToJob("piruza", 1);
        Assertions.assertEquals(4, dao.getEmployeesNames(1).size());
        dao.removeEmployeeFromJob("chona", 1);
        Assertions.assertEquals(3, dao.getEmployeesNames(1).size());
    }

    @Test
    void testAddEmployeeToJob() {
        dao.addEmployeeToJob("lemuri", 1);
        dao.addEmployeeToJob("lemuri1", 1);
        Assertions.assertEquals(2, dao.getEmployeesNames(1).size());
        dao.addEmployeeToJob("lemuri2", 1);
        dao.addEmployeeToJob("lemuri3", 1);
        Assertions.assertEquals(4, dao.getEmployeesNames(1).size());
    }

    @Test
    void testRemoveEmployeeFromJob() {
        Assertions.assertEquals(0, dao.getEmployeesNames(1).size());
        dao.addEmployeeToJob("chona", 1);
        dao.removeEmployeeFromJob("chona", 1);
        Assertions.assertEquals(0, dao.getEmployeesNames(1).size());
    }

    @Test
    void testSaveJob() {
        Set<Job> jobs = dao.getJobs();
        Assertions.assertEquals(0, jobs.size());
        dao.saveJob(job1);
        jobs = dao.getJobs();
        Assertions.assertEquals(1, jobs.size());
    }

    @Test
    void testRemoveJob() {
        Set<Job> jobs = dao.getJobs();
        Assertions.assertEquals(0, jobs.size());
        dao.saveJob(job1);
        jobs = dao.getJobs();
        int jobId = 0;
        for (Job j : jobs) {
            jobId = j.getJobId();
        }
        dao.removeJob(jobId);
        jobs = dao.getJobs();
        Assertions.assertEquals(0,jobs.size());
    }

    @Test
    void testGetJobs() {
        Set<Job> jobs = dao.getJobs();
        Assertions.assertEquals(0, jobs.size());
        dao.saveJob(job1);
        jobs = dao.getJobs();
        Assertions.assertEquals(1, jobs.size());
        dao.saveJob(job2);
        jobs = dao.getJobs();
        Assertions.assertEquals(2, jobs.size());
    }

    @Test
    void testGetJobsByEmployee() {
        dao.saveJob(job1);
        dao.saveJob(job2);
        dao.addEmployeeToJob("nino", job1.getJobId());
        dao.addEmployeeToJob("ana", job2.getJobId());
        Set<Job> jobs = JobDatabase.getJobsByEmployee("nino");
        Assertions.assertEquals(1, jobs.size());
        jobs = JobDatabase.getJobsByEmployee("ana");
        Assertions.assertEquals(1, jobs.size());
    }

    @Test
    void testGetJobsByEmployer() {
        dao.saveJob(job1);
        dao.saveJob(job2);
        Set<Job> jobs = JobDatabase.getJobsByEmployer("liziko");
        Assertions.assertEquals(1, jobs.size());
        jobs = JobDatabase.getJobsByEmployer("chona");
        Assertions.assertEquals(1, jobs.size());
    }

    @Test
    void testAddJobSkills() {
        Set<String> skills = new HashSet<>();
        skills.add("lotoba");
        skills.add("usakmuroba");
        skills.add("oopesdakideba");
        dao.addJobSkills(skills, 1);
        Assertions.assertEquals(skills, dao.getJobSkills(1));
        skills.add("nervebisMoshla");
        skills.add("bebebebebe");
        dao.addJobSkills(skills, 1);
        Assertions.assertEquals(skills, dao.getJobSkills(1));
    }

    @Test
    void testGetJobSkills() {
        Set<String> skills = new HashSet<>();
        skills.add("grrrr");
        skills.add("aaaaaa");
        skills.add("davigale");
        dao.addJobSkills(skills, 1);
        Assertions.assertEquals(skills, dao.getJobSkills(1));
    }

    @Test
    void testGetJobsBySkill() {
        Set<String> skills = new HashSet<>();
        skills.add("lotaoba");
        skills.add("poxuistoba");
        skills.add("dzili");
        Set<String> skills1 = new HashSet<>();
        skills1.add("lotaoba");
        skills1.add("damgaleeeet");
        dao.addJobSkills(skills, 1);
        dao.addJobSkills(skills1, 2);
        Set<Integer> jobs = dao.getJobsBySkill("lotaoba");
        Assertions.assertEquals(2, jobs.size());
    }

    @Test
    void testUpdateJobStatus() {
        dao.saveJob(job1);
        Set<Job> jobs = dao.getJobs();
        int jobid = 0;
        for (Job j : jobs) {
            jobid = j.getJobId();
        }
        dao.updateJobStatus(jobid, "aaaaaaaa");
        Job job = dao.getJob(jobid);
        Assertions.assertEquals("aaaaaaaa", job.getJobStatus());
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
            stm.execute("TRUNCATE TABLE jobs");
            stm.execute("TRUNCATE TABLE hires");
            stm.execute("TRUNCATE TABLE jobskills");
            stm.execute("TRUNCATE TABLE employeeskills");
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
}
