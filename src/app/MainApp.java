package app;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        choiceOption();
    }

    private static void choiceOption(){
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        while(option != 2){
            System.out.println("1 - Działanie");
            System.out.println("2 - Exit");
            System.out.print("Wybór: ");
            option = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (option){
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("Zamykam aplikację...");
                    break;
                default:
                    System.out.println("Nie ma takiego wyboru!");
            }
        }
    }
}
