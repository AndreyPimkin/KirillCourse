package com.example.course;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.DatabaseHandler;
import server.User;

public class Auto {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private Button open;

    @FXML
    private PasswordField password;

    @FXML
    private Label text;

    @FXML
    void initialize() {
        open.setOnAction(actionEvent -> {
            String login = this.login.getText().trim();
            String password = this.password.getText().trim();
            if (!login.equals("") && !password.equals("")) {
                try {
                    loginUser(login, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                text.setText("Пустые данные");

            }
        });

    }

    private void loginUser(String login, String password) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        ResultSet resultAuto = dbHandler.login(user);
        if(resultAuto.next()){
            ControllerMain.attempt = 10;
            open("/com/example/course/main.fxml");
        }
        else text.setText("Такой пользователь не существует");
    }

    private void open(String path) {
        open.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/icon.ico"));
        stage.setTitle("Binary insert");
        stage.show();
    }
}
