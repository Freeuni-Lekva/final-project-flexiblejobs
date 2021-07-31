package servlets;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

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
        sce.getServletContext().setAttribute("datasource",datasource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
