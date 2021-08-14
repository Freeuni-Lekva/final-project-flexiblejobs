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
import java.util.HashSet;
import java.util.Set;

@WebServlet ("/JobsServlet")
public class JobsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        JobDatabase jobDatabase = (JobDatabase) servletContext.getAttribute("jobDao");
        String employer = req.getParameter("employer");
        String jobHeader = req.getParameter("title");
        String description = req.getParameter("description");
        double budget = Double.parseDouble(req.getParameter("budget"));
        String duration = req.getParameter("jobduration");
        String currDate = new Date().toString();
        Job job = new Job(employer, jobHeader, description, budget, duration, currDate);
        Set<String> skills = new HashSet<>();
        for(int i = 1; i <= FlexibleJobsConstants.MAX_SKILLS; i++){
            String skill = req.getParameter("skill" + i);
            if(skill != null && !skill.equals("")) {
                skills.add(skill);
            }
        }
        
        jobDatabase.saveJob(job);



    }

}
