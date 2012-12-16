/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berker
 */
public class UserCollectionJDBC extends DBConnection {

    public UserCollectionJDBC() {
        super();
    }

    public List<User> getUsers() {
        List<User> users = new LinkedList<User>();
        try {
            String query = "SELECT id, name, username, password, email, phone FROM user";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                String email = results.getString("email");
                Long phone = results.getLong("phone");
                User user = new User(name, username, password);
                user.setId(id);
                user.setEmail(email);
                user.setPhone(phone);
                users.add(user);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return users;
    }

    public Integer addUser(User user) {
        try {
            String query = "INSERT INTO user (name, username, password) VALUES (?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.preparePassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserCollectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserCollectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return null;
    }

    public void deleteUser(User user) {
        try {
            String query = "DELETE FROM user WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            String query = "UPDATE user SET name = ?, username = ?, password = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.preparePassword(user.getPassword()));
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserCollectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserCollectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void updateUserPreparedPassword(User user) {
        try {
            String query = "UPDATE user SET name = ?, username = ?, password = ?, email = ?, phone = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getPhone());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    //Parametre olarak user alıp o user database'de var mı diye bakar varsa onun değerlerini döndürür.
    public User checkUserByUsernameAndPassword(User user){
        try {
            String query = "SELECT * FROM user WHERE (username = ? AND password = ?) ";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.preparePassword(user.getPassword()));
            ResultSet result = statement.executeQuery();
            if(result.next()){       
                user.setName(result.getString("name"));
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setPhone(result.getLong("phone"));
            }
            
            statement.close();
            result.close();
            
            return user;
                   
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserCollectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserCollectionJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return user;
    }
    
    

}