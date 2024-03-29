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
            String query = "SELECT user.id, admin.id, name, username, password, email, phone FROM user JOIN admin ON (userID = user.id)";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("user.id");
                Integer adminID = results.getInt("admin.id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                String email = results.getString("email");
                Long phone = results.getLong("phone");
                
                Admin admin = new Admin(name, username, password);
                admin.setId(id);
                admin.setAdminID(adminID);
                admin.setEmail(email);
                admin.setPhone(phone);
                admins.add(admin);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return admins;
    }
    
    public Admin getAdmin(Admin admin) {
        try {
            String query = "SELECT user.id, admin.id, name, username, password FROM user JOIN admin ON (userID = user.id) WHERE user.id = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, admin.getId());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Integer adminID = results.getInt("admin.id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                admin.setAdminID(adminID);
                admin.setName(name);
                admin.setUsername(username);
                admin.setPassword(password);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return admin;
    }

    public void addAdmin(Admin admin) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            Integer userID = userC.addUser(admin);
            String query = "INSERT INTO admin (userID) VALUES (?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, userID);
            statement.executeUpdate();
            userC.close();
            
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
            userC.close();
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
            userC.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void updateAdminPreparedPassword(Admin admin) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "UPDATE admin SET userID = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, admin.getId());
            statement.setInt(2, admin.getAdminID());
            statement.executeUpdate();
            statement.close();
            userC.updateUserPreparedPassword(admin);
            userC.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public boolean isAdmin(User user){
        try {
            String query = "SELECT * FROM admin WHERE (userID = ?) ";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet result = statement.executeQuery();
            boolean flag;         
            if(result.next()){       
                flag = true;
            }
            else
                flag = false;        
            statement.close();
            result.close();  
            return flag;          
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }
}
