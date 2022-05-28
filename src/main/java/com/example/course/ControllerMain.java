package com.example.course;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class ControllerMain {
    private String stringInput = "";
    private boolean check = true;

    @FXML
    private Button auto;

    @FXML
    private Button button;

    @FXML
    private Button clear;

    @FXML
    private Button info;

    @FXML
    private TextField input;

    @FXML
    private Label output;

    @FXML
    private Label outputTwo;

    @FXML
    private Button reg;

    public static int attempt = 2;

    // метод, вызывающийся при запуске данного окна.
    @FXML
    void initialize() {
        if(attempt == 10){
            auto.setDisable(true);
            reg.setDisable(true);
            outputTwo.setText("");
        }

        // кнопка, на которую нужно нажать, чтобы массив сортировался
        button.setOnAction(actionEvent -> {
            stringInput = input.getText();  // в переменную добавляем введенное значение
            String[] str = stringInput.split(" "); //  из строки делаем массив
            input(str);

            int[] arrayInt = new int[str.length]; // новых массив дял хранения цифр

            if (check) {
                for (int i = 0; i < str.length; i++) { // заполнение нового массива
                    arrayInt[i] = Integer.parseInt(str[i]);
                }

                String result = Arrays.toString(sort(arrayInt)) // вызывает метод и сразу записывает в переменную
                        .replace("[", "")  // удаляется скобка
                        .replace("]", "")  // удаляется скобка
                        .replace(",", "");  // удаляется запятая
                if(attempt == 10){
                    output.setText(result); // выводит в поле вывода
                }
                if(attempt == 3 || attempt == 2){
                    outputTwo.setText("У вас осталось " + attempt + " попытки");
                    output.setText(result); // выводит в поле вывода
                    attempt--;
                }
                else if(attempt == 1){
                    outputTwo.setText("У вас осталась " + attempt + " попытка");
                    output.setText(result); // выводит в поле вывода
                    attempt--;
                }
                else if(attempt == 0){
                    output.setText(result); // выводит в поле вывода
                    outputTwo.setText("Все попытки потрачены");
                    attempt--;
                }
                else if(attempt == -1){
                    outputTwo.setText("Попыток больше нет");
                }

            }
        });

        clear.setOnAction(actionEvent -> { // при нажатии очищает поле ввода
            input.clear();
        });

        info.setOnAction(actionEvent -> { // при нажатии открывается окно с помощью
            FXMLLoader loader = new FXMLLoader(); // класс для отображения
            loader.setLocation(getClass().getResource("information.fxml")); // путь нового окна
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene((new Scene(root)));
            stage.getIcons().add(new Image("file:src/main/resources/picture/icon.ico")); // путь иконки приложения
            stage.initModality(Modality.APPLICATION_MODAL); // нельзя будет двигать родительское окно
            stage.showAndWait(); // открывает новое окно, но не закрывает старое
        });

        reg.setOnAction(actionEvent -> { // при нажатии открывается окно с помощью
            open(("/com/example/course/registration.fxml"), reg);
        });
        auto.setOnAction(actionEvent -> {
            open(("/com/example/course/auto.fxml"), auto);
        });

    }

    public boolean input(String[] str) {
        for (String s : str) { // цикл идет по строке
            if (!s.matches("[-+]?\\d+") || Integer.parseInt(s) < 0 ) { // если есть что-то кроме цифр
                output.setText("Вы ввели неверные данные!");
                input.clear();
                check = false;
                break;
            } else {
                check = true;
            }
        }
        return check;
    }


    public int[] sort(int[] array) { // сортировка
        for (int i = 1; i < array.length; i++) {
            int x = array[i];
            // Ищет место для вставки, используя бинарный поиск
            int j = Math.abs(Arrays.binarySearch(array, 0, i, x) + 1);

            // Сдвигает массив на одно место вправо
            System.arraycopy(array, j, array, j + 1, i - j);

            // Размещение элемента в правильном месте
            array[j] = x;
        }
        return array;

    }

    private void open(String path, Button button) {
        button.getScene().getWindow().hide();
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
