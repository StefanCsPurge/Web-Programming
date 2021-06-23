package jspapp.model;

import jspapp.domain.Order;
import jspapp.domain.User;

import java.sql.*;

/**
 * Created by spurge.
 */
public class DBManager {
    private Connection con;
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public Connection getCon() {
        return con;
    }

    public void connect() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/catalog", "root", "");
            stmt = con.createStatement();
        } catch(Exception ex) {
            System.out.println("connect error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet rs;
        User u = null;
        System.out.println(username);
        try {
            rs = stmt.executeQuery("select * from users where username='"+username+"' and password='"+password+"'");
            if (rs.next()) {
                u = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    public void addProduct(String name, String description){
        String sql = "INSERT INTO products(name, description) VALUES ('" + name + "','" + description + "')";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder(Order o){
        String sql = "INSERT INTO orders(user, productid, quantity) VALUES " +
                "('" + o.getUser() + "','" + o.getProductId() + "','" + o.getQuantity() + "')";
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getProductId(String name){
        Integer id = null;
        ResultSet rs;
        try {
            rs = stmt.executeQuery("select id from products where name='"+name+"'");
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
