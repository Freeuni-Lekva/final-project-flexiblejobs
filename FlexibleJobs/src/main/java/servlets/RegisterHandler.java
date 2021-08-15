package servlets;

import accounts.*;
import states.State;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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


        Account account = null;
        ArrayList<String> error = new ArrayList<>();
        boolean isNull = false;

        if (username.equals("")) {
            error.add(FlexibleJobsConstants.NULL_USERNAME);
            isNull = true;
        }
        if (receivedPassword.equals("")) {
            error.add(FlexibleJobsConstants.NULL_PASSWORD);
            isNull = true;
        }
        if (firstname.equals("")) {
            error.add(FlexibleJobsConstants.NULL_FIRSTNAME);
            isNull = true;
        }
        if (lastname.equals("")) {
            error.add(FlexibleJobsConstants.NULL_LASTNAME);
            isNull = true;
        }
        if (livingPlace.equals("")) {
            error.add(FlexibleJobsConstants.NULL_LIVING_PLACE);
            isNull = true;
        }
        if (profileHeading.equals("")) {
            error.add(FlexibleJobsConstants.NULL_HEADING);
            isNull = true;
        }
        if (profileDescription.equals("")) {
            error.add(FlexibleJobsConstants.NULL_DESCRIPTION);
            isNull = true;
        }

        if (accountDao.selectByUsername(username) != null) {
            error.add(FlexibleJobsConstants.EXISTED_ACCOUNT);
            isNull = true;
        }

        if (isNull) {
            State state = new State(null, null, error, null, null, null, false, false,false,false,null);
            req.getSession().setAttribute("state", state);
            req.getRequestDispatcher("/Front/register.jsp").forward(req, resp);
        } else {

            MessageDigest messageDigest = null;
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            messageDigest.update(receivedPassword.getBytes());
            String password = new String(messageDigest.digest());

            PersonalData personalData = new PersonalData(username, firstname, lastname, livingPlace, profileHeading, profileDescription);


            switch (type) {
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE:
                    account = new Employee(username, password, 0, new BigDecimal("0.00"));
                    break;
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER:
                    account = new Employer(username, password, 0, new BigDecimal("0.00"));
                    break;
                case FlexibleJobsConstants.ACCOUNT_ROLE_ADMINISTRATOR:
                    account = new Administrator(username, password, 0, new BigDecimal("0.00"));
                    break;
            }

            account.setPersonalData(personalData);
            accountDao.addAccount(account);

            State state = new State(null, account, null, null, null, null, false, false,false,false,null);
            req.getSession().setAttribute("state", state);
            switch (type) {
                case FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE:
                    req.getRequestDispatcher("/Front/skills.jsp").forward(req, resp);
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
