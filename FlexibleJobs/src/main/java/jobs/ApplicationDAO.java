package jobs;

import accounts.PersonalData;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ApplicationDAO {
    private DataSource dataSource;

    public ApplicationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addApplication(Application application){
        Application app1 = getApplicationsByJobAndEmployee
                (application.getJobId(), application.getEmployee());
        if(app1 == null) {
            PreparedStatement stm = null;
            Connection connection = null;
            try {
                connection = dataSource.getConnection();

                stm = connection.prepareStatement(
                        "INSERT INTO applications (jobid, employee, datesent, " +
                                "letter, bid, applicationstatus) " +
                                "VALUES (?,?,?,?,?,?);");
                stm.setInt(1, application.getJobId());
                stm.setString(2, application.getEmployee());
                stm.setString(3, application.getDate());
                stm.setString(4, application.getLetter());
                stm.setDouble(5, application.getBid());
                stm.setString(6, application.getStatus());
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
    }


    public void deleteApplication(int jobId, String employee){
        PreparedStatement stm = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement("DELETE FROM applications " +
                    "WHERE applicationId <> 0 and jobid = ? and employee = ?");
            stm.setInt(1,jobId);
            stm.setString(2, employee);
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

    public Set<Application> getApplications(){
        Set<Application> applications = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("SELECT * FROM applications");
            while(result.next()){
                int jobId = result.getInt("jobid");
                String employee = result.getString("employee");
                String letter = result.getString("letter");
                String date = result.getString("datesent");
                double bid = result.getDouble("bid");
                String status = result.getString("applicationstatus");
                Application application = new Application(jobId, employee, letter, date, bid,status);
                applications.add(application);
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
        return applications;
    }

    public Set<Application> getApplicationsForJob(int jobId){
        Set<Application> applications = new HashSet<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("SELECT * FROM applications WHERE jobid = '" + jobId + "'");
            while(result.next()){
                String employee = result.getString("employee");
                String letter = result.getString("letter");
                String date = result.getString("datesent");
                double bid = result.getDouble("bid");
                String status = result.getString("applicationstatus");
                Application application = new Application(jobId, employee, letter, date, bid,status);
                applications.add(application);
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
        return applications;
    }

    public Set<Application> getApplicationsByEmployee(String employee){
        Set<Application> applications = new HashSet<>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("SELECT * FROM applications WHERE employee = \"" + employee + "\"");
            while(result.next()){
                int jobId = result.getInt("jobid");
                String letter = result.getString("letter");
                String date = result.getString("datesent");
                double bid = result.getDouble("bid");
                String status = result.getString("applicationstatus");
                Application application = new Application(jobId, employee, letter, date, bid,status);
                applications.add(application);
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
        return applications;
    }

    public Application getApplicationsByJobAndEmployee(int jobId, String employee){
        Application application = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery
                    ("SELECT * FROM applications WHERE jobid = '" + jobId +
                            "' and employee = \"" + employee + "\"");
            while(result.next()){
                String letter = result.getString("letter");
                String date = result.getString("datesent");
                double bid = result.getDouble("bid");
                String status = result.getString("applicationstatus");
                application = new Application(jobId, employee, letter, date, bid,status);
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
        return application;
    }

    public void changeApplicationStatus(String newStatus, String employee, int jobId){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE applications SET applicationstatus=? " +
                            "WHERE employee = ? and jobid = ?;");
            statement.setString(1,newStatus);
            statement.setString(2, employee);
            statement.setInt(3,jobId);
            statement.executeUpdate();
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

    public int getBid(int jobId, String employee){
        Connection connection = null;
        int bid=0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT bid FROM applications WHERE employee = ? AND jobid=?;");
            stm.setString(1, employee);
            stm.setInt(2, jobId);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return 0;
            bid=rs.getInt("bid");
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
        return bid;
    }

}