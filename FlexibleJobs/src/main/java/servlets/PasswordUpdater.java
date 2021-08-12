package servlets;

import accounts.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebServlet("/updatePassword")
public class PasswordUpdater extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account acc=(Account) req.getSession().getAttribute("loggedUser");
        String receivedPassword=req.getParameter("password");
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(receivedPassword.getBytes());
        String password = new String(messageDigest.digest());
        acc.changePassword(password);
        req.getRequestDispatcher("/Front/passwordUpdated.jsp").forward(req,resp);
    }
}
