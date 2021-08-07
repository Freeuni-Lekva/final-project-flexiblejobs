package servlets;

import accounts.AccountDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


@WebServlet ("/loginHandler")
public class LoginHandler extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");

        String username = req.getParameter("username");

        if(accountDao.SelectByUsername(username)!=null){
            req.getRequestDispatcher("/Front/existedAccount.jsp").forward(req,resp);
        } else{
            req.getRequestDispatcher("/Front/invalidUser.jsp").forward(req,resp);
        }
    }
}
