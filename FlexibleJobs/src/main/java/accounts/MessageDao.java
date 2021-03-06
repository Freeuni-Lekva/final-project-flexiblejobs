package accounts;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class  MessageDao {
    private static DataSource dataSource;

    public MessageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addMessage(Message msg){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO messages (sender, receiver, message,timesent)" +
                            "VALUES ( ?, ?, ?, ?);");
            stm.setString(1, msg.getSender());
            stm.setString(2, msg.getReceiver());
            stm.setString(3, msg.getText());
            stm.setString(4, msg.getTime());
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

    public List<Message> getConversation(String username1,String username2){
        Connection connection=null;
        ArrayList<Message> result=new ArrayList<>();
        try {
            connection=dataSource.getConnection();
            PreparedStatement stm=connection.prepareStatement(
                    "SELECT* FROM messages WHERE (sender= ? AND receiver=?)OR(" +
                            "sender=? AND receiver=?);");
            stm.setString(1,username1);
            stm.setString(2,username2);
            stm.setString(3,username2);
            stm.setString(4,username1);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                Message msg=new Message(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                result.add(msg);
            }
            Collections.sort(result);
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
        return result;
    }


    public Set<String> getAllContacts(String username) {
        Connection connection = null;
        Set<String> result = new HashSet<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT DISTINCT sender FROM messages WHERE receiver=?;");
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String str = rs.getString(1);
                result.add(str);
            }
            stm = connection.prepareStatement(
                    "SELECT DISTINCT receiver FROM messages WHERE sender=?;");
            stm.setString(1, username);
            ResultSet resultSets = stm.executeQuery();
            while (resultSets.next()) {
                String str = resultSets.getString(1);
                result.add(str);
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
        return result;
    }
}
