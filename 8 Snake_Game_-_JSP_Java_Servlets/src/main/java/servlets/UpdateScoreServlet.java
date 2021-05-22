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

@WebServlet("/UpdateScoreServlet")
public class UpdateScoreServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestData = req.getReader().lines().collect(Collectors.joining());
        try {
            JSONObject json = new JSONObject(requestData);
            Integer score = json.getInt("score");

            User currentUser = (User) req.getSession().getAttribute("user");
            if (currentUser != null) {
                currentUser.setHigh_score(score);
                ProfilesController.update(currentUser);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
