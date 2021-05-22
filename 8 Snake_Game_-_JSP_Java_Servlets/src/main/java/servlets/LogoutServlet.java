package servlets;

import controller.ProfilesController;
import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var theUser = (User)req.getSession().getAttribute("user");
        // get time spent by the user in the game
        var millis = req.getSession().getLastAccessedTime() - req.getSession().getCreationTime();
        theUser.setTime_spent(theUser.getTime_spent() + millis);
        ProfilesController.update(theUser);

//        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
//                TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
//                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1));
//        Time time_spent = Time.valueOf(hms);

        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("users", null);
        resp.sendRedirect( "/index.jsp" );
    }
}
