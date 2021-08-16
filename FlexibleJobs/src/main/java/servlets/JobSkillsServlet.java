package servlets;

import jobs.JobDatabase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/JobSkillsServlet")
public class JobSkillsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ID = Integer.parseInt(req.getParameter("id"));

        Set<String> skills = new HashSet<>(req.getParameterMap().keySet());

        JobDatabase jobDatabase = (JobDatabase) req.getServletContext().getAttribute("jobDao");

        if (!skills.isEmpty()) {
            jobDatabase.addJobSkills(skills, ID);
        }

        req.getRequestDispatcher("/Front/successfulLoginEmployer.jsp").forward(req, resp);
    }
}
