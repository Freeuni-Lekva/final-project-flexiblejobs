package servlets;

import accounts.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/updateData")
public class PersonalDataUpdater extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Front/settingsPage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonalDataDao dao=(PersonalDataDao) req.getServletContext().getAttribute("personalDataDao");
        String firstname=req.getParameter("firstname");
        String lastname=req.getParameter("lastname");
        String livingplace=req.getParameter("livingplace");
        String heading=req.getParameter("heading");
        String description=req.getParameter("description");
        String username=((Account)req.getSession().getAttribute("loggedUser")).getUserName();
        PersonalData data=new PersonalData(username,firstname,lastname,livingplace,heading,description);
        dao.updateData(data,username);
        req.getRequestDispatcher("/Front/dataUpdated.jsp").forward(req,resp);
    }
}
