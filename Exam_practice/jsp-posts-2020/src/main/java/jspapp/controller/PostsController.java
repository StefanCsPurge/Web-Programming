package jspapp.controller;

import jspapp.model.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostsController extends HttpServlet {
    private boolean postAdded = false;
    private String userThatAdded = "";
    private String addedTopicId = "";
    private String addedText = "";
    private String addedDate = "";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ((action != null) && action.equals("getAllPosts")) {
            DBManager dbmanager = new DBManager();
            //String user = (String) request.getSession().getAttribute("user");

            ResultSet rs;
            StringBuilder content = new StringBuilder("<table border=1><tr><th>ID</th><th>User</th><th>TopicID</th><th>Text</th><th>Date</th></tr>");
            try {
                Statement stmt = dbmanager.getStmt();
                String sql = "select * from posts";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    content.append("<tr><td>")
                            .append(rs.getString("id")).append("</td><td>")
                            .append(rs.getString("user")).append("</td><td>")
                            .append(rs.getString("topicid")).append("</td><td>")
                            .append(rs.getString("text")).append("</td><td>")
                            .append(rs.getString("date")).append("</td></tr>");
                }
                content.append("</table>");
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter out = new PrintWriter(response.getOutputStream());
            out.println(content);
            out.flush();
        }
        else if(((action != null) && action.equals("checkNotification"))){
            String user = request.getParameter("user");
            if(postAdded && !user.equals(userThatAdded)){
                PrintWriter out = new PrintWriter(response.getOutputStream());
                out.println("User " + userThatAdded + " added the article: \n" +
                        "TopicID '" + addedTopicId + "', text '" + addedText + "', date '" + addedDate + "'");
                out.flush();
                postAdded = false;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ((action != null) && action.equals("addPost")) {
            String user = request.getParameter("user");
            String topicName = request.getParameter("topicname");
            String text = request.getParameter("text");
            String date = request.getParameter("date");

            DBManager dbmanager = new DBManager();
            String topicId = dbmanager.findTopicId(topicName);
            if(topicId == null)
            {
                dbmanager.addTopic(topicName);
                topicId = dbmanager.findTopicId(topicName);
            }
            dbmanager.addPost(user,Integer.parseInt(topicId),text,Long.parseLong(date));

            postAdded = true;
            userThatAdded = user;
            addedTopicId = topicId;
            addedText = text;
            addedDate = date;
        }
        else if ((action != null) && action.equals("updatePost")) {
            String user = request.getParameter("user");
            String postId = request.getParameter("postid");
            String topicName = request.getParameter("topicname");
            String text = request.getParameter("text");
            String date = request.getParameter("date");

            DBManager dbmanager = new DBManager();
            String topicId = dbmanager.findTopicId(topicName);
            if(topicId == null)
            {
                dbmanager.addTopic(topicName);
                topicId = dbmanager.findTopicId(topicName);
            }
            dbmanager.updatePost(Integer.parseInt(postId),
                    user,
                    Integer.parseInt(topicId),
                    text,
                    Long.parseLong(date));
        }
    }
}
