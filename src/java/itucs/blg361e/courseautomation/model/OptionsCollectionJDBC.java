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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Oguzzo
 */
public class OptionsCollectionJDBC extends DBConnection {
    public OptionsCollectionJDBC() {
        super();
    }

    public Options getOptions() {       
        Options options = new Options();
        try {
            String query = "SELECT * FROM options";
            Statement statement = this.db.createStatement();
            ResultSet results = statement.executeQuery(query);
            if (results.next()) {
                Boolean isAddDrop = results.getBoolean("isAddDrop");
                
                
                options.setIsAddDrop(isAddDrop);
            }
            results.close();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
        return options;
    }

    public void updateOptions(Options options) {
        try {      
            String query = "UPDATE options SET isAddDrop = ?";
            PreparedStatement statement = this.db.prepareStatement(query);
            statement.setBoolean(1, options.isIsAddDrop());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
