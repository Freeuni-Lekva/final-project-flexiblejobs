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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountDaoTest {
    private MysqlConnectionPoolDataSource datasource;
    private AccountDao dao;

    private Account test1;
    private Account test2;
    private Account test3;
    private Account test4;
    private PersonalData data1;
    private PersonalData data2;
    private PersonalData data3;
    private PersonalData data4;


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
    void test1(){
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
        data1=new EmployeePersonalData("jemal","jiquri","jinvali",
                "kacuri kaci","random long string uhncjfhndfjgnjkngjf");
        data2=new EmployeePersonalData("gaidzvera","guram","tye",
                "tyis kaci","qreba chndeba");
        data3=new EmployeePersonalData("dennis","dennisovich","transylvania",
                "grrrrrrr","imiromtom testebze gaiaros");
        data4=new EmployeePersonalData("tikso","rogorc aseti","shig gldani",
                "The Boss","oghondatsa riaaaaa");

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
