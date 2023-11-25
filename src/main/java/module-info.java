module com.example.oopp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.oopp to javafx.fxml;
    exports com.example.oopp;
}