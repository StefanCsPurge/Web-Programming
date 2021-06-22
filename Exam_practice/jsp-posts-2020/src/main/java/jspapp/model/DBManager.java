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
            rs = stmt.executeQuery("select id,user from posts where user='"+username+"'");
            if (rs.next()) {
                u = username;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public String findTopicId(String name){
        String id = null;
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select id from topics where topicname='"+name+"'");
            if (rs.next()) {
                id = rs.getString("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void addTopic(String name) {
        String sql = "INSERT INTO topics(topicname) VALUES ('" + name + "')";
        PreparedStatement s;
        try {
            s = con.prepareStatement(sql);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPost(String username, Integer topicId, String text, Long cdate) {
        String sql = "INSERT INTO posts(user, topicid, text, date) " +
                "VALUES ('" + username + "','" + topicId + "','" + text + "','" + cdate + "')";
        PreparedStatement s;
        try {
            s = con.prepareStatement(sql);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePost(Integer postId, String username, Integer topicId, String text, Long cdate) {
        String sql = "UPDATE posts SET user='" + username +
                "', topicid='" + topicId +
                "', text='" + text +
                "', date='" + cdate + "' WHERE id='" + postId + "'";
        PreparedStatement s;
        try {
            s = con.prepareStatement(sql);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
