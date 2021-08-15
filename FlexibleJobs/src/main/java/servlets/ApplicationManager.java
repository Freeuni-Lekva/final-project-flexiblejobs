package servlets;

import jobs.ApplicationDAO;
import jobs.JobDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/appManager")
public class ApplicationManager extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationDAO appDao=(ApplicationDAO) req.getServletContext().getAttribute("appDao");
        JobDatabase jobDao=(JobDatabase) req.getServletContext().getAttribute("jobDao");
        String employee=req.getParameter("employee");
        String parameter=req.getParameter("jobId");
        int jobId;
        if(parameter.charAt(0)=='+') {
            jobId=Integer.parseInt(parameter.substring(1));
            appDao.changeApplicationStatus(FlexibleJobsConstants.APPLICATION_STATUS_HIRED,employee,
                    jobId);
            jobDao.addEmployeeToJob(employee, jobId);
        }else{
            jobId=Integer.parseInt(parameter);
            appDao.changeApplicationStatus(FlexibleJobsConstants.APPLICATION_STATUS_REJECTED,employee,
                    jobId);
        }
       req.getRequestDispatcher("/Front/EmployerJob.jsp?jobId="+jobId).forward(req,resp);
    }
}
