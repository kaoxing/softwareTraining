module com.example.softwaretraining {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.softwaretraining to javafx.fxml;
    exports com.example.softwaretraining;
}