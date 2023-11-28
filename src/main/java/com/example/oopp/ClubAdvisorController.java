package com.example.oopp;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.time.DateTimeException;
import java.sql.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClubAdvisorController {
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
    private TableColumn<ScheduleActivity,Integer> EventIdColumn;
    @FXML
    private TableColumn<ScheduleActivity, String> EventClubName;
    @FXML
    private TableColumn<ScheduleActivity, String> EventName;
    @FXML
    private TableColumn<ScheduleActivity, String> eventType;
    @FXML
    private TableColumn<ScheduleActivity,String> eventScheduleDate;
    @FXML
    private TableColumn<ScheduleActivity,String> eventScheduleTime;
    @FXML
    private TableColumn<ScheduleActivity,String> EventScheduleLocation;
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
    private TableColumn<ScheduleActivity , Date> EventDeleteDate;
    @FXML
    private TableColumn<ScheduleActivity, String> EventDeleteTime;
    @FXML
    private TableColumn<ScheduleActivity, String > EventDeleteLocation;
    @FXML
    private TableColumn<ScheduleActivity , String> EventDeleteTypeAgenda;
    @FXML
    private TableColumn<ScheduleActivity,String> EventDeleteDescription;
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
    @FXML
    private TableView<String> advisorJoinReqTable;
    @FXML
    private TableColumn<String,String> joinReqStudentName;
    @FXML
    private TableColumn<String,String> joinReqClubName;




    private static List<Event> eventList = new ArrayList<>();
    private static List<Meeting> meetingList = new ArrayList<>();
    private static List<Activity> activityList = new ArrayList<>();

    @FXML
    public void mainClick(ActionEvent event){
        if (event.getSource()==ManageClubButton){
            createClubAnchorPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }
        if (event.getSource()==SheduleEventsButton){
            eventSchedulingPane.setVisible(true);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);

        }
        if (event.getSource()==clubJoinRequestButton) {
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }

        if (event.getSource()==clubAttendenceButton){
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(true);
            eventSchedulingSecondPane.setVisible(false);
            eventUpdatePane.setVisible(false);
            EventDeleteSecondPane.setVisible(false);
        }
        if (event.getSource() == EventDeleteUpdateButton){
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
            showAlert("Advisor ID Not Found", "Provide a valid Advisor ID");
        }
    }
    @FXML
    public void eventScheduleClick(ActionEvent event) {
        try {
            // Validate other fields (non-empty check)
            String eventName = EventSchedulingEventName.getText();
            String time = EventSchedulingTime.getText();
            String description = EventSchedulingDescription.getText();
            String location = EventSchedulingLocation.getText();
            String TypeOrAgenda = EventSchedulingTypeOrAgenda.getText();
            String advisorId = eventSchedulingEnterField.getText();
            String selectedType = EventSchedulingTypeChoiceBox.getValue();

            boolean hasEmptyFields = false;

            // Set red border color for empty fields
            if (eventName.isEmpty()) {
                EventSchedulingEventName.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingEventName.setStyle(null);
            }

            if (time.isEmpty()) {
                EventSchedulingTime.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                // Validate time format (e.g., 12am)
                if (!time.matches("^\\d{1,2}(am|pm)$")) {
                    showAlert("Invalid Time Format", "Please enter a valid time format (e.g., 12am).");
                    EventSchedulingTime.setStyle("-fx-border-color: red;");
                    return;
                } else {
                    EventSchedulingTime.setStyle(null);
                }
            }

            if (description.isEmpty()) {
                EventSchedulingDescription.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingDescription.setStyle(null);
            }

            if (location.isEmpty()) {
                EventSchedulingLocation.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingLocation.setStyle(null);
            }

            if (TypeOrAgenda.isEmpty()) {
                EventSchedulingTypeOrAgenda.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingTypeOrAgenda.setStyle(null);
            }

            if (advisorId.isEmpty()) {
                eventSchedulingEnterField.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                eventSchedulingEnterField.setStyle(null);
            }

            if (selectedType == null) {
                EventSchedulingTypeChoiceBox.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                EventSchedulingTypeChoiceBox.setStyle(null);
            }

            // Check if ClubName is selected
            if (eventSchedulingChoiceBox.getValue() == null) {
                eventSchedulingChoiceBox.setStyle("-fx-border-color: red;");
                hasEmptyFields = true;
            } else {
                eventSchedulingChoiceBox.setStyle(null);
            }

            if (hasEmptyFields) {
                showAlert("Incomplete Form", "Please fill in all required fields.");
                return;
            }

            // Reset border color for non-empty fields
            EventSchedulingEventName.setStyle(null);
            EventSchedulingTime.setStyle(null);
            EventSchedulingDescription.setStyle(null);
            EventSchedulingLocation.setStyle(null);
            EventSchedulingTypeOrAgenda.setStyle(null);
            eventSchedulingEnterField.setStyle(null);
            EventSchedulingTypeChoiceBox.setStyle(null);
            eventSchedulingChoiceBox.setStyle(null);

            // Validate Event ID (if not empty)
            String eventIdText = EventSchedulingEventId.getText();
            int eventId = eventIdText.isEmpty() ? 0 : Integer.parseInt(eventIdText);

            // Validate Event Date (if not empty)
            LocalDate eventDateLocal = EventSchedulingDatePicker.getValue();
            if (eventDateLocal == null) {
                EventSchedulingDatePicker.setStyle("-fx-border-color: red;");
                showAlert("Invalid Input", "Please select a valid Event Date.");
                return;
            } else {
                // Reset border color for EventSchedulingDatePicker
                EventSchedulingDatePicker.setStyle(null);
            }

// Convert LocalDate to String
            String eventDate = eventDateLocal.toString();


            if (isDuplicateEventId(eventId)) {
                // Set red border color for EventSchedulingEventId
                EventSchedulingEventId.setStyle("-fx-border-color: red;");
                showAlert("Duplicate Event ID", "Event ID already exists. Please choose a different one.");
                return;
            } else {
                // Reset border color for EventSchedulingEventId
                EventSchedulingEventId.setStyle(null);
            }

            int clubId = dbConnection.getClubIdByClubName(eventSchedulingChoiceBox.getValue().getClubName());

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

            // Clear input fields
            clearInputFields();

            // Populate the table
            populateEventSchedulingTable(advisorId);

        } catch (NumberFormatException e) {
            EventSchedulingEventId.setStyle("-fx-border-color: red;");
            // The input is not a valid integer for Event ID
            // Show an error message or alert to the user
            showAlert("Invalid Event ID", "Please enter a valid integer for Event ID.");
        }
    }



    private void clearInputFields() {

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
    }


    public boolean isDuplicateEventId(int eventId) {
        // Get all events, meetings, and activities
        List<Event> events = dbConnection.getAllEvents();
        List<Meeting> meetings = dbConnection.getAllMeetings();
        List<Activity> activities = dbConnection.getAllActivities();

        // Check for duplicate Event ID in events
        for (Event event : events) {
            if (event.getEventId() == eventId) {
                showAlert("Duplicate Event ID", "An event with the specified ID already exists.");
                return true;
            }
        }

        // Check for duplicate Event ID in meetings
        for (Meeting meeting : meetings) {
            if (meeting.getEventId() == eventId) {
                showAlert("Duplicate Event ID", "A meeting with the specified ID already exists.");
                return true;
            }
        }

        // Check for duplicate Event ID in activities
        for (Activity activity : activities) {
            if (activity.getEventId() == eventId) {
                showAlert("Duplicate Event ID", "An activity with the specified ID already exists.");
                return true;
            }
        }

        // If no duplicate Event ID is found
        return false;
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
    public void enterDeleteAdvisorId(ActionEvent event){
        String advisorId1 = EventDeleteAdvisorId.getText();

        if (dbConnection.isAdvisorIdExists(advisorId1)) {
            // If advisor ID exists, set the second pane visible
            EventDeleteSecondPane.setVisible(true);

            eventUpdateDeleteTable(advisorId1);


        } else {
            // If advisor ID doesn't exist, show an alert
            showAlert("Advisor ID Not Found", "Provide valid Advisor ID");
        }
    }

    public void eventUpdateDeleteTable(String advisorId){
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
                } else if (newSelection instanceof Event){
                    EventUpdateTypeOrAgenda.setText(((Event) newSelection).getEventType());
                }else if (newSelection instanceof Activity){
                    EventUpdateTypeOrAgenda.setText(((Activity)newSelection).getActivityType());
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
    public void eventDeleteClick(ActionEvent event){
        try {
            int eventId2 = Integer.parseInt(EventUpdateDeleteEventId.getText());
            setEventFieldsByEventId(eventId2);
            deleteEventByEventId(eventId2);
            EventUpdateDeleteTable.refresh();
            EventUpdateDeleteEventId.clear();
            EventUpdateName.clear();
            EventUpdateTime.clear();
            EventUpdateDate.setValue(null);
            EventUpdateLocation.clear();
            EventUpdateDescription.clear();
            EventUpdateTypeOrAgenda.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for Event ID.");
        }


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
        try {
            int eventId = Integer.parseInt(EventUpdateDeleteEventId.getText());
            setEventFieldsByEventId(eventId);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for Event ID.");
        }
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
    public void updateEventsClick(ActionEvent event){
        try {
            int eventId1 = Integer.parseInt(EventUpdateDeleteEventId.getText());
            updateEvents(eventId1);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer for Event ID.");
        }


    }

    public void updateEvents(int eventId) {
        String advisorId = EventDeleteAdvisorId.getText();
        String newEventName = EventUpdateName.getText();
        String newEventTime = EventUpdateTime.getText();
        String newEventLocation = EventUpdateLocation.getText();
        String newEventDescription = EventUpdateDescription.getText();
        String newEventAgendaOrType = EventUpdateTypeOrAgenda.getText();

        boolean hasEmptyFields = false;

        // Validate other fields (non-empty check)
        if (newEventName.isEmpty() || newEventLocation.isEmpty()
                || newEventDescription.isEmpty() || newEventAgendaOrType.isEmpty()) {
            hasEmptyFields = true;

            // Set red border color for empty fields
            if (newEventName.isEmpty()) {
                EventUpdateName.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateName.setStyle(null);
            }

            if (newEventLocation.isEmpty()) {
                EventUpdateLocation.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateLocation.setStyle(null);
            }

            if (newEventDescription.isEmpty()) {
                EventUpdateDescription.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateDescription.setStyle(null);
            }

            if (newEventAgendaOrType.isEmpty()) {
                EventUpdateTypeOrAgenda.setStyle("-fx-border-color: red;");
            } else {
                EventUpdateTypeOrAgenda.setStyle(null);
            }
        } else {
            // Reset border color for non-empty fields
            EventUpdateName.setStyle(null);
            EventUpdateLocation.setStyle(null);
            EventUpdateDescription.setStyle(null);
            EventUpdateTypeOrAgenda.setStyle(null);
        }

        // Validate Date
        LocalDate newEventDateLocal = EventUpdateDate.getValue();
        if (newEventDateLocal == null) {
            EventUpdateDate.setStyle("-fx-border-color: red;");
            showAlert("Invalid Input", "Please select a valid Event Date.");
            return;
        } else {
            // Reset border color for EventSchedulingDatePicker
            EventUpdateDate.setStyle(null);
        }
        String newEventDate = newEventDateLocal.toString();

        // Validate Time
        if (newEventTime.isEmpty()) {
            EventUpdateTime.setStyle("-fx-border-color: red;");
            hasEmptyFields = true;
        } else {
            // Validate time format (e.g., 12am)
            if (!newEventTime.matches("^\\d{1,2}(am|pm)$")) {
                showAlert("Invalid Time Format", "Please enter a valid time format (e.g., 12am).");
                EventUpdateTime.setStyle("-fx-border-color: red;");
                return;
            } else {
                EventUpdateTime.setStyle(null);
            }
        }

        if (hasEmptyFields) {
            showAlert("Incomplete Form", "Please fill in all required fields.");
            return;
        }

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

        // Similar changes for meetings
        Iterator<Meeting> meetingIterator = meetings.iterator();
        while (meetingIterator.hasNext()) {
            Meeting meeting = meetingIterator.next();
            if (meeting.getEventId() == eventId) {
                // Delete the meeting from the database
                dbConnection.updateMeetingByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the meeting from the list
                meetingIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the meeting is found and deleted
            }
        }

        // Similar changes for activities
        Iterator<Activity> activityIterator = activities.iterator();
        while (activityIterator.hasNext()) {
            Activity activity = activityIterator.next();
            if (activity.getEventId() == eventId) {
                // Delete the activity from the database
                dbConnection.updateActivityByEventId(eventId, newEventName, newEventDate, newEventTime, newEventLocation, newEventDescription, newEventAgendaOrType);
                // Remove the activity from the list
                activityIterator.remove();
                eventFound = true;
                eventUpdateDeleteTable(advisorId);
                break; // Exit the loop once the activity is found and deleted
            }
        }

        // Show the "Event Not Found" alert if the event is not found in any of the lists
        if (!eventFound) {
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


}
