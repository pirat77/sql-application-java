package com.codecool.si3.controler;

import java.util.Scanner;
import java.util.Stack;

public class InputProvider {
    private Scanner scan;

    public InputProvider() {
        scan = new Scanner(System.in);
        scan.useDelimiter(System.lineSeparator());
    }

    private String getInput(){
        return scan.next();
    }

    private boolean isInputInt(String userInput) {
        if (userInput.matches("^[0-9]*$")) { return true; }
        else { return false; }
    }

    private String validateAgainstSQLinjection(String input){
        input.replaceAll("'", "");
        input.replaceAll("\"", "");
        input.replaceAll("%", "");
        input.replaceAll("\\?", "");
        return input;
    }

    public int getIntInput(String message){
        System.out.println(message);
        String userInput = getInput();
        while (!isInputInt(userInput)) {
            System.out.println("Provide numeric input, please.");
            userInput = getInput();
        }
        return Integer.parseInt(userInput);
    }

    public String getStringInput(String message){
        System.out.println(message);
        return validateAgainstSQLinjection(getInput());
    }

    private boolean isNumberInRange(String userInput, int rangeMin, int rangeMax) {
        if (userInput.matches("^[0-9]*$")) {
            int userInt = Integer.parseInt(userInput);
            return userInt >= rangeMin && userInt <= rangeMax;
        }
        return false;
    }

    public int gatherIntInput(String message, int rangeMin, int rangeMax) {
        System.out.println(message);
        String userInput = getInput();
        while (!isNumberInRange(userInput, rangeMin, rangeMax-1)) {
            System.out.println("Invalid input, please try again: ");
            userInput = getInput();
        }
        return Integer.parseInt(userInput);
    }
}
