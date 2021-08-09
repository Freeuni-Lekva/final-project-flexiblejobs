package accounts.Tests;

import accounts.*;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.*;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageDaoTest {
    private MysqlConnectionPoolDataSource datasource;
    private MessageDao dao;

    private Message test1;
    private Message test2;
    private Message test3;
    private Message test4;


    @BeforeAll
    void setUp(){
        datasource=new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME_TEST);
        dao=new MessageDao(datasource);
        createTestObjects();
    }

    @Test
    void testInsert(){
        dao.addMessage(test1);
        dao.addMessage(test2);
        dao.addMessage(test3);
        List<Message> list=dao.getConversation("jemal12","jinvalirules");
        Assertions.assertEquals(3,list.size());
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
            stm.execute("TRUNCATE TABLE messages");
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

    private void createTestObjects(){
        test1= new Message("jemal12","jinvalirules","pirveli mesiji");
        test2= new Message("jemal12","jinvalirules","meore mesiji");
        test3= new Message("jinvalirules","jemal12","mesame mesiji");
        test4= new Message("jinvalirules","jemal12","meotxe mesiji");
    }
}
