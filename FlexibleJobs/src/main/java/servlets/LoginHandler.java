package servlets;

import accounts.Account;
import accounts.AccountDao;
import states.State;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/login")
public class LoginHandler extends HttpServlet {
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
        Account account = accountDao.selectByUsername(username);
        ArrayList<String> error = new ArrayList<>();

        if (account == null) {
            error.add(0, FlexibleJobsConstants.NO_ACCOUNT);
            State state = new State(null, null, error, null, null, null, false, false);
            req.getSession().setAttribute("state", state);
            req.getRequestDispatcher("/Front/login.jsp").forward(req, resp);
        } else if (!account.getPassword().equals(password)) {
            error.add(0, FlexibleJobsConstants.INCORRECT_PASSWORD);
            State state = new State(null, null, error, null, null, null, false, false);
            req.getSession().setAttribute("state", state);
            req.getRequestDispatcher("/Front/login.jsp").forward(req, resp);
        } else {
            List<Account> contacts = accountDao.selectAllByType("employer"); //TODO change with real contacts
            State state = new State(account, null, null, contacts, null, null, false, false);
            accountDao.logIn(account.getUserName());
            req.getSession().setAttribute("state", state);
            switch (account.getType()) {
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
