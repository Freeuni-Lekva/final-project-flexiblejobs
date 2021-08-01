package servlets;

import accounts.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/existedAccLogin")
public class ExistedAccountLogin extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            AccountDao accountDao = new AccountDao((DataSource) req.getServletContext().getAttribute("datasource"));

            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if(accountDao.SelectByUsername(username)!=null && accountDao.SelectByUsername(username).getPassword().equals(password)){
                req.getRequestDispatcher("/Front/successfulLogin.jsp").forward(req,resp);
            } else{
                req.getRequestDispatcher("/Front/invalidUser.jsp").forward(req,resp);
            }
        }

}
