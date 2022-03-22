package com.example.course;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ControllerMainTest {
    ControllerMain controllerMain = new ControllerMain();

    @org.junit.Test
    public void input() {
        String[] correctArray = {"5", "7", "4", "1"};
        String[] noCorrectArray = {"5 ", " 7", "4", "7"};
        String[] noCorrectArrayTwo = {"a", " 7", "4", "1"};
        boolean result = controllerMain.input(noCorrectArrayTwo);
        assertTrue(result);

    }

    @org.junit.Test
    public void sort() {
        int[] correctArray = {2, 1, 5 ,3, 4};
        String expectedArray = "[1, 2, 3, 4, 5]";
        int[] resultArray = controllerMain.sort(correctArray);
        String result = Arrays.toString(resultArray);
        assertEquals(expectedArray, result);

    }
}