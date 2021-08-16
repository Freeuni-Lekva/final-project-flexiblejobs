package servlets;

import accounts.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/registerHandler")
public class RegisterHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");

        String username = req.getParameter("username");
        String receivedPassword = req.getParameter("password");
        String type = req.getParameter("type");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String livingPlace = req.getParameter("livingPlace");
        String profileHeading = req.getParameter("profileHeading");
        String profileDescription = req.getParameter("profileDescription");


        if (accountDao.selectByUsername(username) != null) {
            req.getRequestDispatcher("/Front/registeredProblem.jsp").forward(req, resp);
        } else {
            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(receivedPassword.getBytes());
            String password = new String(messageDigest.digest());

            Account account = null;
            PersonalData personalData = new PersonalData(username, firstname, lastname, livingPlace, profileHeading, profileDescription);


            switch (type) {
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE:
                    account = new Employee(username, password, 0, new BigDecimal("0.00"));
                    break;
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER:
                    account = new Employer(username, password, 1000, new BigDecimal("0.00"));
                    break;
                case FlexibleJobsConstants.ACCOUNT_ROLE_ADMINISTRATOR:
                    account = new Administrator(username, password, 0, new BigDecimal("0.00"));
                    break;
            }

            account.setPersonalData(personalData);
            accountDao.addAccount(account);
            req.getSession().setAttribute("loggedUser",account);

            switch (type) {
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE:
                    req.getRequestDispatcher("/Front/successfulLoginEmployee.jsp").forward(req, resp);
                    break;
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER:
                    req.getRequestDispatcher("/Front/successfulLoginEmployer.jsp").forward(req, resp);
                    break;
                case FlexibleJobsConstants.ACCOUNT_ROLE_ADMINISTRATOR:
                    req.getRequestDispatcher("/Front/successfulLoginAdmin.jsp").forward(req, resp);
                    break;
            }

        }
    }
}
