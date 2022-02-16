package app;

import logic.CalcLogic;

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
            option = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            switch (option){
                case 1:
                    typeAction(bufferedReader);
                    break;
                case 2:
                    System.out.println("Zamykam aplikację...");
                    break;
                default:
                    System.out.println("Nie ma takiego wyboru!");
            }
        }
        bufferedReader.close();
    }

    private static void typeAction(BufferedReader bufferedReader) throws IOException{
        System.out.print("Działanie: ");
        CalcLogic calcLogic = new CalcLogic();
        calcLogic.helper = bufferedReader.readLine();
        checkBrackets(calcLogic);
    }

    private static void checkBrackets(CalcLogic calcLogic) throws IOException{
        calcLogic.stringBuilder.append(calcLogic.helper);
        for(int x=0; x<calcLogic.helper.length(); x++){

        }
    }
}
