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
    
    public List<OpenCourse> getAllOpenCourses() {       
        try {
            List<OpenCourse> openCourseList = new LinkedList<OpenCourse>();
            String query = "SELECT * FROM open_course JOIN course ON (open_course.courseID = course.id) ORDER BY day, begin_time" ;
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer class_roomID = results.getInt("class_roomID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String day = results.getString("day");
                String name = results.getString("name");
                String code = results.getString("code");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  class_roomID,  beginTime,  endTime, day);
                nOpenCourse.setName(name);
                nOpenCourse.setCode(code);
                openCourseList.add(nOpenCourse);
            }
            results.close();
            statement.close();
            return openCourseList;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
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
                Integer class_roomID = results.getInt("class_roomID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String day = results.getString("day");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  class_roomID,  beginTime,  endTime, day);
                openCourseList.add(nOpenCourse);
            }
            results.close();
            statement.close();
            return openCourseList;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }
    
    public List<OpenCourse> getOpenCoursesByCourseID(Integer cID) {       
        try {
            List<OpenCourse> sCourses = new LinkedList<OpenCourse>();
            String query = "SELECT * FROM open_course WHERE (courseID = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, cID);
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer class_roomID = results.getInt("class_roomID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String day = results.getString("day");
                
                OpenCourse teacherCourse = new OpenCourse(CRN, courseID, quota, currentStudentCount, teacherID, class_roomID, beginTime, endTime, day);
                sCourses.add(teacherCourse);
            }
            results.close();
            statement.close();
            return sCourses;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }
    
    public List<OpenCourse> getOneTeacherCourses(Teacher teacher) {       
        try {
            List<OpenCourse> sCourses = new LinkedList<OpenCourse>();
            String query = "SELECT * FROM open_course JOIN course ON (open_course.courseID = course.id)"
                    + " WHERE (teacherID = ?) ORDER BY day,begin_time" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, teacher.getTeacherID());
            ResultSet results = statement.executeQuery();
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer class_roomID = results.getInt("class_roomID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String day = results.getString("day");
                String name = results.getString("course.name");
                String code = results.getString("course.code");
                
                OpenCourse teacherCourse = new OpenCourse(CRN, courseID, quota, currentStudentCount, teacherID, class_roomID, beginTime, endTime, day);
                teacherCourse.setName(name);
                teacherCourse.setCode(code);
                sCourses.add(teacherCourse);
            }
            results.close();
            statement.close();
            return sCourses;
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
            String day;
            Integer CRN, courseID, quota, currentStudentCount, teacherID, class_roomID;
            Time beginTime, endTime;
            if (results.next()) {
                CRN = results.getInt("CRN");
                courseID = results.getInt("courseID");
                quota = results.getInt("quota");
                currentStudentCount = results.getInt("current_student_count");
                teacherID = results.getInt("teacherID");
                class_roomID = results.getInt("class_roomID");
                beginTime = results.getTime("begin_time");
                endTime = results.getTime("end_time");
                day = results.getString("day");
                
            }
            else{
               results.close();
               statement.close();
               return null;
            }
            OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  class_roomID,  beginTime,  endTime, day); 
            results.close();
            statement.close();
            return nOpenCourse;
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public OpenCourse getOpenCourseJoinCourseByCRN(Integer iCRN){
        try {
            String query = "SELECT * FROM open_course JOIN course ON (open_course.courseID = course.id) "
                    + " WHERE (CRN = ?)" ;
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iCRN);
            ResultSet results = statement.executeQuery();
            String day, name, code;
            Integer CRN, courseID, quota, currentStudentCount, teacherID, class_roomID;
            Time beginTime, endTime;
            if (results.next()) {
                CRN = results.getInt("CRN");
                courseID = results.getInt("courseID");
                quota = results.getInt("quota");
                currentStudentCount = results.getInt("current_student_count");
                teacherID = results.getInt("teacherID");
                class_roomID = results.getInt("class_roomID");
                beginTime = results.getTime("begin_time");
                endTime = results.getTime("end_time");
                day = results.getString("day");
                name = results.getString("name");
                code = results.getString("code");
                
            }
            else{
               results.close();
               statement.close();
               return null;
            }
            OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  class_roomID,  beginTime,  endTime, day);
            nOpenCourse.setName(name);
            nOpenCourse.setCode(code);
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
            String query = "SELECT * FROM open_course JOIN course ON (courseID = id) WHERE open_course.class_roomID = 0 ORDER BY quota DESC" ;
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            
            while (results.next()) {
                Integer CRN = results.getInt("CRN");
                Integer courseID = results.getInt("courseID");
                Integer quota = results.getInt("quota");
                Integer currentStudentCount = results.getInt("current_student_count");
                Integer teacherID = results.getInt("teacherID");
                Integer classID = results.getInt("class_roomID");
                String day = results.getString("day");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String name = results.getString("name");
                String code = results.getString("code");
                Integer credits = results.getInt("credits");
                Integer facultyID = results.getInt("facultyID");
                Integer length = results.getInt("length");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  classID,  beginTime,  endTime, day);
                nOpenCourse.setName(name);
                nOpenCourse.setCode(code);
                nOpenCourse.setCredits(credits);
                nOpenCourse.setFacultyID(facultyID);
                nOpenCourse.setLength(length);
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
                Integer class_roomID = results.getInt("class_roomID");
                Time beginTime = results.getTime("begin_time");
                Time endTime = results.getTime("end_time");
                String day = results.getString("day");
                
                OpenCourse nOpenCourse = new OpenCourse(CRN,  courseID,  quota,  currentStudentCount,  teacherID,  class_roomID,  beginTime,  endTime, day);
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
        iOpenCourse.setCurrentStudentCount(0);
        try {
            String query = "INSERT INTO open_course VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            statement.setInt(2, iOpenCourse.getCourseID());
            statement.setInt(3, iOpenCourse.getQuota());
            statement.setInt(4, iOpenCourse.getCurrentStudentCount());
            statement.setInt(5, iOpenCourse.getTeacherID());
            statement.setInt(6, iOpenCourse.getClassID());
            statement.setString(7, iOpenCourse.getDay());
            statement.setTime(8, iOpenCourse.getBeginTime());
            statement.setTime(9, iOpenCourse.getEndTime());
            
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public boolean isTeacherAvailable(OpenCourse course) {
        try {
            String query = "SELECT CRN FROM open_course WHERE teacherID = ? AND"
                    + "(((begin_time <= ? AND end_time > ?) " +
                    "OR (begin_time < ? AND end_time >= ?)) AND day = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, course.getTeacherID());
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

    public boolean checkCode(OpenCourse iOpenCourse) {
        
        try {
            String query = "SELECT COUNT(*) FROM open_course WHERE CRN = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
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
    
    public boolean checkCode(Integer iCRN) {
        
        try {
            String query = "SELECT COUNT(*) FROM open_course WHERE CRN = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iCRN);
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
    
    public void deleteOpenCourseByCourseID(Integer courseID) {
        try {
            String query = "DELETE FROM open_course WHERE (courseID = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, courseID);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateOpenCourseClass(OpenCourse iOpenCourse) {
        try {      
            String query = "UPDATE open_course SET class_roomID = ? WHERE (CRN = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getClassID());
            statement.setInt(2, iOpenCourse.getCRN());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void updateOpenCourse(OpenCourse iOpenCourse){
        try {      
            String query = "UPDATE open_course SET CRN = ?, courseID = ?, quota = ?, current_student_count = ?, teacherID = ?, class_roomID = ?, begin_time = ?, end_time = ?, day = ? WHERE (CRN = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, iOpenCourse.getCRN());
            statement.setInt(2, iOpenCourse.getCourseID());
            statement.setInt(3, iOpenCourse.getQuota());
            statement.setInt(4, iOpenCourse.getCurrentStudentCount());
            statement.setInt(5, iOpenCourse.getTeacherID());
            statement.setInt(6, iOpenCourse.getClassID());
            statement.setTime(7, iOpenCourse.getBeginTime());
            statement.setTime(8, iOpenCourse.getEndTime());
            statement.setString(9, iOpenCourse.getDay());
            statement.setInt(10, iOpenCourse.getCRN());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
}
