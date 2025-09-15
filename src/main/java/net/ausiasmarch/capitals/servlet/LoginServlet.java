package net.ausiasmarch.capitals.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import net.ausiasmarch.capitals.dao.UserDao;
import net.ausiasmarch.capitals.model.UserBean;
import java.io.IOException;

@WebServlet("/capitals/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao oUserDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (oUserDao.authenticate(username, password)) {
            UserBean user = oUserDao.getUserByUsername(username);
            request.getSession().setAttribute("sessionUser", user);
            response.sendRedirect("GameServlet");
        } else {
            request.setAttribute("error", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }
}
