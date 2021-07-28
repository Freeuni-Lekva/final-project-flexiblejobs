package accounts;

import jobs.Job;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private DataSource dataSource;

    public AccountDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addAccount(Account acc) throws SQLException {
        addAccountDb(acc);
        addPersonalData(acc.getPersonalData(), acc.getUserName());
    }

    public List<Account> selectAccounts(String filter) {
//        List<Account> result = new ArrayList<>();
//        try {
//            Statement stm = conn.createStatement();
//            ResultSet rs = stm.executeQuery(
//                    "SELECT id, first_name, last_name, enrollment_year FROM students");
//            while (rs.next()) {
//                ret.add(new Student(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getInt(4)));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return ret;
return null;
    }

    public void delete(String username) {
    }

    public List<Account> SelectBySkills(Job job) {
        return null;
    }


    private void addAccountDb(Account acc) {
        PreparedStatement stm = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "INSERT INTO accounts (username, pass, balance,rating) " +
                            "VALUES (?, ?, ?, ?);");
            stm.setString(1, acc.getUserName());
            stm.setString(2, acc.getPassword());
            stm.setInt(3, acc.getBalance());
            stm.setDouble(4, acc.getRating().doubleValue());
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

    private void addPersonalData(PersonalData data, String username) {
        PreparedStatement stm = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "INSERT INTO personal_info (username, firstname, lastname,imagefile,livingplace" +
                            "profileheading,profiledescription) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?);");
            stm.setString(1, username);
            stm.setString(2, data.getFirstName());
            stm.setString(3, data.getLastName());
            stm.setString(4, data.getImageFile());
            stm.setString(4, data.getLivingPlace());
            stm.setString(4, data.getProfileHeading());
            stm.setString(4, data.getProfileDescription());
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
