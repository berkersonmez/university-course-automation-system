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
public class StudentCollectionJDBC extends DBConnection {

    public StudentCollectionJDBC() {
        super();
    }

    public List<Student> getStudents() {
        List<Student> students = new LinkedList<Student>();
        try {
            String query = "SELECT user.id, student.id, number, name, username, password, credit_limit, current_credit FROM user JOIN student ON (userID = user.id)";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("user.id");
                Integer studentID = results.getInt("student.id");
                Integer number = results.getInt("number");
                String name = results.getString("name");
                String username = results.getString("username");
                String password = results.getString("password");
                Integer credit_limit = results.getInt("credit_limit");
                Integer current_credit = results.getInt("current_credit");
                
                
                Student student = new Student(name, username, password, number);
                student.setId(id);
                student.setStudentID(studentID);
                students.add(student);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return students;
    }

    public void addStudent(Student student) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            Integer userID = userC.addUser(student);
            String query = "INSERT INTO student (number, userID) VALUES (?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, student.getNumber());
            statement.setInt(2, userID);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void deleteStudent(Student student) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "DELETE FROM student WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, student.getStudentID());
            statement.executeUpdate();
            statement.close();
            userC.deleteUser(student);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateStudent(Student student) {
        try {
            UserCollectionJDBC userC = new UserCollectionJDBC();
            
            String query = "UPDATE student SET number = ?, userID = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, student.getNumber());
            statement.setInt(2, student.getId());
            statement.setInt(3, student.getStudentID());
            statement.executeUpdate();
            statement.close();
            userC.updateUser(student);
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
    
    public boolean isStudent(User user){
        try {
            String query = "SELECT * FROM student WHERE (userID = ?) ";
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
