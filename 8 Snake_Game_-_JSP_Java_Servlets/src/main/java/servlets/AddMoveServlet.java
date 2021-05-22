package servlets;

import controller.ProfilesController;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/AddMoveServlet")
public class AddMoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestData = req.getReader().lines().collect(Collectors.joining());
        try {
            JSONObject json = new JSONObject(requestData);
            var username = json.getString("username");
            var x = json.getInt("x");
            var y = json.getInt("y");
            //System.out.println(username + " " + x + " " + y);
            ProfilesController.addSnakeMove(username,x,y);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //User currentUser = (User) req.getSession().getAttribute("user");
        // if (currentUser==null) {
        //        resp.sendRedirect("/index.jsp");
        //        return;
        //   }

        //set the current user again
        // req.getSession().setAttribute("user", ProfilesController.getUserWithUsername(currentUser.getUsername()));
        // resp.sendRedirect("/homepage.jsp");
    }
}
