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
            String query = "SELECT id, number, name FROM student";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                Integer id = results.getInt("id");
                Integer number = results.getInt("number");
                String name = results.getString("name");
                Student student = new Student(number, name);
                student.setId(id);
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
            String query = "INSERT INTO student (number, name) VALUES (?, ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, student.getNumber());
            statement.setString(2, student.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void deleteStudent(Student student) {
        try {
            String query = "DELETE FROM student WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, student.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void updateStudent(Student student) {
        try {
            String query = "UPDATE student SET number = ?, name = ? WHERE (id = ?)";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setInt(1, student.getNumber());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
