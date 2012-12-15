/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Oguzzo
 */
public class BuildingCollectionJDBC extends DBConnection {

    public BuildingCollectionJDBC() {
        super();
    }
    
    public Building getBuilding(Integer buildingID) {
        
        try {
            String query = "SELECT * FROM building WHERE id = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, buildingID);
            ResultSet results = statement.executeQuery();
            Integer id;
            String code, name, location;
            if (results.next()) {
                id = results.getInt("id");
                code = results.getString("code");
                name = results.getString("name");
                location = results.getString("location");
                Building nBuilding = new Building(id, code, name, location);
                results.close();
                statement.close();
                return nBuilding;
            }
            else{
                 results.close();
                 statement.close();
                 return null;
            }
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }
    
    public void addBuilding(Building iBuilding) {
        try {
            Building bil = new Building();
            String query = "INSERT INTO building (id, code, name, location) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iBuilding.getId());
            statement.setString(2, iBuilding.getCode());
            statement.setString(3, iBuilding.getName());
            statement.setString(4, iBuilding.getLocation());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void deleteBuilding(Building iBuilding) {
        try {
            String query = "DELETE FROM building WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iBuilding.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateBuilding(Building iBuilding) {
        try {      
            String query = "UPDATE building SET id = ?, code = ?, name = ?, location = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iBuilding.getId());
            statement.setString(2, iBuilding.getCode());
            statement.setString(3, iBuilding.getName());
            statement.setString(4, iBuilding.getLocation());
            statement.setInt(1, iBuilding.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
}
