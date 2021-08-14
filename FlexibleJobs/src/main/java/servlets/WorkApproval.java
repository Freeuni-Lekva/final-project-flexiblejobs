package servlets;

import accounts.Account;
import accounts.AccountDao;
import accounts.Review;
import accounts.ReviewDao;

import jobs.ApplicationDAO;
import jobs.JobDatabase;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/approve")
public class WorkApproval extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int jobId=Integer.parseInt(req.getParameter("jobId"));
        JobDatabase jobDatabase=(JobDatabase) req.getServletContext().getAttribute("jobDao");
        jobDatabase.updateJobStatus(jobId,FlexibleJobsConstants.JOB_STATUS_FINISHED);
        req.getRequestDispatcher("/Front/successfulLoginEmployer.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rate=Integer.parseInt(req.getParameter("rate"));
        int jobId=Integer.parseInt(req.getParameter("jobId"));
        String employee=req.getParameter("employee");
        ApplicationDAO applicationDAO=(ApplicationDAO) req.getServletContext().getAttribute("appDao");
        ReviewDao reviewDao=(ReviewDao) req.getServletContext().getAttribute("reviewDao");
        AccountDao accountDao=(AccountDao)req.getServletContext().getAttribute("accountDao");
        Account employer=(Account) req.getSession().getAttribute("loggedUser");
        int bid=applicationDAO.getBid(jobId,employee);
        employer.withdraw(bid);
        Review review=new Review(employer.getUserName(),employee,rate,jobId);
        reviewDao.addReview(review);
        double rating=reviewDao.averageReview(employee);
        accountDao.updateRating(employee,rating);
        int bal=accountDao.getCurrentBalance(employee);
        AccountDao.updateBalance(employee,bal+bid);
        applicationDAO.changeApplicationStatus(FlexibleJobsConstants.APPLICATION_STATUS_FINISHED,
                employee,jobId);
        req.getRequestDispatcher("/Front/EmployerJob.jsp?jobId="+jobId).forward(req,resp);
    }
}
