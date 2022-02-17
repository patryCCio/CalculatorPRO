package app;

import logic.CalcLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {
    public static void main(String[] args) {
        try {
            choiceOption();
        } catch (IOException | NumberFormatException e){
            System.err.println(e.getMessage());
        } catch (ArithmeticException e){
            System.err.println("Nie dziel przez 0!");
        }
    }

    //Wybór opcji menu
    private static void choiceOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int option = -1;
        while (option != 2) {
            System.out.println("1 - Działanie");
            System.out.println("2 - Exit");
            System.out.print("Wybór: ");
            option = Integer.parseInt(bufferedReader.readLine());
            System.out.println();

            switch (option) {
                case 1 -> {
                    CalcLogic calcLogic = new CalcLogic();
                    calcLogic.typeAction(bufferedReader);
                }
                case 2 -> System.out.println("Zamykam aplikację...");
                default -> System.out.println("Nie ma takiego wyboru!");
            }
        }
        bufferedReader.close();
    }

}
