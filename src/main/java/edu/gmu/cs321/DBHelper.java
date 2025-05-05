package edu.gmu.cs321;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBHelper {
    public static Connection getConnection() throws Exception {
        Properties props = new Properties();
        try (InputStream input = DBHelper.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new Exception("Cannot find database.properties file.");
            }
            props.load(input);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
    
    public static List<Immigrant> getAllImmigrants() {
        List<Immigrant> list = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/workflow_db";
        String user = "workflow_user";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM immigrants");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
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

                list.add(imm);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}

