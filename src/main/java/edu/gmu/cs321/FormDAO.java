package edu.gmu.cs321;

import java.sql.*;

import com.cs321.Workflow;

public class FormDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/workflow_db";
    private static final String DB_USER = "workflow_user";
    private static final String DB_PASSWORD = "password";

    public static int saveImmigrant(Immigrant i) {
        String query = "INSERT INTO immigrants (full_name, dob, ssn, country_of_origin, immigration_status, street, city, state, zip) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, i.getFullName());
            stmt.setString(2, i.getDateOfBirth());
            stmt.setString(3, i.getSsn());
            stmt.setString(4, i.getCountryOfOrigin());
            stmt.setString(5, i.getImmigrationStatus());
            stmt.setString(6, i.getAddress().getStreet());
            stmt.setString(7, i.getAddress().getCity());
            stmt.setString(8, i.getAddress().getState());
            stmt.setString(9, i.getAddress().getZip());

            int rows = stmt.executeUpdate();
            System.out.println("Inserted rows: " + rows);

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    i.setId(generatedId);  // ✅ set ID in Immigrant object
                    return generatedId;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error inserting immigrant:");
            e.printStackTrace();
        }

        return -1; // return -1 if something went wrong
    }
    public static Immigrant getImmigrantById(int id) {
        String query = "SELECT * FROM immigrants WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Address addr = new Address(
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zip")
                    );
    
                    Immigrant imm = new Immigrant(
                        rs.getString("full_name"),
                        rs.getString("dob"),
                        rs.getString("ssn"),
                        rs.getString("country_of_origin"),
                        rs.getString("immigration_status"),
                        addr
                    );
    
                    imm.setId(id);  // ✅ set ID
                    return imm;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }    

}


