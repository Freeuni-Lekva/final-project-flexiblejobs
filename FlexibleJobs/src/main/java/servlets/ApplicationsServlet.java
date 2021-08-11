package servlets;

import jobs.Application;
import jobs.ApplicationDAO;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

@WebServlet ("/ApplicationsServlet")
public class ApplicationsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        DataSource dataSource = (DataSource) servletContext.getAttribute("datasource");
        ApplicationDAO database = (ApplicationDAO) servletContext.getAttribute("appDao");
        int jobId = Integer.parseInt(req.getParameter("id"));
        String employeeName = req.getParameter("employee_name");
        System.out.println(employeeName);
        String condition = req.getParameter("condition");
        if(condition.equals("send")){
            sendApplication(req, resp, jobId, employeeName, database);
        }else{
            String employee = req.getParameter("employee");
            deleteApplication(jobId, employee, database);
        }
    }

    private void sendApplication(HttpServletRequest req, HttpServletResponse resp,
                                 int jobId, String employeeName, ApplicationDAO database){
        Double bid = Double.parseDouble(req.getParameter("bid"));
        String letter = req.getParameter("letter");
        String sendingDate = new Date().toString();
        Application application = new Application(jobId, employeeName, letter,
                sendingDate, bid, FlexibleJobsConstants.APPLICATION_STATUS_WAITING);
        database.addApplication(application);
        try {
            req.getRequestDispatcher("/Jobs_Front/ApplicationSent.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteApplication(int jobId, String employee, ApplicationDAO database){
        database.deleteApplication(jobId, employee);
    }
}
