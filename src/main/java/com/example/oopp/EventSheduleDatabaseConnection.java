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

                        Club club = new Club(clubName);

                        Club club = new Club(clubId,clubName,clubDescription);

                        clubs.add(club);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }
    public int getClubIdByClubName(String clubName) {
        int clubId = -1;
        try {
            String query = "SELECT clubId FROM club WHERE TRIM(clubName) = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, clubName.trim());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        clubId = resultSet.getInt("clubId");
                        System.out.println("Found clubId: " + clubId);
                    } else {
                        System.out.println("No matching club found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubId;
    }


    public void insertEvents(List<Event> events) {
        String query = "INSERT INTO clubEvents (eventId, clubId, teacherId, eventName, eventDate, eventTime, eventLocation, eventDescription, eventType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            for (Event event : events) {
                preparedStatement.setInt(1, event.getEventId());
                preparedStatement.setInt(2, event.getClubId());
                preparedStatement.setString(3, event.getAdvisorId());
                preparedStatement.setString(4, event.getTitle());
                preparedStatement.setString(5, event.getDate());
                preparedStatement.setString(6, event.getTime());
                preparedStatement.setString(7, event.getLocation());
                preparedStatement.setString(8, event.getDescription());
                preparedStatement.setString(9, event.getEventType());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully for event: " + event.getTitle());
                } else {
                    System.out.println("Failed to insert data for event: " + event.getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Do not close the connection here if you want to keep it open for subsequent operations
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }


    public void insertMeetings(List<Meeting> meetings) {
        String query = "INSERT INTO clubMeetings (eventId, clubId, teacherId, meetingName, meetingDate, meetingTime, meetingLocation, meetingDescription, meetingAgenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            for (Meeting meeting : meetings) {
                preparedStatement.setInt(1, meeting.getEventId());
                preparedStatement.setInt(2, meeting.getClubId());
                preparedStatement.setString(3, meeting.getAdvisorId());
                preparedStatement.setString(4, meeting.getTitle());
                preparedStatement.setString(5, meeting.getDate());
                preparedStatement.setString(6, meeting.getTime());
                preparedStatement.setString(7, meeting.getLocation());
                preparedStatement.setString(8, meeting.getDescription());
                preparedStatement.setString(9, meeting.getAgenda());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully for meeting: " + meeting.getTitle());
                } else {
                    System.out.println("Failed to insert data for meeting: " + meeting.getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Do not close the connection here if you want to keep it open for subsequent operations
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    public void insertActivities(List<Activity> activities) {
        String query = "INSERT INTO clubActivities (eventId, clubId, teacherId, activityName, activityDate, activityTime, activityLocation, activityDescription, activityType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            for (Activity activity : activities) {
                preparedStatement.setInt(1, activity.getEventId());
                preparedStatement.setInt(2, activity.getClubId());
                preparedStatement.setString(3, activity.getAdvisorId());
                preparedStatement.setString(4, activity.getTitle());
                preparedStatement.setString(5, activity.getDate());
                preparedStatement.setString(6, activity.getTime());
                preparedStatement.setString(7, activity.getLocation());
                preparedStatement.setString(8, activity.getDescription());
                preparedStatement.setString(9, activity.getActivityType());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully for activity: " + activity.getTitle());
                } else {
                    System.out.println("Failed to insert data for activity: " + activity.getTitle());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                // Do not close the connection here if you want to keep it open for subsequent operations
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM clubEvents";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getInt("eventId"),
                        resultSet.getString("eventName"),
                        resultSet.getString("eventDate"),
                        resultSet.getString("eventTime"),
                        resultSet.getString("eventLocation"),
                        resultSet.getString("eventDescription"),
                        resultSet.getInt("clubId"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("eventType")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return events;
    }

    public List<Meeting> getAllMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        String query = "SELECT * FROM clubMeetings";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Meeting meeting = new Meeting(
                        resultSet.getInt("eventId"),
                        resultSet.getString("meetingName"),
                        resultSet.getString("meetingDate"),
                        resultSet.getString("meetingTime"),
                        resultSet.getString("meetingLocation"),
                        resultSet.getString("meetingDescription"),
                        resultSet.getInt("clubId"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("meetingAgenda")
                );
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return meetings;
    }

    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        String query = "SELECT * FROM clubActivities";
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Activity activity = new Activity(
                        resultSet.getInt("eventId"),
                        resultSet.getString("activityName"),
                        resultSet.getString("activityDate"),
                        resultSet.getString("activityTime"),
                        resultSet.getString("activityLocation"),
                        resultSet.getString("activityDescription"),
                        resultSet.getInt("clubId"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("activityType")
                );
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return activities;
    }
    public String getClubNameById(int clubId) {
        String clubName = null;
        try {
            String query = "SELECT clubName FROM club WHERE clubId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, clubId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        clubName = resultSet.getString("clubName");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubName;
    }
    public List<Event> getEventsByAdvisorId(String advisorId) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM clubEvents WHERE teacherId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, advisorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getInt("eventId"),
                        resultSet.getString("eventName"),
                        resultSet.getString("eventDate"),
                        resultSet.getString("eventTime"),
                        resultSet.getString("eventLocation"),
                        resultSet.getString("eventDescription"),
                        resultSet.getInt("clubId"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("eventType")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close the ResultSet and PreparedStatement, but keep the Connection open
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return events;
    }

    public List<Meeting> getMeetingsByAdvisorId(String advisorId) {
        List<Meeting> meetings = new ArrayList<>();
        String query = "SELECT * FROM clubMeetings WHERE teacherId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, advisorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Meeting meeting = new Meeting(
                        resultSet.getInt("eventId"),
                        resultSet.getString("meetingName"),
                        resultSet.getString("meetingDate"),
                        resultSet.getString("meetingTime"),
                        resultSet.getString("meetingLocation"),
                        resultSet.getString("meetingDescription"),
                        resultSet.getInt("clubId"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("meetingAgenda")
                );
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close the ResultSet and PreparedStatement, but keep the Connection open
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return meetings;
    }

    public List<Activity> getActivitiesByAdvisorId(String advisorId) {
        List<Activity> activities = new ArrayList<>();
        String query = "SELECT * FROM clubActivities WHERE teacherId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, advisorId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Activity activity = new Activity(
                        resultSet.getInt("eventId"),
                        resultSet.getString("activityName"),
                        resultSet.getString("activityDate"),
                        resultSet.getString("activityTime"),
                        resultSet.getString("activityLocation"),
                        resultSet.getString("activityDescription"),
                        resultSet.getInt("clubId"),
                        resultSet.getString("teacherId"),
                        resultSet.getString("activityType")
                );
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close the ResultSet and PreparedStatement, but keep the Connection open
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        return activities;
    }

    public void deleteEvent(Event event) {
        String query = "DELETE FROM clubEvents WHERE eventId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, event.getEventId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close only the PreparedStatement, not the Connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        // Do not close the connection here
    }

    public void deleteMeeting(Meeting meeting) {
        String query = "DELETE FROM clubMeetings WHERE eventId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, meeting.getEventId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close only the PreparedStatement, not the Connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        // Do not close the connection here
    }

    public void deleteActivity(Activity activity) {
        String query = "DELETE FROM clubActivities WHERE eventId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, activity.getEventId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Close only the PreparedStatement, not the Connection
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
        // Do not close the connection here
    }

    public void updateEventByEventId(int eventId, String newName, String newDate, String newTime, String newLocation, String newDescription, String newType) {
        String query = "UPDATE clubEvents SET eventName = ?, eventDate = ?, eventTime = ?, eventLocation = ?, eventDescription = ?, eventType = ? WHERE eventId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newDate);
            preparedStatement.setString(3, newTime);
            preparedStatement.setString(4, newLocation);
            preparedStatement.setString(5, newDescription);
            preparedStatement.setString(6, newType);
            preparedStatement.setInt(7, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Do not close the connection here
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    // Update Meeting by Event ID
    public void updateMeetingByEventId(int eventId, String newName, String newDate, String newTime, String newLocation, String newDescription, String newAgenda) {
        String query = "UPDATE clubMeetings SET meetingName = ?, meetingDate = ?, meetingTime = ?, meetingLocation = ?, meetingDescription = ?, meetingAgenda = ? WHERE eventId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newDate);
            preparedStatement.setString(3, newTime);
            preparedStatement.setString(4, newLocation);
            preparedStatement.setString(5, newDescription);
            preparedStatement.setString(6, newAgenda);
            preparedStatement.setInt(7, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Do not close the connection here
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    // Update Activity by Event ID
    public void updateActivityByEventId(int eventId, String newName, String newDate, String newTime, String newLocation, String newDescription, String newType) {
        String query = "UPDATE clubActivities SET activityName = ?, activityDate = ?, activityTime = ?, activityLocation = ?, activityDescription = ?, activityType = ? WHERE eventId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, newDate);
            preparedStatement.setString(3, newTime);
            preparedStatement.setString(4, newLocation);
            preparedStatement.setString(5, newDescription);
            preparedStatement.setString(6, newType);
            preparedStatement.setInt(7, eventId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        } finally {
            // Do not close the connection here
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }
    public List<Club> getAllClubs() {
        List<Club> clubs = new ArrayList<>();

        try {
            // Assuming you have a "Club" table with columns "clubName" and "clubDescription"
            String query = "SELECT clubId, clubName, clubDescription FROM Club";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String clubId = resultSet.getString("clubId");
                    String clubName = resultSet.getString("clubName");
                    String clubDescription = resultSet.getString("clubDescription");

                    // Create a Club object and add it to the list
                    Club club = new Club(clubId , clubName, clubDescription);
                    clubs.add(club);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }

        return clubs;
    }

    public void insertMembershipRequest(String studentId, int clubId) {
        String insertQuery = "INSERT INTO membershiprequests (clubId, studentId) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Set parameters
            preparedStatement.setInt(1, clubId);
            preparedStatement.setString(2, studentId);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("Membership request inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately based on your application's needs
        }
    }














}
