package jobs;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JobDatabase {

    private DataSource dataSource;

    public JobDatabase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveJob(Job job) {
        PreparedStatement stm = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();

            stm = connection.prepareStatement(
                    "INSERT INTO jobs (employer, heading, jobdescription, budget, " +
                            "dateposted, numapplications, jobduration,jobstatus ) " +
                            "VALUES (?,?,?,?,?,?,?,?);");
            stm.setString(1, job.getEmployer());
            stm.setString(2, job.getHeader());
            stm.setString(3, job.getDescription());
            stm.setDouble(4, job.getBudget());
            String date = job.getDate().toString();
            System.out.println(date);
            stm.setString(5, date);
            stm.setInt(6, 0);
            stm.setString(7, job.getJobDuration());
            stm.setString(8, job.getJobStatus());
            stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void removeJob(int jobid) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM jobs WHERE jobid = ?");
            stm.setInt(1, jobid);
            stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public Set<Job> getJobsByEmployer(String employer){
        Set<Job> jobs = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "select FROM jobs WHERE employer = \"" + employer + "\"");
            while (result.next()) {
                int jobid = result.getInt("jobid");
                String jobHeader = result.getString("heading");
                String description = result.getString("jobdescription");
                double budget = result.getDouble("budget");
                String duration = result.getString("jobduration");
                String date = result.getString("dateposted");
                int numApplications = result.getInt("numapplications");
                String status = result.getString("jobstatus");
                Job job = new Job(jobid, numApplications, status, employer, jobHeader, description,
                        budget, duration, date);
                jobs.add(job);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return jobs;
    }

    public Job getJob(int jobid) {
        Connection connection = null;
        Job job = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "select * FROM jobs WHERE jobid = \"" + jobid + "\"");
            while (result.next()) {
                String employer = result.getString("employer");
                String jobHeader = result.getString("heading");
                String description = result.getString("jobdescription");
                double budget = result.getDouble("budget");
                String duration = result.getString("jobduration");
                String date = result.getString("dateposted");
                int numApplications = result.getInt("numapplications");
                String status = result.getString("jobstatus");
                job = new Job(jobid, numApplications, status, employer, jobHeader, description,
                        budget, duration, date);
                return job;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return job;
    }

    public Set<Integer> getJobsBySkill(String skill){
        Set<Integer> jobs = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM jobskills " +
                    "WHERE skill = \""+ skill + "\"");
            while (result.next()){
                int jobId = result.getInt("jobid");
                jobs.add(jobId);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return  jobs;
    }
}
