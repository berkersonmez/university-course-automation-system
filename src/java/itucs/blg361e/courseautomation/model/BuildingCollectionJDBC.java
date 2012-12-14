/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Oguzzo
 */
public class BuildingCollectionJDBC extends DBConnection {

    public BuildingCollectionJDBC() {
        super();
    }
    
    public void addBuilding(Building iBuilding) {
        try {
            Building bil = new Building();
            String query = "INSERT INTO building (id, code, name, location) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iBuilding.getId());
            statement.setInt(2, iBuilding.getCode());
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
            String query = "UPDATE building SET ID = ?, CRN = ?, name = ?, location = ? WHERE (userID = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iBuilding.getId());
            statement.setInt(2, iBuilding.getCode());
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
