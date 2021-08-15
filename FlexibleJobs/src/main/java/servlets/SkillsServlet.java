package servlets;

import accounts.Account;
import accounts.AccountDao;
import accounts.Message;
import accounts.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");
        MessageDao messageDao = (MessageDao) req.getServletContext().getAttribute("messageDao");

        Account loggedUser = (Account) req.getSession().getAttribute("loggedUser");
        String with = req.getParameter("conversationWith");
        Account conversationWith = accountDao.selectByUsername(with);

        List<Message> conversation = messageDao.getConversation(loggedUser.getUserName(), conversationWith.getUserName());

        req.getServletContext().setAttribute("messageDao", messageDao);
        req.getSession().setAttribute("conversationWith", with);
//        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
}
