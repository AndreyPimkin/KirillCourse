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
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private Button info;

    @FXML
    private Button clear;

    @FXML
    private TextField input;

    @FXML
    private Label output;

    // метод, вызывающийся при запуске данного окна.
    @FXML
    void initialize() {

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

                output.setText(result); // выводит в поле вывода
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

    }

    public boolean input(String[] str) {
        for (String s : str) { // цикл идет по строке
            if (!s.matches("[-+]?\\d+")) { // если есть что-то кроме цифр
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
            System.arraycopy(array, j,
                    array, j + 1, i - j);

            // Размещение элемента в правильном месте
            array[j] = x;
        }
        return array;

    }
}
