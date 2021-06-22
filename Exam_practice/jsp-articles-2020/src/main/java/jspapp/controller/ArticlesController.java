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

public class ArticlesController extends HttpServlet {
    private boolean articleAdded = false;
    private String userThatAdded = "";
    private String addedJournalId = "";
    private String addedSummary = "";
    private String addedDate = "";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ((action != null) && action.equals("getArticlesForJournal")) {
            DBManager dbmanager = new DBManager();
            String user = (String) request.getSession().getAttribute("user");
            String journalName = request.getParameter("journalname");
            String journalId = dbmanager.findJournalId(journalName);

            ResultSet rs;
            StringBuilder content = new StringBuilder("<table border=1><tr><th>ID</th><th>User</th><th>JournalID</th><th>Summary</th><th>Date</th></tr>");
            try {
                Statement stmt = dbmanager.getStmt();
                String sql = "select * from articles where user='"+user + "' and journalid='"+journalId+"'";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    content.append("<tr><td>")
                            .append(rs.getString("id")).append("</td><td>")
                            .append(rs.getString("user")).append("</td><td>")
                            .append(rs.getString("journalid")).append("</td><td>")
                            .append(rs.getString("summary")).append("</td><td>")
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
            if(articleAdded && !user.equals(userThatAdded)){
                PrintWriter out = new PrintWriter(response.getOutputStream());
                out.println("User " + userThatAdded + " added the article: \n" +
                        "JournalID '" + addedJournalId + "', summary '" + addedSummary + "', date '" + addedDate + "'");
                out.flush();
                articleAdded = false;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ((action != null) && action.equals("addArticle")) {
            String user = request.getParameter("user");
            String journalName = request.getParameter("journalname");
            String journalSummary = request.getParameter("journalsummary");
            String date = request.getParameter("date");

            DBManager dbmanager = new DBManager();
            String journalId = dbmanager.findJournalId(journalName);
            if(journalId == null)
            {
                dbmanager.addJournal(journalName);
                journalId = dbmanager.findJournalId(journalName);
            }
            dbmanager.addArticle(user,Integer.parseInt(journalId),journalSummary,Long.parseLong(date));

            articleAdded = true;
            userThatAdded = user;
            addedJournalId = journalId;
            addedSummary = journalSummary;
            addedDate = date;
        }
    }
}
