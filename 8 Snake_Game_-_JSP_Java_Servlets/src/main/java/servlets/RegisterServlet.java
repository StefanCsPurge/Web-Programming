package servlets;

import authentication.CredentialsManager;
import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CredentialsManager.register(
                new User(req.getParameter("username")),
                req.getParameter("password"));

        resp.sendRedirect("/login.jsp");
    }
}
