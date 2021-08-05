package servlets;

import jobs.Application;
import jobs.JobDatabase;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;

@WebServlet ("/ApplicationCreationServlet")
public class ApplicationCreationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        DataSource dataSource = (DataSource) servletContext.getAttribute("datasource");
        JobDatabase database = new JobDatabase(dataSource);
        int jobId = Integer.parseInt(req.getParameter("jobid"));
        String employeeName = req.getParameter("employee_name");
        Double bid = Double.parseDouble(req.getParameter("bid"));
        String letter = req.getParameter("letter");
        Date sendingDate = new Date();
        Application application = new Application(jobId, employeeName, letter, sendingDate, bid);
        database.addApplication(application);
    }
}
