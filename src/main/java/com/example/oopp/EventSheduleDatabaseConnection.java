package com.example.oopp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventSheduleDatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sacms";
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


                        new Club(clubName);

                        Club club = new Club(clubId,clubName,clubDescription);


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

    public int getClubIdByClubName(String clubName) {
        int clubId = -1;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT clubId FROM club WHERE TRIM(clubName) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, clubName.trim());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                clubId = resultSet.getInt("clubId");
                System.out.println("Found clubId: " + clubId);
            } else {
                System.out.println("No matching club found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close ResultSet and PreparedStatement in the reverse order of creation
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    public List<Club> getAvailableClubsForStudent(String studentId) {
        List<Club> clubs = new ArrayList<>();

        try {
            // Assuming you have a "Club" table with columns "clubId", "clubName", and "clubDescription"
            // and a "Student_Club" table with columns "studentId" and "clubId"
            String query = "SELECT c.clubId, c.clubName, c.clubDescription " +
                    "FROM Club c " +
                    "WHERE NOT EXISTS (SELECT 1 FROM Student_Club sc " +
                    "WHERE sc.studentId = ? AND sc.clubId = c.clubId)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String clubId = resultSet.getString("clubId");
                        String clubName = resultSet.getString("clubName");
                        String clubDescription = resultSet.getString("clubDescription");

                        // Create a Club object and add it to the list
                        Club club = new Club(clubId, clubName, clubDescription);
                        clubs.add(club);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        }

        return clubs;
    }


    public void insertMembershipRequest(String studentId, int clubId) {
        String insertQuery = "INSERT INTO membershiprequests (clubId, studentId) VALUES (?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(insertQuery);

            // Set parameters
            preparedStatement.setInt(1, clubId);
            preparedStatement.setString(2, studentId);

            // Execute the update
            preparedStatement.executeUpdate();

            System.out.println("Membership request inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    public List<MembershipRequest> getMembershipRequestsByAdvisorId(String advisorId) {
        List<MembershipRequest> membershipRequests = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();


            String query = "SELECT c.clubName, m.studentId " +
                    "FROM membershiprequests m " +
                    "JOIN Club c ON m.clubId = c.clubId " +
                    "WHERE c.teacherId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, advisorId);
            resultSet = preparedStatement.executeQuery();

            // Process the results
            while (resultSet.next()) {
                String clubName = resultSet.getString("clubName");
                String studentId = resultSet.getString("studentId");

                // Create a MembershipRequest object and add it to the list
                MembershipRequest membershipRequest = new MembershipRequest(clubName, studentId);
                membershipRequests.add(membershipRequest);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return membershipRequests;
    }

    public void acceptMembershipRequest(String studentId, int clubId) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        PreparedStatement insertStatement = null;

        try {
            connection = getConnection();


            String deleteQuery = "DELETE FROM membershiprequests WHERE studentId = ? AND clubId = ?";
            deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, studentId);
            deleteStatement.setInt(2, clubId);
            deleteStatement.executeUpdate();


            String insertQuery = "INSERT INTO student_club (studentId, clubId) VALUES (?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, studentId);
            insertStatement.setInt(2, clubId);
            insertStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // Close resources
            try {
                if (deleteStatement != null) {
                    deleteStatement.close();
                }
                if (insertStatement != null) {
                    insertStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void declineMembershipRequest(String studentId, String clubName) {
        try {
            int clubId = getClubIdByClubName(clubName);

            if (clubId != -1) {
                String deleteQuery = "DELETE FROM membershiprequests WHERE studentId = ? AND clubId = ?";
                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                    deleteStatement.setString(1, studentId);
                    deleteStatement.setInt(2, clubId);

                    int rowsAffected = deleteStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Membership request declined and deleted.");
                    } else {
                        System.out.println("No matching membership request found.");
                    }
                }
            } else {
                System.out.println("No matching club found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Club> getJoinedClubsByStudentId(String studentId) {
        List<Club> joinedClubs = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            // Query to retrieve joined clubs based on student ID
            String query = "SELECT c.clubName, c.clubDescription " +
                    "FROM Club c " +
                    "JOIN Student_club sc ON c.clubId = sc.clubId " +
                    "WHERE sc.studentId = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentId);
            resultSet = preparedStatement.executeQuery();

            // Process the results
            while (resultSet.next()) {
                String clubName = resultSet.getString("clubName");

                String clubDescription = resultSet.getString("clubDescription");

                // Create a Club object and add it to the list
                Club joinedClub = new Club(clubName, clubDescription);
                joinedClubs.add(joinedClub);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or handle it based on your application's needs
        } finally {
            // Close resources in the reverse order of their creation to avoid potential issues
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Log the exception or handle it based on your needs
            }
        }

        return joinedClubs;
    }

    public void leaveClub(String studentId, int clubId) {
        String deleteQuery = "DELETE FROM student_club WHERE studentId = ? AND clubId = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(deleteQuery);

            // Set parameters
            preparedStatement.setString(1, studentId);
            preparedStatement.setInt(2, clubId);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Membership record deleted successfully.");
            } else {
                System.out.println("No matching membership record found.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately based on your application's needs
        } finally {
            // Close PreparedStatement (Connection will remain open)
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
            // Note: Do not close the Connection here
        }
    }

    public List<Event> getClubEventsByStudentId(String studentId) {
        List<Event> clubEvents = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM ClubEvents WHERE clubId IN (SELECT clubId FROM student_club WHERE studentId = ?)";
            preparedStatement = connection.prepareStatement(query);

            // Set parameter
            preparedStatement.setString(1, studentId);
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
                clubEvents.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception based on your application's needs
            }
        }

        return clubEvents;
    }

    public List<Meeting> getClubMeetingsByStudentId(String studentId) {
        List<Meeting> clubMeetings = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM ClubMeetings WHERE clubId IN (SELECT clubId FROM student_club WHERE studentId = ?)";
            preparedStatement = connection.prepareStatement(query);

            // Set parameter
            preparedStatement.setString(1, studentId);
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
                clubMeetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception based on your application's needs
            }
        }

        return clubMeetings;
    }

    public List<Activity> getClubActivitiesByStudentId(String studentId) {
        List<Activity> clubActivities = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM ClubActivities WHERE clubId IN (SELECT clubId FROM student_club WHERE studentId = ?)";
            preparedStatement = connection.prepareStatement(query);

            // Set parameter
            preparedStatement.setString(1, studentId);
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
                clubActivities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception based on your application's needs
            }
        }

        return clubActivities;
    }

}
