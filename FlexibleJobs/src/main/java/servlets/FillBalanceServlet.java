package servlets;

import accounts.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/fillBalance")
public class FillBalanceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int jobId =  Integer.parseInt(req.getParameter("jobId"));
        String employee=req.getParameter("employee");
       Account acc = (Account) req.getSession().getAttribute("loggedUser");
       acc.deposit(1000);
       req.getRequestDispatcher("/Front/approveWork.jsp?jobId="+jobId+"&?employee="+employee).forward(req,resp);
    }
}
