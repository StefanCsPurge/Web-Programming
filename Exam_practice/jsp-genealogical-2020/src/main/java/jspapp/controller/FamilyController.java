package jspapp.controller;

import jspapp.model.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FamilyController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String mother = request.getParameter("mother");
        String father = request.getParameter("father");
        RequestDispatcher rd;

        DBManager dbmanager = new DBManager();
        dbmanager.addParents(username, mother, father);

        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        String action = request.getParameter("action");

        if ((action != null) && action.equals("getFatherLine")) {
            DBManager dbmanager = new DBManager();
            String username = request.getParameter("username");

            StringBuilder content = new StringBuilder("<ol>");
            String father = dbmanager.getFather(username);
            while(father!=null) {
                content.append("<li>").append(father).append("</li>");
                username = father;
                father = dbmanager.getFather(username);
            }
            content.append("</ol>");

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(content);
            out.flush();
        }
        else if(((action != null) && action.equals("getMotherLine"))){
            DBManager dbmanager = new DBManager();
            String username = request.getParameter("username");

            StringBuilder content = new StringBuilder("<ol>");
            String mother = dbmanager.getMother(username);
            while(mother!=null) {
                content.append("<li>").append(mother).append("</li>");
                mother = dbmanager.getMother(mother);
            }
            content.append("</ol>");

            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(content);
            out.flush();
            }

        }
}
