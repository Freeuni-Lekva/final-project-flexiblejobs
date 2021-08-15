package servlets;

import jobs.Job;
import jobs.JobDatabase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Date;

@WebServlet ("/JobCreationServlet")
public class JobCreationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        DataSource source = (DataSource) servletContext.getAttribute("datasource");
        JobDatabase jobDatabase = new JobDatabase(source);
        String employer = req.getParameter("employer");
        String jobHeader = req.getParameter("title");
        String description = req.getParameter("description");
        double budget = Double.parseDouble(req.getParameter("budget"));
        String duration = req.getParameter("jobduration");
        Date currDate = new Date();
        Job job = new Job(employer, jobHeader, description, budget, duration, currDate.toString());

        jobDatabase.saveJob(job);
        //jobDatabase.removeJob(1);
        // RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/egaris.jsp");
        // dispatcher.forward(req, resp);
    }

}
