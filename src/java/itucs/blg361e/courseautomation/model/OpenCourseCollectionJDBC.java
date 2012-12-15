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
import java.sql.Time;
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

    public List<OpenCourse> getAllOpenCourses(OpenCourse iOpenCourse) {       
        try {
            List<OpenCourse> openCourseList = new LinkedList<OpenCourse>();
            String query = "SELECT * FROM open_course WHERE (CRN = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer classID = results.getInt("classID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  classID,  beginTime,  endTime);
                openCourseList.add(nOpenCourse);
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
            Time beginTime, endTime;
            if (results.next()) {
                CRN = results.getInt("CRN");
                courseID = results.getInt("courseID");
                quota = results.getInt("quota");
                currentStudentCount = results.getInt("current_student_count");
                teacherID = results.getInt("teacherID");
                classID = results.getInt("classID");
                beginTime = results.getTime("begin_time");
                endTime = results.getTime("end_time");
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

    public List<OpenCourse> getOpenCoursesJoinCourses() {
        try {
            List<OpenCourse> openCourseList = new LinkedList<OpenCourse>();
            String query = "SELECT * FROM open_course JOIN course ON (courseID = id)" ;
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer classID = results.getInt("classID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String name = results.getString("name");
                String code = results.getString("code");
                Integer credits = results.getInt("credits");
                Integer facultyID = results.getInt("facultyID");
                Integer length = results.getInt("facultyID");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  classID,  beginTime,  endTime);
                openCourseList.add(nOpenCourse);
            }
            results.close();
            statement.close();
            return openCourseList;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
     
    public OpenCourse getOpenCourse(OpenCourse iOpenCourse) {
        try {
            String query = "SELECT * FROM open_course WHERE (CRN = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            ResultSet results = statement.executeQuery();
            
            if (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer classID = results.getInt("classID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  classID,  beginTime,  endTime);
                iOpenCourse = nOpenCourse;
            }
            results.close();
            statement.close();
            return iOpenCourse;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void addOpenCourse(OpenCourse iOpenCourse) {
        try {
            String query = "INSERT INTO open_course VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            statement.setInt(2, iOpenCourse.getCourseID());
            statement.setInt(2, iOpenCourse.getQuota());
            statement.setInt(2, iOpenCourse.getCurrentStudentCount());
            statement.setInt(2, iOpenCourse.getTeacherID());
            statement.setInt(2, iOpenCourse.getClassID());
            statement.setTime(2, iOpenCourse.getBeginTime());
            statement.setTime(2, iOpenCourse.getEndTime());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void deleteOpenCourse(OpenCourse iOpenCourse) {
        try {
            String query = "DELETE FROM open_course WHERE (CRN = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateStudent(OpenCourse iOpenCourse) {
        try {      
            String query = "UPDATE open_course SET CRN = ?, quota = ?, current_student_count = ?, teacherID = ?, ClassID = ?, begin_time = ?, end_time = ? WHERE (userID = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            statement.setInt(2, iOpenCourse.getCourseID());
            statement.setInt(2, iOpenCourse.getQuota());
            statement.setInt(2, iOpenCourse.getCurrentStudentCount());
            statement.setInt(2, iOpenCourse.getTeacherID());
            statement.setInt(2, iOpenCourse.getClassID());
            statement.setTime(2, iOpenCourse.getBeginTime());
            statement.setTime(2, iOpenCourse.getEndTime());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
