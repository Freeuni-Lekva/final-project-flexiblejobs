package accounts.Tests;

import accounts.*;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
        dao=new AccountDao(datasource);
        createTestObjects();
    }

    @Test
    void testInsert(){
        assertEquals(0,dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE).size());
        assertEquals(0,dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER).size());
        dao.addAccount(test1);
        dao.addAccount(test2);
        dao.addAccount(test3);
        List<Account> list=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE);
        List<Account> list2=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER);
        assertEquals(2,list.size());
        assertEquals(1,list2.size());
        assertTrue(list.get(0).equals(test1)||list.get(0).equals(test2));
        assertTrue(list.get(1).equals(test1)||list.get(1).equals(test2));
    }


    @Test
    void testDelete(){
        dao.addAccount(test4);
        dao.addAccount(test1);
        Account acc=dao.selectByUsername("jemal12");
        assertFalse(acc==null);
        dao.delete("jemal12");
        acc=dao.selectByUsername("jemal12");
        assertTrue(acc==null);
        acc=dao.selectByUsername("jemal1234");
        assertTrue(acc==null);
    }

    @Test
    void testSelect(){
        dao.addAccount(test1);
        dao.addAccount(test2);
        dao.addAccount(test3);
        dao.addAccount(test4);
        List<Account> list=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE);
        List<Account> list2=dao.selectAllByType(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER);
        assertTrue(list.get(0).equals(test1)||list.get(0).equals(test2));
        assertTrue(list.get(1).equals(test1)||list.get(1).equals(test2));
        assertTrue(list2.get(0).equals(test3)||list2.get(0).equals(test4));
        assertTrue(list2.get(1).equals(test3)||list2.get(1).equals(test4));
        Account acc=dao.selectByUsername("jemal1234");
        Account shmacc=dao.selectByUsername("justjemal");
        assertEquals(acc,test3);
        assertEquals(null,shmacc);
    }

    @Test
    void testUpdateBalance(){
        dao.addAccount(test3);
        AccountDao.updateBalance("jemal1234",1225);
        Account acc=dao.selectByUsername("jemal1234");
        assertEquals(1225,acc.getBalance());
    }

    @Test
    void testUpdatePassword(){
        dao.addAccount(test2);
        AccountDao.updatePassword("jemal123","hidroeleqtrosadguri");
        Account acc=dao.selectByUsername("jemal123");
        assertEquals("hidroeleqtrosadguri",acc.getPassword());
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
            stm.execute("TRUNCATE TABLE accounts");
            stm.execute("TRUNCATE TABLE personal_info");
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
        PersonalData data1 = new EmployeePersonalData("jemal", "jiquri", "jinvali",
                "kacuri kaci", "random long string uhncjfhndfjgnjkngjf");
        PersonalData data2 = new EmployeePersonalData("gaidzvera", "guram", "tye",
                "tyis kaci", "qreba chndeba");
        PersonalData data3 = new EmployeePersonalData("dennis", "dennisovich", "transylvania",
                "grrrrrrr", "imiromtom testebze gaiaros");
        PersonalData data4 = new EmployeePersonalData("tikso", "rogorc aseti", "shig gldani",
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
