module com.example.oopp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oopp to javafx.fxml;
    exports com.example.oopp;
}