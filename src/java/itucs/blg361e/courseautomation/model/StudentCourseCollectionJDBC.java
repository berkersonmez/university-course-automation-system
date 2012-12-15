/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

/**
 *
 * @author Oguzzo
 */
import itucs.blg361e.courseautomation.DBConnection;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class StudentCourseCollectionJDBC extends DBConnection {

    public StudentCourseCollectionJDBC() {
        super();
    }

    public List<StudentCourse> getStudentCourses(StudentCourse sCourse) {       
        try {
            List<StudentCourse> sCourses = new LinkedList<StudentCourse>();
            String query = "SELECT userID, CRN FROM student_course WHERE (userID = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, sCourse.getUserID());
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Integer id = results.getInt("id");
                Integer studentID = results.getInt("userID");
                Integer CRN = results.getInt("CRN");
                
                StudentCourse studentCourse = new StudentCourse(id, studentID, CRN);
                sCourses.add(studentCourse);
            }
            results.close();
            statement.close();
            return sCourses;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }

    public void addStudentCourse(StudentCourse sCourse) {
        try {
            String query = "INSERT INTO student_course (userID, CRN) VALUES (?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, sCourse.getUserID());
            statement.setInt(2, sCourse.getCRN());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void deleteStudent(StudentCourse sCourse) {
        try {
            String query = "DELETE FROM student_course WHERE (userID = ?) AND (CRN = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, sCourse.getUserID());
            statement.setInt(2, sCourse.getCRN());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateStudent(StudentCourse sCourse) {
        try {      
            String query = "UPDATE student_course SET userID = ?, CRN = ? WHERE (userID = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, sCourse.getUserID());
            statement.setInt(2, sCourse.getCRN());
            statement.setInt(3, sCourse.getUserID());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
}
