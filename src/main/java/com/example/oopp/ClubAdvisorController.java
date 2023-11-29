package com.example.oopp;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class ClubAdvisorController implements Initializable {


    @FXML
    private TextField attendance_AdvisorID_search;
    @FXML
    private AnchorPane Attendance2pane;
    @FXML
    private TableColumn<Attendance, Boolean> attendance_Attendance_column;

    @FXML
    private ChoiceBox<String> attendance_Club_choice;
    @FXML
    private ChoiceBox<Integer> attendance_EventID_choice;

    @FXML
    private Button attendance_MarkAttendance_button;


    @FXML
    private TableColumn<Map.Entry<String, String>, String> attendance_StudentID_column;

    @FXML
    private TextField attendance_StudentID_search_bar;

    @FXML
    private TableColumn<Map.Entry<String, String>, String> attendance_StudentName_column;


    @FXML
    private TableView<Map.Entry<String, String>> attendance_table;
    @FXML
    private AnchorPane AttendencePane;

    @FXML
    private AnchorPane JoinRequestsPane;

    @FXML
    private Button ManageClubButton;

    @FXML
    private Button SheduleEventsButton;

    @FXML
    private Button clubAttendenceButton;

    @FXML
    private Button clubJoinRequestButton;

    @FXML
    private AnchorPane createClubAnchorPane;
    @FXML
    private Button EventDeleteUpdateButton;
    @FXML
    private AnchorPane eventUpdatePane;
    @FXML
    private AnchorPane eventSchedulingPane;
    @FXML
    private AnchorPane eventSchedulingSecondPane;
    @FXML
    private TextField eventSchedulingEnterField;
    @FXML
    private ChoiceBox<Club> eventSchedulingChoiceBox;
    @FXML
    private Button EnterAdvisorButton;
    @FXML
    private ChoiceBox<String> EventSchedulingTypeChoiceBox;
    @FXML
    private TextField EventSchedulingEventId;
    @FXML
    private TextField EventSchedulingEventName;
    @FXML
    private DatePicker EventSchedulingDatePicker;
    @FXML
    private TextField EventSchedulingTime;
    @FXML
    private TextField EventSchedulingLocation;
    @FXML
    private TextField EventSchedulingTypeOrAgenda;
    @FXML
    private TextField EventSchedulingDescription;
    @FXML
    private TextField EventDeleteAdvisorId;
    @FXML
    private TableView<ScheduleActivity> EventSchedulingTable;
    @FXML
    private TableColumn<ScheduleActivity, Integer> EventIdColumn;
    @FXML
    private TableColumn<ScheduleActivity, String> EventClubName;
    @FXML
    private TableColumn<ScheduleActivity, String> EventName;
    @FXML
    private TableColumn<ScheduleActivity, String> eventType;
    @FXML
    private TableColumn<ScheduleActivity, String> eventScheduleDate;
    @FXML
    private TableColumn<ScheduleActivity, String> eventScheduleTime;
    @FXML
    private TableColumn<ScheduleActivity, String> EventScheduleLocation;
    @FXML
    private TableColumn<ScheduleActivity, String> EventScheduleDescription;
    @FXML
    private AnchorPane EventDeleteSecondPane;
    @FXML
    private TableView<ScheduleActivity> EventUpdateDeleteTable;
    @FXML
    private TableColumn<ScheduleActivity, Integer> EventDeleteId;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteName;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteActivityType;
    @FXML
    private TableColumn<ScheduleActivity, Date> EventDeleteDate;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteTime;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteLocation;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteTypeAgenda;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteDescription;
    @FXML
    private TextField EventUpdateName;
    @FXML
    private TextField EventUpdateTime;
    @FXML
    private DatePicker EventUpdateDate;
    @FXML
    private TextField EventUpdateLocation;
    @FXML
    private TextField EventUpdateDescription;
    @FXML
    private TextField EventUpdateTypeOrAgenda;
    @FXML
    private TextField EventUpdateDeleteEventId;


    private static List<Event> eventList = new ArrayList<>();
    private static List<Meeting> meetingList = new ArrayList<>();
    private static List<Activity> activityList = new ArrayList<>();


    @FXML
    public void mainClick(ActionEvent event) {
        if (event.getSource() == ManageClubButton) {
            createClubAnchorPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }
        if (event.getSource() == SheduleEventsButton) {
            eventSchedulingPane.setVisible(true);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);

        }
        if (event.getSource() == clubJoinRequestButton) {
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }

        if (event.getSource() == clubAttendenceButton) {
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(true);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }
        if (event.getSource() == EventDeleteUpdateButton) {
            eventUpdatePane.setVisible(true);
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);

        }
    }


    EventSheduleDatabaseConnection dbConnection = new EventSheduleDatabaseConnection();

    @FXML
    public void enterAdvisorIdClick(ActionEvent event) {
        String advisorId = eventSchedulingEnterField.getText();

        // Check if advisor ID exists
        if (dbConnection.isAdvisorIdExists(advisorId)) {
            // If advisor ID exists, set the second pane visible
            eventSchedulingSecondPane.setVisible(true);

            // Retrieve and load all clubs to the ChoiceBox
            List<Club> clubs = dbConnection.getClubsByAdvisorId(advisorId);

            // Clear existing items in the ChoiceBox
            eventSchedulingChoiceBox.getItems().clear();


            for (Club club : clubs) {
                if (club != null) {
                    eventSchedulingChoiceBox.getItems().add(club);
                }
            }

            // Set a custom cell factory for the ChoiceBox to display club names
            eventSchedulingChoiceBox.setConverter(new StringConverter<Club>() {
                @Override
                public String toString(Club club) {
                    return club == null ? "" : club.getClubName();
                }

                @Override
                public Club fromString(String string) {
                    return null;
                }
            });
            EventSchedulingTypeChoiceBox.getItems().setAll("Meeting", "Activity", "Event");
            populateEventSchedulingTable(advisorId);
        } else {
            // If advisor ID doesn't exist, show an alert
            showAlert("Advisor ID Not Found", "The provided Advisor ID does not exist in the database.");
        }
    }

    @FXML
    public void eventScheduleClick(ActionEvent event) {
        int eventId = parseInt(EventSchedulingEventId.getText());
        String eventName = EventSchedulingEventName.getText();
        String eventDate = String.valueOf(EventSchedulingDatePicker.getValue());
        String time = EventSchedulingTime.getText();
        String description = EventSchedulingDescription.getText();
        String location = EventSchedulingLocation.getText();
        String TypeOrAgenda = EventSchedulingTypeOrAgenda.getText();
        String clubName = eventSchedulingChoiceBox.getValue().getClubName();
        String advisorId = eventSchedulingEnterField.getText();
        int clubId = dbConnection.getClubIdByClubName(clubName);


        String selectedType = EventSchedulingTypeChoiceBox.getValue();

        switch (selectedType) {
            case "Meeting":
                Meeting meeting = new Meeting(eventId, eventName, eventDate, time, location, description, clubId, advisorId, TypeOrAgenda);
                meetingList.add(meeting);
                dbConnection.insertMeetings(meetingList);
                meetingList.clear();
                break;
            case "Event":
                Event events = new Event(eventId, eventName, eventDate, time, location, description, clubId, advisorId, TypeOrAgenda);
                eventList.add(events);
                dbConnection.insertEvents(eventList);
                eventList.clear();
                break;
            case "Activity":
                Activity activity = new Activity(eventId, eventName, eventDate, time, location, description, clubId, advisorId, TypeOrAgenda);
                activityList.add(activity);
                dbConnection.insertActivities(activityList);
                activityList.clear();
                break;
            default:
                showAlert("Please Choose a Type", "Please select a type (Meeting, Event, or Activity).");
                break;

        }
//        dbConnection.insertmeetings(meetingList);
//        dbConnection.insertEvents(eventList);
//        dbConnection.insertActivities(activityList);


        EventSchedulingEventId.clear();
        EventSchedulingEventId.setStyle(null);
        EventSchedulingEventName.clear();
        EventSchedulingEventName.setStyle(null);
        EventSchedulingDatePicker.getEditor().clear();
        EventSchedulingDatePicker.setStyle(null);
        EventSchedulingDatePicker.setValue(null);
        EventSchedulingTime.clear();
        EventSchedulingTime.setStyle(null);
        EventSchedulingLocation.clear();
        EventSchedulingLocation.setStyle(null);
        EventSchedulingDescription.clear();
        EventSchedulingDescription.setStyle(null);
        EventSchedulingTypeOrAgenda.clear();
        EventSchedulingTypeOrAgenda.setStyle(null);
        populateEventSchedulingTable(advisorId);

    }


    private void populateEventSchedulingTable(String advisorId) {
        EventSchedulingTable.getItems().clear();

        // Fetch data from the database and add to the table
        List<ScheduleActivity> allActivities = new ArrayList<>();
        allActivities.addAll(dbConnection.getEventsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getMeetingsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getActivitiesByAdvisorId(advisorId));

        // Set cell value factories for each column
        EventIdColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        EventClubName.setCellValueFactory(param -> new SimpleStringProperty(getClubName(param.getValue().getClubId())));
        EventName.setCellValueFactory(cellData -> new SimpleStringProperty(getCombinedNames(cellData.getValue())));
        eventType.setCellValueFactory(cellData -> new SimpleStringProperty(getType(cellData.getValue())));
        eventScheduleDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventScheduleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EventScheduleLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        EventScheduleDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add the data to the table
        EventSchedulingTable.getItems().addAll(allActivities);
    }

    private String getClubName(int clubId) {
        return dbConnection.getClubNameById(clubId);
    }

    private String getCombinedNames(ScheduleActivity scheduleActivity) {
        if (scheduleActivity instanceof Event) {
            return ((Event) scheduleActivity).getTitle();
        } else if (scheduleActivity instanceof Meeting) {
            return ((Meeting) scheduleActivity).getTitle();
        } else if (scheduleActivity instanceof Activity) {
            return ((Activity) scheduleActivity).getTitle();
        } else {
            return "";
        }
    }

    private String getType(ScheduleActivity scheduleActivity) {
        if (scheduleActivity instanceof Event) {
            return "Event";
        } else if (scheduleActivity instanceof Meeting) {
            return "Meeting";
        } else if (scheduleActivity instanceof Activity) {
            return "Activity";
        } else {
            return "";
        }
    }

    @FXML
    public void enterDeleteAdvisorId(ActionEvent event) {
        String advisorId1 = EventDeleteAdvisorId.getText();

        if (dbConnection.isAdvisorIdExists(advisorId1)) {
            // If advisor ID exists, set the second pane visible
            EventDeleteSecondPane.setVisible(true);

            eventUpdateDeleteTable(advisorId1);


        } else {
            // If advisor ID doesn't exist, show an alert
            showAlert("Advisor ID Not Found", "The provided Advisor ID does not exist in the database.");
        }
    }

    public void eventUpdateDeleteTable(String advisorId) {
        EventUpdateDeleteTable.getItems().clear();

        // Fetch data from the database and add to the table
        List<ScheduleActivity> allActivities = new ArrayList<>();
        allActivities.addAll(dbConnection.getEventsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getMeetingsByAdvisorId(advisorId));
        allActivities.addAll(dbConnection.getActivitiesByAdvisorId(advisorId));

        EventDeleteId.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        EventDeleteName.setCellValueFactory(cellData -> new SimpleStringProperty(getCombinedNames(cellData.getValue())));
        EventDeleteActivityType.setCellValueFactory(cellData -> new SimpleStringProperty(getType(cellData.getValue())));
        EventDeleteDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        EventDeleteTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        EventDeleteLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        EventDeleteTypeAgenda.setCellValueFactory(cellData -> new SimpleStringProperty(getCombinedTypeAndAgenda(cellData.getValue())));
        EventDeleteDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        EventUpdateDeleteTable.getItems().addAll(allActivities);

        EventUpdateDeleteTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Populate the text fields with the details of the selected event
                EventUpdateDeleteEventId.setText(String.valueOf(newSelection.getEventId()));
                EventUpdateName.setText(newSelection.getTitle());
                EventUpdateTime.setText(newSelection.getTime());
                // Convert the date String to LocalDate and set it to the DatePicker
                EventUpdateDate.setValue(LocalDate.parse(newSelection.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                EventUpdateLocation.setText(newSelection.getLocation());
                EventUpdateDescription.setText(newSelection.getDescription());
                // Set type or agenda based on the event type
                if (newSelection instanceof Meeting) {
                    EventUpdateTypeOrAgenda.setText(((Meeting) newSelection).getAgenda());
                } else if (newSelection instanceof Event) {
                    EventUpdateTypeOrAgenda.setText(((Event) newSelection).getEventType());
                } else if (newSelection instanceof Activity) {
                    EventUpdateTypeOrAgenda.setText(((Activity) newSelection).getActivityType());
                }
            } else {
                // Clear the text fields if no event is selected
                EventUpdateDeleteEventId.clear();
                EventUpdateName.clear();
                EventUpdateTime.clear();
                EventUpdateDate.setValue(null);
                EventUpdateLocation.clear();
                EventUpdateDescription.clear();
                EventUpdateTypeOrAgenda.clear();
            }
        });


    }

    private String getCombinedTypeAndAgenda(ScheduleActivity scheduleActivity) {
        if (scheduleActivity instanceof Event) {
            return ((Event) scheduleActivity).getEventType();
        } else if (scheduleActivity instanceof Meeting) {
            return ((Meeting) scheduleActivity).getAgenda();
        } else if (scheduleActivity instanceof Activity) {
            return ((Activity) scheduleActivity).getActivityType();
        } else {
            return "";
        }
    }

    @FXML
    public void eventDeleteClick(ActionEvent event) {
        int eventId2 = parseInt(EventUpdateDeleteEventId.getText());

        deleteEventByEventId(eventId2);
        EventUpdateDeleteTable.refresh();
        EventUpdateDeleteEventId.clear();
        EventUpdateName.clear();
        EventUpdateTime.clear();
        EventUpdateDate.setValue(null);
        EventUpdateLocation.clear();
        EventUpdateDescription.clear();
        EventUpdateTypeOrAgenda.clear();
    }

    public void deleteEventByEventId(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();

        // Create lists to store events, meetings, and activities for the advisor
        List<Event> events = dbConnection.getEventsByAdvisorId(advisorId);
        List<Meeting> meetings = dbConnection.getMeetingsByAdvisorId(advisorId);
        List<Activity> activities = dbConnection.getActivitiesByAdvisorId(advisorId);

        boolean eventFound = false;

        // Iterate over the list using an iterator to avoid concurrent modification
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (event.getEventId() == eventId) {
                // Delete the event from the database
                dbConnection.deleteEvent(event);
                // Remove the event from the list
                eventIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the event is found and deleted
            }
        }

        boolean meetingFound = false;

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Delete the meeting from the database
                dbConnection.deleteMeeting(meeting);
                // Remove the meeting from the list
                meetingIterator.remove();
                meetingFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the meeting is found and deleted
            }
        }

        boolean activityFound = false;

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Delete the activity from the database
                dbConnection.deleteActivity(activity);
                // Remove the activity from the list
                activityIterator.remove();
                activityFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the activity is found and deleted
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound && !meetingFound && !activityFound) {
            showAlert("Event Not Found", "The specified Event ID was not found for the advisor.");
        }
    }

    @FXML
    public void eventSearchClick(ActionEvent event) {
        int eventId = parseInt(EventUpdateDeleteEventId.getText());
        setEventFieldsByEventId(eventId);

    }

    public void setEventFieldsByEventId(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();

        // Create lists to store events, meetings, and activities for the advisor
        List<Event> events = dbConnection.getEventsByAdvisorId(advisorId);
        List<Meeting> meetings = dbConnection.getMeetingsByAdvisorId(advisorId);
        List<Activity> activities = dbConnection.getActivitiesByAdvisorId(advisorId);

        boolean eventFound = false;

        // Iterate over the list using an iterator to avoid concurrent modification
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (event.getEventId() == eventId) {
                // Set the values to the text fields based on the retrieved event
                setEventDataFields(event);
                eventFound = true;
                break; // Exit the loop once the event is found
            }
        }

        boolean meetingFound = false;

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Set the values to the text fields based on the retrieved meeting
                setMeetingDataFields(meeting);
                meetingFound = true;
                break; // Exit the loop once the meeting is found
            }
        }

        boolean activityFound = false;

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Set the values to the text fields based on the retrieved activity
                setActivityDataFields(activity);
                activityFound = true;
                break; // Exit the loop once the activity is found
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound && !meetingFound && !activityFound) {
            showAlert("Event Not Found", "The specified Event ID was not found for the advisor.");
        }
    }

    public void setEventDataFields(Event event) {
        EventUpdateName.setText(event.getTitle());
        EventUpdateTime.setText(event.getTime());

        // Assuming that 'eventDate' is a String; if it's a Date object, you may need to format it
        EventUpdateDate.setValue(LocalDate.parse(event.getDate()));

        EventUpdateLocation.setText(event.getLocation());
        EventUpdateDescription.setText(event.getDescription());
        EventUpdateTypeOrAgenda.setText(event.getEventType());
    }

    public void setMeetingDataFields(Meeting meeting) {
        // Assuming Meeting has similar properties as Event
        EventUpdateName.setText(meeting.getTitle());
        EventUpdateTime.setText(meeting.getTime());

        // Assuming that 'meetingDate' is a String; if it's a Date object, you may need to format it
        EventUpdateDate.setValue(LocalDate.parse(meeting.getDate()));

        EventUpdateLocation.setText(meeting.getLocation());
        EventUpdateDescription.setText(meeting.getDescription());
        EventUpdateTypeOrAgenda.setText(meeting.getAgenda());
    }

    public void setActivityDataFields(Activity activity) {
        // Assuming Activity has similar properties as Event
        EventUpdateName.setText(activity.getTitle());
        EventUpdateTime.setText(activity.getTime());

        // Assuming that 'activityDate' is a String; if it's a Date object, you may need to format it
        EventUpdateDate.setValue(LocalDate.parse(activity.getDate()));

        EventUpdateLocation.setText(activity.getLocation());
        EventUpdateDescription.setText(activity.getDescription());
        EventUpdateTypeOrAgenda.setText(activity.getActivityType());
    }

    @FXML
    public void updateEventsClick(ActionEvent event) {
        int eventId1 = parseInt(EventUpdateDeleteEventId.getText());
        updateEvents(eventId1);

    }

    public void updateEvents(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();
        String newEventName = EventUpdateName.getText();
        String newEventDate = String.valueOf(EventUpdateDate.getValue());
        String newEventTime = EventUpdateTime.getText();
        String newEventLocation = EventUpdateLocation.getText();
        String newEventDescription = EventUpdateDescription.getText();
        String newEventAgendaOrType = EventUpdateTypeOrAgenda.getText();


        List<Event> events = dbConnection.getEventsByAdvisorId(advisorId);
        List<Meeting> meetings = dbConnection.getMeetingsByAdvisorId(advisorId);
        List<Activity> activities = dbConnection.getActivitiesByAdvisorId(advisorId);

        boolean eventFound = false;

        // Iterate over the list using an iterator to avoid concurrent modification
        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            if (event.getEventId() == eventId) {
                // Delete the event from the database
                dbConnection.updateEventByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the event from the list
                eventIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the event is found and deleted
            }
        }

        boolean meetingFound = false;

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Delete the meeting from the database
                dbConnection.updateMeetingByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the meeting from the list
                meetingIterator.remove();
                meetingFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the meeting is found and deleted
            }
        }

        boolean activityFound = false;

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Delete the activity from the database
                dbConnection.updateActivityByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the activity from the list
                activityIterator.remove();
                activityFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the activity is found and deleted
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound && !meetingFound && !activityFound) {
            showAlert("Event Not Found", "The specified Event ID was not found for the advisor.");
        }


    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    AttendanceTrackDatabaseConnection attendanceTrackDatabaseConnection = new AttendanceTrackDatabaseConnection();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void attendanceAdvisorClick(ActionEvent event) {
        // Get the advisor ID from the search field
        String advisorId = attendance_AdvisorID_search.getText();

        // Check if the advisor ID is empty or null
        if (advisorId == null || advisorId.trim().isEmpty()) {
            showAlert("Null Advisor ID", "Please enter an Advisor ID.");
            return;
        }

        // Check if the advisor ID is a valid integer
        try {
            Integer.parseInt(advisorId);
            showAlert("Invalid Advisor ID", "Advisor ID should not be a full-numeric value.");
            return;
        } catch (NumberFormatException e) {
            // Advisor ID is not a valid integer, continue with the check
        }

        // Check if the advisor ID exists in the database
        if (dbConnection.isAdvisorIdExists(advisorId)) {
            Attendance2pane.setVisible(true);
            List<String> clubNames = attendanceTrackDatabaseConnection.getClubNamesByAdvisorId(advisorId);
            attendance_Club_choice.getItems().addAll(clubNames);
        } else {
            // If advisor ID does not exist, show an alert
            showAlert("Advisor ID Not Found", "No records found for the provided Advisor ID.");
        }
        Attendance2pane.setVisible(true);
    }


    @FXML
    public void enterClubNameClick(ActionEvent event) {
        String clubName = attendance_Club_choice.getValue();
        int clubId = dbConnection.getClubIdByClubName(clubName);
        List<Integer> eventIds = attendanceTrackDatabaseConnection.getEventIdsByClubId(clubId);
        attendance_EventID_choice.getItems().addAll(eventIds);
        populateAttendanceTable(clubId);

    }

    @FXML
    public void populateAttendanceTable(int clubId) {
        // Call the method to get student details by clubId
        Map<String, String> studentsMap = attendanceTrackDatabaseConnection.getStudentsByClubId(clubId);

        // Create an observable list for the table
        ObservableList<Map.Entry<String, String>> data = FXCollections.observableArrayList(studentsMap.entrySet());

        // Clear existing columns
        attendance_table.getColumns().clear();

        // Set the cell value factories for the existing columns
        attendance_StudentID_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        attendance_StudentName_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        // Create a new TableColumn for attendance checkbox
        TableColumn<Map.Entry<String, String>, Boolean> attendance_Column = new TableColumn<>("Attendance");
        attendance_Column.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getKey();
            String studentName = cellData.getValue().getValue();


            // Initialize the Attendance object with a default attendance status of false
            Attendance attendance = new Attendance(studentId, studentName, studentId, false);


            // Set the checkbox value in the Attendance object when the property changes
            attendance.attendanceProperty().addListener((obs, oldValue, newValue) -> attendance.setAttendance(newValue));

            // Create a CheckBoxTableCell that displays the checkbox and allows manual interaction
            CheckBoxTableCell<Map.Entry<String, String>, Boolean> checkBoxTableCell = new CheckBoxTableCell<>();
            checkBoxTableCell.setSelectedStateCallback(index -> attendance.attendanceProperty());


            return attendance.attendanceProperty().asObject();

        });

        attendance_Column.setCellFactory(CheckBoxTableCell.forTableColumn(attendance_Column));

        // Add the columns in the desired order to the table
        attendance_table.getColumns().addAll(attendance_StudentID_column, attendance_StudentName_column, attendance_Column);

        attendance_Attendance_column.setEditable(true);

        attendance_table.setEditable(true);

        // Set the data to the table
        attendance_table.setItems(data);


    }

    @FXML
    public void attendance_MarkAttendance_click(ActionEvent event) {
        System.out.println("Attended Student : ");

        // Get the selected event from the ChoiceBox
        int selectedEvent = attendance_EventID_choice.getValue();

        // Check if an event is selected
        if (selectedEvent != 0) {
            // Set the current event ID
            AttendanceTrackDatabaseConnection.setCurrentEventId(selectedEvent);

            // Get the event type
            String eventType = attendanceTrackDatabaseConnection.getEventType(selectedEvent);
            ObservableList<Map.Entry<String, String>> selectedItems = attendance_table.getSelectionModel().getSelectedItems();

            // Debug: Print the event type
            System.out.println("Event Type: " + eventType);
            for (Map.Entry<String, String> selectedItem : selectedItems) {
                String studentId = selectedItem.getKey();

                // Print the details to the console
                System.out.println("Student ID: " + studentId);
                System.out.println("Event ID: " + AttendanceTrackDatabaseConnection.getCurrentEventId());
                System.out.println("----------------------");
                if ("Event".equals(eventType)) {
                    Attendance attendance = new Attendance(studentId, "Student Name", studentId, false);
                    attendanceTrackDatabaseConnection.updateStudentAttendanceTable(attendance, selectedEvent);
                } else  if ("Meeting".equals(eventType)) {
                    Attendance attendance = new Attendance(studentId, "Student Name", studentId, false);
                    attendanceTrackDatabaseConnection.updateStudentAttendanceMeetingsTable(attendance, selectedEvent);
                } else if ("Activity".equals(eventType)) {
                    Attendance attendance = new Attendance(studentId, "Student Name", studentId, false);
                    attendanceTrackDatabaseConnection.updateStudentAttendanceActivityTable(attendance, selectedEvent);
                }

                // Create an Attendance object with default attendance status of false

            }

        } else {
            // Handle the case when no event is selected, e.g., show an alert
            System.out.println("No event selected");
        }

    }


    @FXML
    public void attendance_SearchButtonClick(ActionEvent event) {
        // Get the entered student ID from the search bar
        String studentId = attendance_StudentID_search_bar.getText();

        // Get the selected club name from the ChoiceBox
        String selectedClub = attendance_Club_choice.getValue();

        // Check if both student ID and club are entered
        if (studentId == null || studentId.trim().isEmpty()) {
            showAlert("Null Student ID", "Please enter a Student ID.");
            return;
        }

        try {
            Integer.parseInt(studentId);
            showAlert("Invalid Student ID", "Student ID should not be a full-numeric value.");
            return;
        } catch (NumberFormatException e) {
            // Advisor ID is not a valid integer, continue with the check
        }

        if (selectedClub == null) {
            showAlert("Club not selected", "Please select a Club.");
            return;
        }

        int clubId = dbConnection.getClubIdByClubName(selectedClub);

        // Perform the search in the database
        Map<String, String> searchResults = attendanceTrackDatabaseConnection.searchStudentsByStudentIdAndClub(studentId, clubId);

        if (searchResults.isEmpty()) {
            showAlert("Student not found", "Student ID not found in the relevant club.");
            return;
        }

        // Update the GUI to display the search results
        updateSearchResultsOnGUI(searchResults);
    }

    private void updateSearchResultsOnGUI(Map<String, String> searchResults) {
        // Clear the existing data in the table
        attendance_table.getItems().clear();

        // Create an observable list for the table
        ObservableList<Map.Entry<String, String>> data = FXCollections.observableArrayList(searchResults.entrySet());

        // Set the cell value factories for the existing columns
        attendance_StudentID_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        attendance_StudentName_column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue()));

        // Create a new TableColumn for attendance checkbox
        TableColumn<Map.Entry<String, String>, Boolean> attendance_Column = new TableColumn<>("Attendance");
        attendance_Column.setCellValueFactory(cellData -> {
            String studentId = cellData.getValue().getKey();
            String studentName = cellData.getValue().getValue();

            // Initialize the Attendance object with a default attendance status of false
            Attendance attendance = new Attendance(studentId, studentName, studentId, false);

            // Set the checkbox value in the Attendance object when the property changes
            attendance.attendanceProperty().addListener((obs, oldValue, newValue) -> attendance.setAttendance(newValue));

            // Create a CheckBoxTableCell that displays the checkbox and allows manual interaction
            CheckBoxTableCell<Map.Entry<String, String>, Boolean> checkBoxTableCell = new CheckBoxTableCell<>();
            checkBoxTableCell.setSelectedStateCallback(index -> attendance.attendanceProperty());

            return attendance.attendanceProperty().asObject();
        });

        attendance_Column.setCellFactory(CheckBoxTableCell.forTableColumn(attendance_Column));

        // Add the columns in the desired order to the table
        attendance_table.getColumns().clear();
        attendance_table.getColumns().addAll(attendance_StudentID_column, attendance_StudentName_column, attendance_Column);

        attendance_Attendance_column.setEditable(true);

        // Set the data to the table
        attendance_table.setItems(data);
    }


}