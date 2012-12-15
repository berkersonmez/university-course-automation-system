/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berker
 */
public class CourseCollectionJDBC extends DBConnection {

    public CourseCollectionJDBC() {
        super();
    }

    public List<Course> getCourses() {
        List<Course> courses = new LinkedList<Course>();
        try {
            String query = "SELECT * FROM course";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("id");
                String name = results.getString("name");
                String code = results.getString("code");
                Integer credits = results.getInt("credits");
                Integer facultyID = results.getInt("facultyID");
                Integer length = results.getInt("length");
                Course course = new Course(name, code, credits, facultyID, length);
                course.setId(id);
                courses.add(course);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return courses;
    }
    
    public Course getCourse(Course course) {
        
        try {
            String query = "SELECT * FROM course WHERE id = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, course.getId());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Integer id = results.getInt("id");
                String name = results.getString("name");
                String code = results.getString("code");
                Integer credits = results.getInt("credits");
                Integer facultyID = results.getInt("facultyID");
                Integer length = results.getInt("length");
                course = new Course(name, code, credits, facultyID, length);
                course.setId(id);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return course;
    }
    
    public boolean checkCode(Course course) {
        
        try {
            String query = "SELECT COUNT(*) FROM course WHERE code = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setString(1, course.getCode());
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Integer count = results.getInt("COUNT(*)");
                if (count > 0) {
                    return true;
                }
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return false;
    }
    
    public void addCourse(Course course) {
        try {
            String query = "INSERT INTO course (name, code, credits, facultyID, length) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getCode());
            statement.setInt(3, course.getCredits());
            statement.setInt(4, course.getFacultyID());
            statement.setInt(5, course.getLength());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void updateCourse(Course course) {
        try {      
            String query = "UPDATE course SET name = ?, code = ?, credits = ?, facultyID = ?, length = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setString(1, course.getName());
            statement.setString(2, course.getCode());
            statement.setInt(3, course.getCredits());
            statement.setInt(4, course.getFacultyID());
            statement.setInt(5, course.getLength());
            statement.setInt(6, course.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

}
