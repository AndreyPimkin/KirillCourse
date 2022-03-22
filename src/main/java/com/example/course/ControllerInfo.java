package com.example.course;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    void initialize() {
        button.setOnAction(actionEvent -> {
            button.getScene().getWindow().hide(); // прячет окно при нажатии на кнопку
        });
    }

}
