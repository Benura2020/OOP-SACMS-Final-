package com.example.oopp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventSheduleDatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ood";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection connection;

    public EventSheduleDatabaseConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection to the database failed!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isAdvisorIdExists(String advisorId) {
        try {
            String query = "SELECT COUNT(*) FROM clubadvisor WHERE teacherId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Club> getClubsByAdvisorId(String advisorId) {
        List<Club> clubs = new ArrayList<>();
        try {
            String query = "SELECT clubId, clubName, clubDescription FROM club WHERE teacherId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String clubId = resultSet.getString("clubId");
                        String clubName = resultSet.getString("clubName");
                        String clubDescription = resultSet.getString("clubDescription");
                        Club club = new Club(clubId, clubName, clubDescription);
                        clubs.add(club);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

}
