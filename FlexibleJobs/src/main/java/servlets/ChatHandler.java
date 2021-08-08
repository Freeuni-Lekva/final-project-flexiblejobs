package servlets;

import accounts.Account;
import accounts.Employee;
import accounts.Message;
import accounts.MessageDao;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Chat")
public class ChatHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Account active=(Account) req.getSession().getAttribute("loggedUser");
//        String addressee=req.getParameter("addressee");
//        if(active==null||addressee==null)
//            return;
        Account test1=new Employee("jemal12","jinvalirules");
        req.getSession().setAttribute("loggedUser",test1);
        req.getSession().setAttribute("addressee","kutukaca xarxela");
        MessageDao dao=new MessageDao((MysqlConnectionPoolDataSource)req.getServletContext().getAttribute("datasource"));
        req.getServletContext().setAttribute("messageDao",dao);
        req.getRequestDispatcher("/Front/personalChat.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from=((Account)(req.getSession().getAttribute("loggedUser"))).getUserName();
        String to=(String) req.getSession().getAttribute("addressee");
        String text=req.getParameter("message");
        MessageDao dao=(MessageDao) req.getServletContext().getAttribute("messageDao");
        dao.addMessage(new Message(from, to, text));
        req.getRequestDispatcher("/Front/personalChat.jsp").forward(req,resp);
    }
}
