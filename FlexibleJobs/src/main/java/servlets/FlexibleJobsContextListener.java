package servlets;

import accounts.AccountDao;
import accounts.MessageDao;
import accounts.PersonalDataDao;
import accounts.ReviewDao;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import jobs.ApplicationDAO;
import jobs.JobDatabase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FlexibleJobsContextListener implements ServletContextListener {

    private MysqlConnectionPoolDataSource datasource;



    @Override
    public void contextInitialized(ServletContextEvent sce) {
        datasource=new MysqlConnectionPoolDataSource();
        datasource.setPort(FlexibleJobsConstants.PORT);
        datasource.setServerName(FlexibleJobsConstants.SERVER_NAME);
        datasource.setUser(FlexibleJobsConstants.USER);
        datasource.setPassword(FlexibleJobsConstants.PASSWORD);
        datasource.setDatabaseName(FlexibleJobsConstants.DB_NAME);
        AccountDao accountDao = new AccountDao(datasource);
        MessageDao messageDao = new MessageDao(datasource);
        PersonalDataDao personalDataDao = new PersonalDataDao(datasource);
        JobDatabase jobDatabase = new JobDatabase(datasource);
        ApplicationDAO appDao = new ApplicationDAO(datasource);
        ReviewDao reviewDao=new ReviewDao(datasource);
        sce.getServletContext().setAttribute("accountDao",accountDao);
        sce.getServletContext().setAttribute("messageDao",messageDao);
        sce.getServletContext().setAttribute("personalDataDao",personalDataDao);
        sce.getServletContext().setAttribute("jobDao",jobDatabase);
        sce.getServletContext().setAttribute("appDao", appDao);
        sce.getServletContext().setAttribute("reviewDao",reviewDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
