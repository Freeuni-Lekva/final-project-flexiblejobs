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

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> skills = new ArrayList<>(req.getParameterMap().keySet());

        AccountDao accountDao = (AccountDao) req.getServletContext().getAttribute("accountDao");

        State state = (State) req.getSession().getAttribute("state");
        Account userRegistering = state.getUserRegistering();
        //TODO save skills
        req.getRequestDispatcher("/Front/successfulLoginEmployee.jsp").forward(req, resp);
    }
}
