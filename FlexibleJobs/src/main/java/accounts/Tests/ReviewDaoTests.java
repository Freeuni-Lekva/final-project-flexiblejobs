package accounts.Tests;

import accounts.AccountDao;
import accounts.Review;
import accounts.ReviewDao;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.*;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewDaoTests {

    private MysqlConnectionPoolDataSource datasource;
    private ReviewDao dao;
    private Review test1;
    private Review test2;
    private Review test3;
    private Review test4;
    private Review test5;

    @BeforeAll
    void setUp(){
        datasource=new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME_TEST);
        dao=new ReviewDao(datasource);
        createTestObjects();
    }

    private void createTestObjects() {
        test1 = new Review("Kaci", "Kali", 2, 1);
        test2 = new Review("Osmanov", "bereja", 5, 2);
        test3 = new Review("Osmanov", "humanitari", 0, 2);
        test4 = new Review("Zaala", "humanitari", 4, 3);
        test5 = new Review("bereja", "Kali", 2, 4);
    }

    @Test
    void testInsert() {
        Assertions.assertEquals(0,dao.selectByEmployee("bereja").size());
        dao.addReview(test1);
        dao.addReview(test2);
        dao.addReview(test3);
        dao.addReview(test4);
        Assertions.assertEquals(0,dao.selectByEmployee("liziko").size());
        List<Review> list1 = dao.selectByEmployee("humanitari");
        List<Review> list2 = dao.selectByEmployee("bereja");
        List<Review> list3 = dao.selectByEmployee("Kali");
        Assertions.assertEquals(2,list1.size());
        Assertions.assertEquals(1,list2.size());
        Assertions.assertEquals(1,list3.size());
        Assertions.assertTrue(list1.get(0).equals(test3)||list1.get(0).equals(test4));
        Assertions.assertTrue(list1.get(1).equals(test3)||list1.get(1).equals(test4));
    }

    @Test
    void testSelect() {
        dao.addReview(test1);
        dao.addReview(test2);
        dao.addReview(test3);
        dao.addReview(test4);
        dao.addReview(test5);
        List<Review> list1 = dao.selectByEmployee("bereja");
        Assertions.assertTrue(list1.get(0).equals(test2));
        List<Review> list2 = dao.selectByEmployee("Kali");
        Assertions.assertTrue(list2.get(0).equals(test1)||list2.get(0).equals(test5));
        Assertions.assertTrue(list2.get(1).equals(test1)||list2.get(1).equals(test5));
    }

    @Test
    void testAverageReview() {
        dao.addReview(test1);
        dao.addReview(test2);
        dao.addReview(test3);
        dao.addReview(test4);
        Assertions.assertEquals(2, dao.averageReview("Kali"));
        Assertions.assertEquals(5,dao.averageReview("bereja"));
        Assertions.assertEquals(2,dao.averageReview("humanitari"));
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
            stm.execute("TRUNCATE TABLE reviews");
        } catch (SQLException throwables) { throwables.printStackTrace();
        }finally {
            if(connection!=null) {
                try { connection.close();
                } catch (SQLException throwables) { throwables.printStackTrace();
                }
            }
        }
    }
}
