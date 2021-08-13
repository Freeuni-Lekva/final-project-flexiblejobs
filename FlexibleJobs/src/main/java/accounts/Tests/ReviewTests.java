package accounts.Tests;

import accounts.AccountDao;
import accounts.ReviewDao;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import servlets.FlexibleJobsConstants;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewTests {

    private MysqlConnectionPoolDataSource datasource;
    private ReviewDao dao;

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

    }
}
