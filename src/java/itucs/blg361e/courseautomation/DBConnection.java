/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itucs.blg361e.courseautomation;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Berker
 */
public class DBConnection {
    protected Connection db;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }

        try {
            String jdbcURL = "jdbc:mysql://localhost/course_automation?" +
                             "user=root&password=mbotm361";
            this.db = DriverManager.getConnection(jdbcURL);
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }
    
    public void close() {
        try {
            db.close();
        } catch (SQLException ex) {
            throw new UnsupportedOperationException(ex.getMessage());
        }
    }
}
