package controller;

import db.Manager;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfilesController {

    public static void addSnakeMove(String username, Integer x, Integer y) {
        String sql = "INSERT INTO snake_moves(username,x,y) VALUES ('" + username + "', '" + x + "', '" + y + "');";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUserWithUsername(String username) {
        String sql = "SELECT * FROM users WHERE username='" + username + "';";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                User user = new User();
                user.setUsername(result.getString("username"));
                user.setTime_spent(result.getLong("time_spent"));
                user.setHigh_score(result.getInt("high_score"));
                return user;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void update(User user) {
        String sql = "UPDATE users SET time_spent='" + user.getTime_spent() +
                "', high_score='" + user.getHigh_score() + "'"
                + " WHERE username='" + user.getUsername() + "';";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            stmt.execute();
            Manager.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> search(String username){
        String sql = "SELECT * FROM users WHERE username<>'" + username + "';";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            List<User> users = new ArrayList<>();

            while (result.next()){
                User user = new User();
                user.setUsername(result.getString("username"));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
