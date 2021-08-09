package accounts;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class  MessageDao {
    private static final int CHAT_ID_NOT_AVAILABLE = -1;
    private static DataSource dataSource;

    public MessageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addMessage(Message msg){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO messages (chatId, sender, receiver, message,timesent)" +
                            "VALUES (?, ?, ?, ?, ?);");
            stm.setInt(1,msg.getChatId());
            stm.setString(2, msg.getSender());
            stm.setString(3, msg.getReceiver());
            stm.setString(4, msg.getText());
            stm.setString(5, msg.getTime());
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
                    "SELECT FROM messages WHERE (sender= ? AND receiver=?)OR(" +
                            "sender=? AND receiver=?);");
            stm.setString(1,username1);
            stm.setString(2,username2);
            stm.setString(3,username2);
            stm.setString(4,username1);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                Message msg=new Message(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
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

    public int getConversationId(String username1, String username2){
        Connection connection=null;
        int chatId = CHAT_ID_NOT_AVAILABLE;
        try {
            connection=dataSource.getConnection();
            PreparedStatement stm=connection.prepareStatement(
                    "SELECT FROM conversation WHERE (user_1= ? AND user_2= ?)OR(" +
                            "user_1=? AND user_2=?);");
            stm.setString(1,username1);
            stm.setString(2,username2);
            stm.setString(3,username2);
            stm.setString(4,username1);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
               chatId = rs.getInt(1);
            }
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
        return chatId;
    }

    public List<Message> getConversationById(int chatId){
        Connection connection=null;
        ArrayList<Message> result=new ArrayList<>();
        try {
            connection=dataSource.getConnection();
            PreparedStatement stm=connection.prepareStatement(
                    "SELECT FROM messages WHERE (chatId= ?);");
            stm.setInt(1,chatId);
            ResultSet rs=stm.executeQuery();
            while(rs.next()){
                Message msg=new Message(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
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
}
