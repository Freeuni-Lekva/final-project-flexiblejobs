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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/message")
public class SendMessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");
        MessageDao messageDao = (MessageDao) req.getServletContext().getAttribute("messageDao");

        Account loggedUser = (Account) req.getSession().getAttribute("loggedUser");
        String with = req.getParameter("conversationWith");
        Account conversationWith = accountDao.selectByUsername(with);
        String content = req.getParameter("content");

        List<Message> conversation = messageDao.getConversation(loggedUser.getUserName(), conversationWith.getUserName());
        Message message = new Message(loggedUser.getUserName(), conversationWith.getUserName(), content, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        messageDao.addMessage(message);
        req.getSession().setAttribute("sender", loggedUser.getUserName());
        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }
}
