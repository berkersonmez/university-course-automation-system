/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Berker
 */
public class AdminCollectionJDBC extends DBConnection {

    public AdminCollectionJDBC() {
        super();
    }

    public List<Admin> getAdmins() {
        List<Admin> admins = new LinkedList<Admin>();
        try {
            String query = "SELECT user.id, admin.id, name, username, password FROM user JOIN admin ON (userID = user.id)";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("user.id");
                Integer adminID = results.getInt("admin.id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                
                Admin admin = new Admin(name, username, password);
                admin.setId(id);
                admin.setAdminID(adminID);
                admins.add(admin);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return admins;
    }

    public void addAdmin(Admin admin) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            Integer userID = userC.addUser(admin);
            String query = "INSERT INTO admin (userID) VALUES (?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, userID);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void deleteAdmin(Admin admin) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "DELETE FROM admin WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, admin.getAdminID());
            statement.executeUpdate();
            statement.close();
            userC.deleteUser(admin);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateAdmin(Admin admin) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "UPDATE admin SET userID = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, admin.getId());
            statement.setInt(2, admin.getAdminID());
            statement.executeUpdate();
            statement.close();
            userC.updateUser(admin);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
