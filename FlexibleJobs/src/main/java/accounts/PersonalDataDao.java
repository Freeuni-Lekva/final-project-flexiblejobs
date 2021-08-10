package accounts;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalDataDao {
    private static DataSource dataSource;

    public PersonalDataDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void addPersonalData(PersonalData data, String username) {
        PreparedStatement stm;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            stm = connection.prepareStatement(
                    "INSERT INTO personal_info (username, firstname, lastname,livingplace," +
                            "profileheading,profiledescription) " +
                            "VALUES (?, ?, ?, ?, ?, ?);");
            stm.setString(1, username);
            stm.setString(2, data.getFirstName());
            stm.setString(3, data.getLastName());
            stm.setString(4, data.getLivingPlace());
            stm.setString(5, data.getProfileHeading());
            stm.setString(6, data.getProfileDescription());
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

    public static void deletePersonalData(String username) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM personal_info WHERE username = ?;");
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

    public void updateData(PersonalData updated,String username){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE personal_info SET firstname=?, lastname=?," +
                            "livingplace=?,profileheading=?,profiledescription=?" +
                            " WHERE username = ?;");
            statement.setString(1, updated.getFirstName());
            statement.setString(2, updated.getLastName());
            statement.setString(3, updated.getLivingPlace());
            statement.setString(4, updated.getProfileHeading());
            statement.setString(5, updated.getProfileDescription());
            statement.setString(6, username);
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

    public PersonalData selectByUsername(String username){
        Connection connection = null;
        PersonalData result=null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT* FROM personal_info WHERE username = ?;");
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            String firstname=rs.getString(2);
            String lastname=rs.getString(3);
            String livingplace=rs.getString(4);
            String profileheading=rs.getString(5);
            String profiledescription=rs.getString(6);
            result=new PersonalData(username,firstname,lastname,livingplace,profileheading,profiledescription);
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

}
