/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation.model;

import itucs.blg361e.courseautomation.DBConnection;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Berker
 */
public class TeacherCollectionJDBC extends DBConnection {

    public TeacherCollectionJDBC() {
        super();
    }

    public List<Teacher> getTeachers() {
        List<Teacher> teachers = new LinkedList<Teacher>();
        try {
            String query = "SELECT user.id, teacher.id, name, username, password, email, phone FROM user JOIN teacher ON (userID = user.id)";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("user.id");
                Integer teacherID = results.getInt("teacher.id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                String email = results.getString("email");
                Long phone = results.getLong("phone");
                
                Teacher teacher = new Teacher(name, username, password);
                teacher.setId(id);
                teacher.setTeacherID(teacherID);
                teacher.setEmail(email);
                teacher.setPhone(phone);
                teachers.add(teacher);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return teachers;
    }

    public Teacher getTeacher(Integer userID){
        try {
            String query = "SELECT user.id, teacher.id, name, username, password FROM user JOIN teacher ON (user.id = teacher.userID) WHERE (user.id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Integer id = results.getInt("user.id");
                Integer teacherID = results.getInt("teacher.id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                
                Teacher teacher = new Teacher(name, username, password);
                teacher.setId(id);
                teacher.setTeacherID(teacherID);
                results.close();
                statement.close();
                return teacher;
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
    
    public Teacher getTeacherByTeacherId(Integer teacher_id){
        try {
            String query = "SELECT user.id, teacher.id, name, username, password FROM user JOIN teacher ON (user.id = teacher.userID) WHERE (teacher.id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, teacher_id);
            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Integer id = results.getInt("user.id");
                Integer teacherID = results.getInt("teacher.id");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                
                Teacher teacher = new Teacher(name, username, password);
                teacher.setId(id);
                teacher.setTeacherID(teacherID);
                results.close();
                statement.close();
                return teacher;
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
    
    public void addTeacher(Teacher teacher) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            Integer userID = userC.addUser(teacher);
            String query = "INSERT INTO teacher (userID) VALUES (?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, userID);
            statement.executeUpdate();
            userC.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void deleteTeacherByUserId(Teacher teacher) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "DELETE FROM teacher WHERE (userID = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, teacher.getId());
            statement.executeUpdate();
            statement.close();
            userC.deleteUser(teacher);
            userC.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public void deleteTeacherByTeacherId(Teacher teacher) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            teacher = this.getTeacherByTeacherId(teacher.getTeacherID());
            String query = "DELETE FROM teacher WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, teacher.getTeacherID());
            statement.executeUpdate();
            statement.close();
            userC.deleteUser(teacher);
            userC.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public boolean checkCode(Teacher teacher) {
        
        try {
            String query = "SELECT COUNT(*) FROM teacher WHERE id = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, teacher.getTeacherID());
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
    
    public void updateTeacher(Teacher teacher) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "UPDATE teacher SET userID = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, teacher.getId());
            statement.setInt(2, teacher.getTeacherID());
            statement.executeUpdate();
            statement.close();
            userC.updateUser(teacher);
            userC.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public boolean isTeacher(User user){
        try {
            String query = "SELECT * FROM teacher WHERE (userID = ?) ";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, user.getId());
            ResultSet result = statement.executeQuery();
            boolean flag;
            
            if(result.next()){       
                flag = true;
                
            }
            else
                flag = false;
            
            statement.close();
            result.close();  
            return flag; 
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        
    }
}
