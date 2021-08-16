package servlets;

import accounts.Account;
import accounts.AccountDao;
import accounts.Message;
import accounts.MessageDao;
import states.State;

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

        State state = (State) req.getSession().getAttribute("state");
        Account loggedUser = state.getLoggedUser();
        double d=accountDao.getRating(loggedUser.getUserName());
        int b=accountDao.getCurrentBalance(loggedUser.getUserName());
        loggedUser.setBalance(b);
        loggedUser.setRating(d);
        String with = state.getConversationWith().getUserName();
        Account conversationWith = accountDao.selectByUsername(with);
        String content = req.getParameter("content");

        Message message = new Message(loggedUser.getUserName(), conversationWith.getUserName(), content, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        messageDao.addMessage(message);

        List<Message> conversation = messageDao.getConversation(loggedUser.getUserName(), conversationWith.getUserName());

        state.setReloadForChatPartner(true);
        state.setConversation(conversation);
        switch (state.getLoggedUser().getType()) {
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
