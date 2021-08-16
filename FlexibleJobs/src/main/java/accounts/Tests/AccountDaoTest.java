package accounts.Tests;

import accounts.*;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.*;
import servlets.FlexibleJobsConstants;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountDaoTest {
    private MysqlConnectionPoolDataSource datasource;
    private AccountDao dao;
    private Account test1;
    private Account test2;
    private Account test3;
    private Account test4;


    @BeforeAll
    void setUp(){
        datasource=new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME_TEST);
        PersonalDataDao pd = new PersonalDataDao(datasource);
        dao=new AccountDao(datasource);
        createTestObjects();
    }

    @Test
    void testInsert(){
        Assertions.assertEquals(0,dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE).size());
        Assertions.assertEquals(0,dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER).size());
        dao.addAccount(test1);
        dao.addAccount(test2);
        dao.addAccount(test3);
        List<Account> list=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE);
        List<Account> list2=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER);
        Assertions.assertEquals(2,list.size());
        Assertions.assertEquals(1,list2.size());
        Assertions.assertTrue(list.get(0).equals(test1)||list.get(0).equals(test2));
        Assertions.assertTrue(list.get(1).equals(test1)||list.get(1).equals(test2));
    }


    @Test
    void testDelete(){
        dao.addAccount(test4);
        dao.addAccount(test1);
        Account acc=dao.selectByUsername("jemal12");
        Assertions.assertNotNull(acc);
        dao.delete("jemal12");
        acc=dao.selectByUsername("jemal12");
        Assertions.assertNull(acc);
        acc=dao.selectByUsername("jemal1234");
        Assertions.assertNull(acc);
    }

    @Test
    void testSelect(){
        dao.addAccount(test1);
        dao.addAccount(test2);
        dao.addAccount(test3);
        dao.addAccount(test4);
        List<Account> list=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE);
        List<Account> list2=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER);
        Assertions.assertTrue(list.get(0).equals(test1)||list.get(0).equals(test2));
        Assertions.assertTrue(list.get(1).equals(test1)||list.get(1).equals(test2));
        Assertions.assertTrue(list2.get(0).equals(test3)||list2.get(0).equals(test4));
        Assertions.assertTrue(list2.get(1).equals(test3)||list2.get(1).equals(test4));
        Account acc=dao.selectByUsername("jemal1234");
        Account shmacc=dao.selectByUsername("justjemal");
        Assertions.assertEquals(acc,test3);
        Assertions.assertNull(shmacc);
    }

    @Test
    void testUpdateBalance(){
        dao.addAccount(test3);
        AccountDao.updateBalance("jemal1234",1225);
        Account acc=dao.selectByUsername("jemal1234");
        Assertions.assertEquals(1225,acc.getBalance());
    }

    @Test
    void testUpdatePassword(){
        dao.addAccount(test2);
        AccountDao.updatePassword("jemal123","hidroeleqtrosadguri");
        Account acc=dao.selectByUsername("jemal123");
        Assertions.assertEquals("hidroeleqtrosadguri",acc.getPassword());
    }

    @Test
    void testLogIn() {
        Assertions.assertFalse(dao.isOnline("liziko"));
        dao.logIn("liziko");
        Assertions.assertTrue(dao.isOnline("liziko"));
    }

    @Test
    void singOut() {
        dao.logIn("liziko");
        Assertions.assertTrue(dao.isOnline("liziko"));
        dao.signOut("liziko");
        Assertions.assertFalse(dao.isOnline("liziko"));
    }

    @Test
    void testIsOnline() {
        Assertions.assertFalse(dao.isOnline("liziko"));
        dao.logIn("liziko");
        Assertions.assertTrue(dao.isOnline("liziko"));
        dao.signOut("liziko");
        Assertions.assertFalse(dao.isOnline("liziko"));
    }

    @Test
    void testUpdateRating() {
        dao.addAccount(test1);
        Assertions.assertEquals(0.0, dao.getRating(test1.getUserName()));
        dao.updateRating(test1.getUserName(), 3);
        Assertions.assertEquals(3.0,dao.getRating(test1.getUserName()));
        dao.addAccount(test2);
        Assertions.assertEquals(0.0, dao.getRating(test2.getUserName()));
        dao.updateRating(test2.getUserName(), 4.0);
        Assertions.assertEquals(4.0, dao.getRating(test2.getUserName()));
    }

    @Test
    void getCurrentBalance() {
        dao.addAccount(test1);
        Assertions.assertEquals(0, dao.getCurrentBalance(test1.getUserName()));
        AccountDao.updateBalance(test1.getUserName(), 100);
        Assertions.assertEquals(100, dao.getCurrentBalance(test1.getUserName()));
        dao.addAccount(test3);
        Assertions.assertEquals(0, dao.getCurrentBalance(test3.getUserName()));
        AccountDao.updateBalance(test3.getUserName(), 1000);
        Assertions.assertEquals(1000, dao.getCurrentBalance(test3.getUserName()));
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
            stm.execute("TRUNCATE TABLE accounts");
            stm.execute("TRUNCATE TABLE personal_info");
            stm.execute("TRUNCATE TABLE online_users");
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
        PersonalData data1 = new PersonalData("jemal12","jemal", "jiquri", "jinvali",
                "kacuri kaci", "random long string uhncjfhndfjgnjkngjf");
        PersonalData data2 = new PersonalData("jemal123","gaidzvera", "guram", "tye",
                "tyis kaci", "qreba chndeba");
        PersonalData data3 = new PersonalData("jemal1234","dennis", "dennisovich", "transylvania",
                "grrrrrrr", "imiromtom testebze gaiaros");
        PersonalData data4 = new PersonalData("jemal12345","tikso", "rogorc aseti", "shig gldani",
                "The Boss", "oghondatsa riaaaaa");

        test1=new Employee("jemal12","jinvalirules");
        test2=new Employee("jemal123","jinvalirules");
        test3=new Employer("jemal1234","jinvalirules");
        test4=new Employer("jemal12345","jinvalirules");

        test1.setPersonalData(data1);
        test2.setPersonalData(data2);
        test3.setPersonalData(data3);
        test4.setPersonalData(data4);
    }
}
