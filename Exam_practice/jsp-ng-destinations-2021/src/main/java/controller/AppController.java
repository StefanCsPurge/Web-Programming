package controller;

import db.Manager;
import model.Asset;
import model.User;
import model.VDestination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    public static Integer getDestinationId(String name) {
        String sql = "SELECT id FROM vacationdestinations WHERE destination='" + name + "';";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                return result.getInt("id");
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean destinationNotBanned(String user, Integer destinationId){
        String sql = "SELECT id FROM bannedlist WHERE destinationid='" + destinationId + "' and user='" + user + "'";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            return !result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static List<VDestination> getSearchedDestinations(String user, String destName) {
        String sql = "SELECT * FROM vacationdestinations WHERE destination LIKE '%" + destName + "%'";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            List<VDestination> dests = new ArrayList<>();

            while (result.next()){
                if(destinationNotBanned(user,result.getInt("id")))
                    dests.add(new VDestination(
                            result.getInt("id"),
                            result.getString("destination"),
                            result.getString("country"),
                            result.getInt("price")
                    ));
            }
            return dests;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void addBan(String user, Integer destId) {
        String sql = "INSERT INTO bannedlist(user, destinationid) VALUES ('" + user + "','" + destId + "')";
        PreparedStatement stmt;
        try {
            stmt = Manager.getConnection().prepareStatement(sql);
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
                return new User(result.getInt("id"),result.getString("username"));
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<Asset> getAssetsOfUser(int userid) {
        String sql = "SELECT * FROM assets WHERE userid=" + userid;
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            List<Asset> assets = new ArrayList<>();

            while (result.next()){
                assets.add(new Asset(
                        result.getInt("id"),
                        result.getInt("userid"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getInt("value")
                ));
            }
            return assets;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    public static void addAsset(int userId, String name, String desc, int value) {
        String sql = "INSERT INTO assets(userid, name, description, value) VALUES (" + userId + ",'" + name + "','" + desc + "'," + value + ")";
        PreparedStatement stmt;
        try {
            stmt = Manager.getConnection().prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
