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
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");
        MessageDao messageDao = (MessageDao) req.getServletContext().getAttribute("messageDao");

        State state = (State) req.getSession().getAttribute("state");
        Account loggedUser = state.getLoggedUser();
        String with = req.getParameter("conversationWith");
        Account conversationWith = accountDao.selectByUsername(with);
        List<Message> conversation = messageDao.getConversation(loggedUser.getUserName(), conversationWith.getUserName());

        state.setConversation(conversation);
        state.setConversationWith(conversationWith);
        state.setChatOpened(true);
        state.setChatStarted(true);

        String type = loggedUser.getType();

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageDao messageDao = (MessageDao) req.getServletContext().getAttribute("messageDao");

        State state = (State) req.getSession().getAttribute("state");
        Account loggedUser = state.getLoggedUser();
        Account conversationWith = state.getConversationWith();
        List<Message> conversation = messageDao.getConversation(loggedUser.getUserName(), conversationWith.getUserName());

        state.setConversation(conversation);


        String type = loggedUser.getType();

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
