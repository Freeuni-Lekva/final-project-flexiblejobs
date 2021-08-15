package servlets;

import states.State;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String keyword = req.getParameter("keyword");
        State state = (State) req.getSession().getAttribute("state");
        state.setSearchKeyword(keyword);
        state.setSearchedByFilter(true);

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
