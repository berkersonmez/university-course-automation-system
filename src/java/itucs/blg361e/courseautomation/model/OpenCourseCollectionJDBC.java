/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oguzzo
 */
public class OpenCourseCollectionJDBC extends DBConnection {
    public OpenCourseCollectionJDBC() {
        super();
    }

    public List<OpenCourse> getOpenCourse(OpenCourse nOpenCourse) {       
        try {
            List<OpenCourse> openCourseList = new LinkedList<OpenCourse>();
            String query = "SELECT CRN, FROM open_course WHERE (CRN = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, nOpenCourse.getCRN());
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer classID = results.getInt("classID");
                String beginTime = results.getString("begin_time");
                String endTime = results.getString("end_time");
                
                OpenCourse newOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  classID,  beginTime,  endTime);
            }
            results.close();
            statement.close();
            return openCourseList;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }
    
    public OpenCourse getOpenCourseByCRN(Integer iCRN){
        try {
            String query = "SELECT * FROM open_course WHERE (CRN = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iCRN);
            ResultSet results = statement.executeQuery();
            Integer CRN, courseID, quota, currentStudentCount, teacherID, classID;
            String beginTime, endTime;
            if (results.next()) {
                CRN = results.getInt("CRN");
                courseID = results.getInt("courseID");
                quota = results.getInt("quota");
                currentStudentCount = results.getInt("current_student_count");
                teacherID = results.getInt("teacherID");
                classID = results.getInt("classID");
                beginTime = results.getString("begin_time");
                endTime = results.getString("end_time");
            }
            else{
               results.close();
               statement.close();
               return null;
            }
            OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  classID,  beginTime,  endTime); 
            results.close();
            statement.close();
            return nOpenCourse;
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
