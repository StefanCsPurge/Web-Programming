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

    public void connect() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/some_users", "root", "");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("connect error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public String authenticate(String username, String parent) {
        ResultSet rs;
        String u = null;
        System.out.println(username+" "+parent);
        try {
            rs = stmt.executeQuery("select * from familyrelations where username='"+username+"' " +
                    "and (mother='"+parent+"' OR father='" + parent + "')");
            if (rs.next()) {
                u = username;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public void addParents(String username, String mother, String father)
    {
        String sql = "INSERT INTO familyrelations(username, mother, father) VALUES ('" + username + "','" + mother + "','" + father + "')";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getFather(String username){
        String father = null;
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select father from familyrelations where username='"+username+"'");
            if (rs.next()) {
                father = rs.getString("father");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return father;
    }

    public String getMother(String username){
        String mother = null;
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select mother from familyrelations where username='"+username+"'");
            if (rs.next()) {
                mother = rs.getString("mother");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mother;
    }
}
