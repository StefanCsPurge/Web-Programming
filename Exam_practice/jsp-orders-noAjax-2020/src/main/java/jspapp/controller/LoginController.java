package jspapp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                                throws ServletException, IOException {

        String username = request.getParameter("username");
        RequestDispatcher rd;

        if (username != null) {
            rd = request.getRequestDispatcher("/homepage.jsp");
            request.setAttribute("user", username);
            HttpSession session = request.getSession();
            session.setAttribute("user", username);

        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        rd.forward(request, response);
    }
}
