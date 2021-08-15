package accounts;

import jobs.Job;
import servlets.FlexibleJobsConstants;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private static DataSource dataSource;

    public AccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void updateBalance(String username, int balance){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE accounts SET balance=? WHERE username = ?;");
            statement.setInt(1,balance);
            statement.setString(2, username);
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

    public static void updatePassword(String username, String password){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE accounts SET pass=? WHERE username = ?;");
            statement.setString(1,password);
            statement.setString(2, username);
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

    public boolean addAccount(Account acc){
        if(acc==null||acc.getPersonalData()==null)
            return false;
        addAccountDb(acc);
        PersonalDataDao.addPersonalData(acc.getPersonalData(), acc.getUserName());
        return true;
    }

    public List<Account> selectAllByType(String type) {
        Connection connection=null;
        List<Account> result = new ArrayList<>();
        try {
            connection=dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT* FROM accounts WHERE acctype=?;");
            stm.setString(1,type);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String username=rs.getString(1);
                String password=rs.getString(2);
                int balance=rs.getInt(3);
                BigDecimal rating=rs.getBigDecimal(4);
                Account acc=null;
                if(type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE))
                    acc=new Employee(username,password,balance,rating);
                else if(type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER))
                    acc=new Employer(username,password,balance,rating);
                result.add(acc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return result;

    }

    public void delete(String username) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM accounts WHERE username = ?;");
            stm.setString(1, username);
            stm.executeUpdate();
            PersonalDataDao.deletePersonalData(username);
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

    public Account selectByUsername(String username) {
        Connection connection = null;
        Account result=null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT* FROM accounts WHERE username = ?;");
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            String password=rs.getString(2);
            int balance=rs.getInt(3);
            BigDecimal rating=rs.getBigDecimal(4);
            String type=rs.getString(5);
            if(type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER)){
                result=new Employer(username,password,balance,rating);
            }else if(type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE)) {
                result = new Employee(username, password, balance, rating);
            } else if(type.equals(FlexibleJobsConstants.ACCOUNT_ROLE_ADMINISTRATOR)){
                result = new Administrator(username, password, balance, rating);
            }
            result.setPersonalData(PersonalDataDao.selectByUsername(username));
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
        return result;
    }


    private void addAccountDb(Account acc) {
        PreparedStatement stm;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "INSERT INTO accounts (username, pass, balance,rating,acctype) " +
                            "VALUES (?, ?, ?, ?, ?);");
            stm.setString(1, acc.getUserName());
            stm.setString(2, acc.getPassword());
            stm.setInt(3, acc.getBalance());
            stm.setDouble(4, acc.getRating().doubleValue());
            stm.setString(5,acc.getType());
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

    public void logIn(String username){
        PreparedStatement stm;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "INSERT INTO online_users (username) " +
                            "VALUES (?);");
            stm.setString(1, username);
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

    public void signOut(String username){
        PreparedStatement stm;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "DELETE FROM online_users WHERE username=?;");
            stm.setString(1, username);
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

    public boolean isOnline(String username){
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM online_users WHERE username = ?");
            stm.setString(1, username);
             rs = stm.executeQuery();
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
        return rs!=null;
    }

    public ArrayList<String> getSkills(String username){
        ArrayList<String> skills = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT* FROM employeeskills WHERE username = ?;");
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            while (true){
                skills.add(rs.getString(2));
                if(!rs.next())break;
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
        return skills;

    }

    public void addSkills(String username, List<String> skills){
        PreparedStatement stm;
        Connection connection = null;
        try {
            for(int i=0; i<skills.size(); i++) {
                String skill = skills.get(i);
                connection = dataSource.getConnection();
                stm = connection.prepareStatement(
                        "INSERT INTO employeeskills (username, skill) " +
                                "VALUES (?, ?);");
                stm.setString(1, username);
                stm.setString(2, skill);
                stm.executeUpdate();
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
    }
}
