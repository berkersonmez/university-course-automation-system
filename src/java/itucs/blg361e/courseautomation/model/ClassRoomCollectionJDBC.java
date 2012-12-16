/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oguzzo
 */
public class ClassRoomCollectionJDBC extends DBConnection {

    public ClassRoomCollectionJDBC() {
        super();
    }
    
    public List<ClassRoom> getClassRooms(){
        List<ClassRoom> classRooms = new LinkedList<ClassRoom>();
        try {
            String query = "SELECT * FROM class_room";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("id");
                Integer quota = results.getInt("quota");
                String name = results.getString("name");
                Integer buildingID = results.getInt("buildingID");
                Boolean lab = results.getBoolean("lab");
                
                ClassRoom nClassRoom = new ClassRoom(id,quota,name,buildingID,lab);
                classRooms.add(nClassRoom);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return classRooms;
    }
    
    public ClassRoom getClassRoom(ClassRoom classRoom){
        try {
            String query = "SELECT * FROM class_room WHERE id = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, classRoom.getId());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Integer quota = results.getInt("quota");
                String name = results.getString("name");
                Integer buildingID = results.getInt("buildingID");
                Boolean lab = results.getBoolean("lab");
                classRoom.setQuota(quota);
                classRoom.setName(name);
                classRoom.setLab(lab);
                classRoom.setBuildingID(buildingID);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return classRoom;
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
    
    public ClassRoom findEmptyClassroomOnSameFaculty(OpenCourse course) {
        ClassRoom classroom = null;
        try {
            String query = "SELECT class_room.id, class_room.name FROM class_room JOIN building ON (class_room.buildingID = building.id) " +
                            "JOIN faculty ON (faculty.buildingID = building.id) WHERE faculty.id = ? AND " +
                            "class_room.quota >= ? AND " +
                            "class_room.id NOT IN (SELECT classID from open_course " +
                            "WHERE (((begin_time <= ? AND end_time > ?) " +
                            "OR (begin_time < ? AND end_time >= ?)) AND day = ?)) "+ 
                            "ORDER BY quota DESC";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, course.getFacultyID());
            statement.setInt(2, course.getQuota());
            statement.setTime(3, course.getBeginTime());
            statement.setTime(4, course.getBeginTime());
            statement.setTime(5, course.getEndTime());
            statement.setTime(6, course.getEndTime());
            statement.setString(7, course.getDay());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                classroom = new ClassRoom();
                classroom.setId(results.getInt("class_room.id"));
                classroom.setName(results.getString("class_room.name"));
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return classroom;
    }
    
    public ClassRoom findEmptyClassroomOnDifferentFaculty(OpenCourse course) {
        ClassRoom classroom = null;
        try {
            String query = "SELECT class_room.id, class_room.name FROM class_room JOIN building ON (class_room.buildingID = building.id) " +
                            "JOIN faculty ON (faculty.buildingID = building.id) WHERE faculty.id <> ? AND " +
                            "class_room.quota >= ? AND " +
                            "class_room.id NOT IN (SELECT classID from open_course " +
                            "WHERE (((begin_time <= ? AND end_time > ?) " +
                            "OR (begin_time < ? AND end_time >= ?)) AND day = ?)) "+ 
                            "ORDER BY quota DESC";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, course.getFacultyID());
            statement.setInt(2, course.getQuota());
            statement.setTime(3, course.getBeginTime());
            statement.setTime(4, course.getBeginTime());
            statement.setTime(5, course.getEndTime());
            statement.setTime(6, course.getEndTime());
            statement.setString(7, course.getDay());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                classroom = new ClassRoom();
                classroom.setId(results.getInt("class_room.id"));
                classroom.setName(results.getString("class_room.name"));
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return classroom;
    }
    
    public boolean isClassRoomAvailable(OpenCourse course) {
        try {
            String query = "SELECT CRN FROM open_course WHERE classID = ? AND"
                    + "(((begin_time <= ? AND end_time > ?) " +
                    "OR (begin_time < ? AND end_time >= ?)) AND day = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, course.getClassID());
            statement.setTime(2, course.getBeginTime());
            statement.setTime(3, course.getBeginTime());
            statement.setTime(4, course.getEndTime());
            statement.setTime(5, course.getEndTime());
            statement.setString(6, course.getDay());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                results.close();
                statement.close();
                return false;
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return true;
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
