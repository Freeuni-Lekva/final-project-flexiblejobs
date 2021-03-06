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

        state.setLoggedUser(state.getUserRegistering());
        state.setUserRegistering(null);

        List<Account> employers = accountDao.selectAllByType("employer");//TODO change with real contacts
        List<Account> employees = accountDao.selectAllByType("employee");

        List<Account> contacts = new ArrayList<>();
        for(Account acc : employees){
            if(!acc.getUserName().equals(state.getLoggedUser().getUserName())){
                contacts.add(acc);
            }
        }

        for(Account acc : employers){
            if(!acc.getUserName().equals(state.getLoggedUser().getUserName())){
                contacts.add(acc);
            }
        }

        state.setContacts(contacts);
        //TODO save skills
        if (!skills.isEmpty()) {
            accountDao.addSkills(state.getLoggedUser().getUserName(), skills);
        }
        req.getRequestDispatcher("/Front/successfulLoginEmployee.jsp").forward(req, resp);
    }
}
