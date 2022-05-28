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

public class Registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

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
            String firstname = this.firstname.getText().trim();
            String lastname = this.lastname.getText().trim();
            if (!login.equals("") && !password.equals("") && !firstname.equals("") && !lastname.equals(""))
                loginUser(login, password, firstname, lastname); //Проверяет на пустоту
            else {
                text.setText("Вы что-то не ввели");
            }
        });
    }

    private void loginUser(String login, String password, String firstname, String lastname) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        System.out.println(login);
        user.setLogin(login);
        ResultSet result = dbHandler.getLogin(user);
        try {
            if (result.next()) {
                text.setText("Пользователь уже существует");
            } else {
                User userLogin = new User();
                userLogin.setFirstname(firstname);
                userLogin.setLastname(lastname);
                userLogin.setLogin(login);
                userLogin.setPassword(password);
                try {
                    dbHandler.registration(userLogin);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                ControllerMain.attempt = 10;
                open("/com/example/course/main.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void open(String window) {
        open.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/icon.ico"));
        stage.show();
    }

}
