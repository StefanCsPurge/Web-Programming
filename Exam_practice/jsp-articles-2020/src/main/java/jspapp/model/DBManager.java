package jspapp.model;

import java.sql.*;

/**
 * Created by spurge.
 */
public class DBManager {

    private Statement stmt;
    private Connection con;

    public DBManager() {
        connect();
    }

    public Statement getStmt() {
        return stmt;
    }

    public Connection getCon() {
        return con;
    }

    public void connect() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/phplab", "root", "");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("connect error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String authenticate(String username) {
        ResultSet rs;
        String u = null;
        System.out.println(username);
        try {
            rs = stmt.executeQuery("select id,user from articles where user='"+username+"'");
            if (rs.next()) {
                u = username;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public String findJournalId(String name){
        String id = null;
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select id from journals where name='"+name+"'");
            if (rs.next()) {
                id = rs.getString("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addJournal(String journalName) {
        String sql = "INSERT INTO journals(name) VALUES ('" + journalName + "')";
        PreparedStatement s;
        try {
            s = con.prepareStatement(sql);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addArticle(String username, Integer journalId, String summary, Long cdate) {
        String sql = "INSERT INTO articles(user, journalid, summary, date) " +
                "VALUES ('" + username + "','" + journalId + "','" + summary + "','" + cdate + "')";
        PreparedStatement s;
        try {
            s = con.prepareStatement(sql);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
