package servlets;

import accounts.Account;
import accounts.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@WebServlet("/existedAccLogin")
public class ExistedAccountLogin extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");

            String username = req.getParameter("username");
/*
            String receivedPassword = req.getParameter("password");

            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(receivedPassword.getBytes());
            String password = new String(messageDigest.digest());

 */
            String password = req.getParameter("password");

            Account account = accountDao.SelectByUsername(username);
            if(account!=null && account.getPassword().equals(password)){
                if(account.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE)) {
                    req.getRequestDispatcher("/Front/successfulLoginEmployee.jsp").forward(req, resp);
                } else if(account.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER)){
                    req.getRequestDispatcher("/Front/successfulLoginEmployer.jsp").forward(req, resp);
                } else if(account.getType().equals(FlexibleJobsConstants.ACCOUNT_ROLE_ADMINISTRATOR)){
                    req.getRequestDispatcher("/Front/successfulLoginAdmin.jsp").forward(req, resp);
                }

            } else{
                req.getRequestDispatcher("/Front/invalidUser.jsp").forward(req,resp);
            }
        }

}
