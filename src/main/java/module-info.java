module com.example.oopp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires mysql.connector.java;


    opens com.example.oopp to javafx.fxml;
    exports com.example.oopp;
}