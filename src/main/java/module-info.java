module com.example.course {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.sql;


    opens com.example.course to javafx.fxml;
    exports com.example.course;
}