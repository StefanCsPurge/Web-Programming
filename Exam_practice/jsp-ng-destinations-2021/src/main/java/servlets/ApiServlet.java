package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.AppController;
import model.Asset;
import model.VDestination;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.attribute.standard.Destination;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/asset")
public class ApiServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ((action != null) && action.equals("addAssets")) {
            String newAssetsToAddJSON = request.getParameter("newAssetsToAdd");
            System.out.println("received post");
            System.out.println(newAssetsToAddJSON);

            ObjectMapper mapper = new ObjectMapper();   // JSON magic happens here
            Asset[] assets = mapper.readValue(newAssetsToAddJSON, Asset[].class);

            for(Asset asset : assets) {
                System.out.println(asset.toString());
                AppController.addAsset(asset.getUserId(), asset.getName(), asset.getDescription(), asset.getValue());
            }
        } else if((action != null) && action.equals("addBan")){
            String username = request.getParameter("user");
            String destination = request.getParameter("destination");
            Integer destId = AppController.getDestinationId(destination);
            if(destId != null){
                AppController.addBan(username,destId);
                System.out.println("Added destination ban");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ((action != null) && action.equals("getAssets")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            JSONObject answer = new JSONObject();
            JSONArray jsonAssets = new JSONArray();
            for (Asset asset : AppController.getAssetsOfUser(userId)) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", asset.getId());
                jObj.put("userId", asset.getUserId());
                jObj.put("name", asset.getName());
                jObj.put("description", asset.getDescription());
                jObj.put("value", asset.getValue());
                jsonAssets.add(jObj);
            }
            answer.put("assets", jsonAssets);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(answer);
            out.flush();
        }
        else if((action != null) && action.equals("getDestinations")){
            String destName = request.getParameter("name");
            String user = request.getParameter("user");
            //System.out.println(name);
            //JSONObject answer = new JSONObject();
            JSONArray jsonDestinations = new JSONArray();
            for (VDestination d : AppController.getSearchedDestinations(user,destName)) {
                JSONObject jObj = new JSONObject();
                jObj.put("id", d.getId());
                jObj.put("destination", d.getDestination());
                jObj.put("country", d.getCountry());
                jObj.put("price", d.getPrice());
                jsonDestinations.add(jObj);
            }
            //answer.put("assets", jsonAssets);
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(jsonDestinations);
            out.flush();
        }
    }
}
