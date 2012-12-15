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
public class ClassRoomCollectionJDBC extends DBConnection {

    public ClassRoomCollectionJDBC() {
        super();
    }
    
    public void addClassRoom(ClassRoom iClassRoom) {
        try {
            String query = "INSERT INTO class_room (id, code, quota, name, buildingID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iClassRoom.getId());
            statement.setInt(2, iClassRoom.getQuota());
            statement.setString(3, iClassRoom.getName());
            statement.setInt(4, iClassRoom.getBuildingID());
            statement.setBoolean(5, iClassRoom.isLab());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void deleteClassRoom(ClassRoom iClassRoom) {
        try {
            String query = "DELETE FROM class_room WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iClassRoom.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateClassRoom(ClassRoom iClassRoom) {
        try {      
            String query = "UPDATE class_room SET id = ?, quota = ?, name = ?, buildingID = ?, lab = ? WHERE (ID = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iClassRoom.getId());
            statement.setInt(2, iClassRoom.getQuota());
            statement.setString(3, iClassRoom.getName());
            statement.setInt(4, iClassRoom.getBuildingID());
            statement.setBoolean(5, iClassRoom.isLab());
            statement.setInt(6, iClassRoom.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
}
