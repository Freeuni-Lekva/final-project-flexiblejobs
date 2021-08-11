package accounts.Tests;

import accounts.*;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.*;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageDaoTest {
    private MysqlConnectionPoolDataSource datasource;
    private MessageDao dao;

    private Message test1;
    private Message test2;
    private Message test3;
    private Message test4;
    private Message test5;
    private Message test6;
    private Message test7;


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
        dao.addMessage(test5);
        dao.addMessage(test7);
        List<Message> list=dao.getConversation("jemal12","jinvalirules");
        Assertions.assertEquals(3,list.size());
    }

    @Test
    void testUniqueContacts(){
        dao.addMessage(test1);
        dao.addMessage(test2);
        dao.addMessage(test3);
        dao.addMessage(test4);
        dao.addMessage(test5);
        dao.addMessage(test6);
        dao.addMessage(test7);

        Set<String> set1=dao.getAllContacts("jemal12");
        Set<String> set2=dao.getAllContacts("crash bandicoot");

        Assertions.assertEquals(2,set1.size());
        Assertions.assertEquals(2,set2.size());
        Assertions.assertTrue(set1.contains("jinvalirules"));
        Assertions.assertTrue(set1.contains("crash bandicoot"));
        Assertions.assertTrue(set2.contains("jinvalirules"));
        Assertions.assertTrue(set2.contains("jemal12"));
    }

    @Test
    void testSortedConversation() throws InterruptedException {
        dao.addMessage(test1);
        Thread.sleep(20);
        dao.addMessage(test2);
        Thread.sleep(20);
        dao.addMessage(test3);
        Thread.sleep(20);
        dao.addMessage(test4);
        Thread.sleep(20);
        dao.addMessage(test5);
        Thread.sleep(20);
        dao.addMessage(test6);
        Thread.sleep(20);
        dao.addMessage(test7);
        Thread.sleep(20);

        List<Message> list=dao.getConversation("jemal12","jinvalirules");

        Assertions.assertEquals(4,list.size());
        Assertions.assertEquals("pirveli mesiji",list.get(0).getText());
        Assertions.assertEquals("meore mesiji",list.get(1).getText());
        Assertions.assertEquals("mesame mesiji",list.get(2).getText());
        Assertions.assertEquals("meotxe mesiji",list.get(3).getText());
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
        test5= new Message("crash bandicoot","jemal12","mexute mesiji");
        test6= new Message("jinvalirules","crash bandicoot","meeqvse mesiji");
        test7= new Message("elise","grrrrr","meshvide mesiji");
    }
}
