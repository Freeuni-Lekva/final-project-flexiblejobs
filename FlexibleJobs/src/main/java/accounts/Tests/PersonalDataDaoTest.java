package accounts.Tests;

import accounts.*;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.*;
import servlets.FlexibleJobsConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonalDataDaoTest {
    private MysqlConnectionPoolDataSource datasource;
    private PersonalDataDao dao;

    private PersonalData test1;
    private PersonalData test2;
    private PersonalData test3;
    private PersonalData test4;


    @BeforeAll
    void setUp(){
        datasource=new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME_TEST);
        dao= new PersonalDataDao(datasource);
        createTestObjects();
        clearTables();
    }

    @Test
    void testInsert(){
        PersonalDataDao.addPersonalData(test1,"jemal12");
        PersonalDataDao.addPersonalData(test3,"jemal1234");
        Assertions.assertNotNull(dao.selectByUsername("jemal12"));
        Assertions.assertNotNull(dao.selectByUsername("jemal1234"));
        Assertions.assertNull(dao.selectByUsername("drakula untoldi"));
    }

    @Test
    void testDelete(){
        Assertions.assertNull(dao.selectByUsername("jemal123"));

        PersonalDataDao.addPersonalData(test2,"jemal123");
        Assertions.assertNotNull(dao.selectByUsername("jemal123"));

        PersonalDataDao.deletePersonalData("jemal123");
        Assertions.assertNull(dao.selectByUsername("jemal123"));
    }

    @Test
    void testSelect(){
        PersonalDataDao.addPersonalData(test1,"jemal12");
        PersonalDataDao.addPersonalData(test2,"jemal123");
        PersonalDataDao.addPersonalData(test3,"jemal1234");
        PersonalDataDao.addPersonalData(test4,"jemal12345");

        PersonalData data= dao.selectByUsername("jemal123");
        Assertions.assertEquals(test2.getFirstName(),data.getFirstName());
        Assertions.assertEquals(test2.getLastName(),data.getLastName());
        Assertions.assertEquals(test2.getLivingPlace(),data.getLivingPlace());
        Assertions.assertEquals(test2.getProfileHeading(),data.getProfileHeading());
        Assertions.assertEquals(test2.getProfileDescription(),data.getProfileDescription());
    }

    @Test
    void testUpdate(){
        PersonalDataDao.addPersonalData(test1,"jemal12");
        dao.updateData(test3,"jemal12");
        PersonalData data= dao.selectByUsername("jemal12");
        Assertions.assertEquals(test3.getFirstName(),data.getFirstName());
        Assertions.assertEquals(test3.getLastName(),data.getLastName());
        Assertions.assertEquals(test3.getLivingPlace(),data.getLivingPlace());
        Assertions.assertEquals(test3.getProfileHeading(),data.getProfileHeading());
        Assertions.assertEquals(test3.getProfileDescription(),data.getProfileDescription());
    }

    @AfterEach
    void clearTables(){
        Connection connection=null;
        try {
            connection=datasource.getConnection();
            Statement stm=connection.createStatement();
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
        test1 = new PersonalData("jemal12","jemal", "jiquri", "jinvali",
                "kacuri kaci", "random long string uhncjfhndfjgnjkngjf");
        test2 = new PersonalData("jemal123","gaidzvera", "guram", "tye",
                "tyis kaci", "qreba chndeba");
        test3 = new PersonalData("jemal1234","dennis", "dennisovich", "transylvania",
                "grrrrrrr", "imiromtom testebze gaiaros");
        test4 = new PersonalData("jemal12345","tikso", "rogorc aseti", "shig gldani",
                "The Boss", "oghondatsa riaaaaa");
    }
}
