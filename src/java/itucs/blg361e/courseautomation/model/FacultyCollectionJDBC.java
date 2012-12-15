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
public class FacultyCollectionJDBC extends DBConnection {

    public FacultyCollectionJDBC() {
        super();
    }

    public List<Faculty> getFaculties() {
        List<Faculty> faculties = new LinkedList<Faculty>();
        try {
            String query = "SELECT * FROM faculty";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("id");
                String name = results.getString("name");
                String code = results.getString("code");
                Integer building = results.getInt("building");
                Faculty faculty = new Faculty(id, building, name, code);
                //faculty.setId(id);
                faculties.add(faculty);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return faculties;
    }
    
//    public Course getCourse(Course course) {
//        
//        try {
//            String query = "SELECT * FROM course WHERE id = ?";
//            PreparedStatement statement = this.db.prepareStatement(query);
//            statement.setInt(1, course.getId());
//            ResultSet results = statement.executeQuery();
//            if (results.next()) {
//                Integer id = results.getInt("id");
//                String name = results.getString("name");
//                String code = results.getString("code");
//                Integer credits = results.getInt("credits");
//                Integer facultyID = results.getInt("facultyID");
//                Integer length = results.getInt("length");
//                course = new Course(name, code, credits, facultyID, length);
//                course.setId(id);
//            }
//            results.close();
//            statement.close();
//        } catch (SQLException e) {
//            throw new UnsupportedOperationException(e.getMessage());
//        }
//        return course;
//    }

}