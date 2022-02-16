package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try {
            choiceOption();
        } catch (IOException e) {
            System.err.println("Źle zapisane działanie lub coś poszło nie tak!");
        }
    }

    private static void choiceOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int option = -1;
        while(option != 2){
            System.out.println("1 - Działanie");
            System.out.println("2 - Exit");
            System.out.print("Wybór: ");
            option = bufferedReader.read();
            System.out.println();

            switch (option){
                case 1:
                    checkBrackets(bufferedReader);
                    break;
                case 2:
                    System.out.println("Zamykam aplikację...");
                    break;
                default:
                    System.out.println("Nie ma takiego wyboru!");
            }
        }
    }

    private static void checkBrackets(BufferedReader bufferedReader) throws IOException{

    }
}
