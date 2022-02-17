package app;

import controller.CalcController;
import logic.CalcLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {
    public static void main(String[] args) {
        try {
            choiceOption();
        } catch (IOException e) {
            System.err.println("Źle zapisane działanie lub coś poszło nie tak!");
        } catch (ArithmeticException e) {
            System.err.println("Nie dzieli się przez 0!");
        } catch (NumberFormatException e) {
            System.err.println("Można używać tylko cyfr i odpowiednich znaków! Od 0 do 9 | . | , | * | / | + | - | ( | ) |");
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

    //Wpisywanie działania
    private static void typeAction(BufferedReader bufferedReader) throws IOException {
        System.out.print("Działanie: ");
        CalcLogic calcLogic = new CalcLogic();
        calcLogic.helper = bufferedReader.readLine();
        checkBrackets(calcLogic);
    }

    //Sprawdzanie nawiasów, zamiana przecinków na kropki, nawiasów kwadratowych na okrągłe
    private static void checkBrackets(CalcLogic calcLogic) throws IOException {
        calcLogic.stringBuilder.append(calcLogic.helper);
        for (int x = 0; x < calcLogic.stringBuilder.length(); x++) {
            if (calcLogic.stringBuilder.charAt(x) == '(') {
                calcLogic.setLeftBracket(calcLogic.getLeftBracket() + 1);
            }
            if (calcLogic.stringBuilder.charAt(x) == ')') {
                calcLogic.setRightBracket(calcLogic.getRightBracket() + 1);
            }
            if ((calcLogic.stringBuilder.charAt(x) == '[')) {
                calcLogic.stringBuilder.replace(x, x + 1, "(");
                calcLogic.setLeftBracket(calcLogic.getLeftBracket() + 1);
            }
            if (calcLogic.stringBuilder.charAt(x) == ']') {
                calcLogic.stringBuilder.replace(x, x + 1, ")");//zabezpieczenie przed nawiasami kwadratowymi
                calcLogic.setRightBracket(calcLogic.getRightBracket() + 1);
            }
            if (calcLogic.stringBuilder.charAt(x) == ',') {
                calcLogic.stringBuilder.replace(x, x + 1, ".");//zabezpieczenie przed przecinkiem
            }
            if (calcLogic.stringBuilder.charAt(x) == '*' || calcLogic.stringBuilder.charAt(x) == '/') calcLogic.setMultiply(true);
        }
        if (calcLogic.getLeftBracket() != calcLogic.getRightBracket()) throw new IOException();
        checkCorrectAction(calcLogic);
    }

    //Sprawdzanie działania pod względem logiki
    private static void checkCorrectAction(CalcLogic calcLogic) throws IOException {
        if (calcLogic.stringBuilder.charAt(0) != '0' && calcLogic.stringBuilder.charAt(0) != '1' && calcLogic.stringBuilder.charAt(0) != '2' && calcLogic.stringBuilder.charAt(0) != '3' &&
                calcLogic.stringBuilder.charAt(0) != '4' && calcLogic.stringBuilder.charAt(0) != '5' && calcLogic.stringBuilder.charAt(0) != '6' && calcLogic.stringBuilder.charAt(0) != '7' &&
                calcLogic.stringBuilder.charAt(0) != '8' && calcLogic.stringBuilder.charAt(0) != '9' && calcLogic.stringBuilder.charAt(0) != '(' && calcLogic.stringBuilder.charAt(0) != '-')
            throw new IOException();

        if (calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '0' && calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '1' &&
                calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '2' && calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '3' &&
                calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '4' && calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '5' &&
                calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '6' && calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '7' &&
                calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '8' && calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != '9' &&
                calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) != ')') throw new IOException();

        for (int x = 0; x < calcLogic.stringBuilder.length(); x++) {
            if (x != 0 && calcLogic.stringBuilder.charAt(x) == '.' && calcLogic.stringBuilder.charAt(x - 1) == '.')
                throw new IOException();
            if (calcLogic.stringBuilder.charAt(x) != '0' && calcLogic.stringBuilder.charAt(x) != '1' && calcLogic.stringBuilder.charAt(x) != '2' && calcLogic.stringBuilder.charAt(x) != '3' &&
                    calcLogic.stringBuilder.charAt(x) != '4' && calcLogic.stringBuilder.charAt(x) != '5' && calcLogic.stringBuilder.charAt(x) != '6' && calcLogic.stringBuilder.charAt(x) != '7' &&
                    calcLogic.stringBuilder.charAt(x) != '8' && calcLogic.stringBuilder.charAt(x) != '9' && calcLogic.stringBuilder.charAt(x) != '(' && calcLogic.stringBuilder.charAt(x) != ')' &&
                    calcLogic.stringBuilder.charAt(x) != '.' && calcLogic.stringBuilder.charAt(x) != '+' && calcLogic.stringBuilder.charAt(x) != '-' && calcLogic.stringBuilder.charAt(x) != '*' && calcLogic.stringBuilder.charAt(x) != '/')
                throw new NumberFormatException();
        }
        fillEmptyBrackets(calcLogic);
    }

    //dodawanie znakow mnozenia np. 3(2+8)(4-3) = 3*(2+8)*(4-3)
    private static void addMultiply(CalcLogic calcLogic) {
        for (int x = 1; x < calcLogic.stringBuilder.length(); x++) {
            if (x + 1 < calcLogic.stringBuilder.length() && calcLogic.stringBuilder.charAt(x - 1) != '(' && calcLogic.stringBuilder.charAt(x) == '(' && calcLogic.stringBuilder.charAt(x - 1) != '/' && calcLogic.stringBuilder.charAt(x - 1) != '*' && calcLogic.stringBuilder.charAt(x - 1) != '-' && calcLogic.stringBuilder.charAt(x - 1) != '+') {
                calcLogic.stringBuilder.replace(x, x + 1, "*(");
            }
        }
        for (int x = 0; x < calcLogic.stringBuilder.length() - 1; x++) {
            if (x != 0 && calcLogic.stringBuilder.charAt(x + 1) != ')' && calcLogic.stringBuilder.charAt(x) == ')' && calcLogic.stringBuilder.charAt(x + 1) != '/' && calcLogic.stringBuilder.charAt(x + 1) != '*' && calcLogic.stringBuilder.charAt(x + 1) != '-' && calcLogic.stringBuilder.charAt(x + 1) != '+') {
                calcLogic.stringBuilder.replace(x, x + 1, ")*");
            }
        }
        divideByZero(calcLogic);
    }

    //Uzupełnianie pustych nawiasów np. ()() = 0*0
    private static void fillEmptyBrackets(CalcLogic calcLogic) {
        for (int x = 0; x < calcLogic.stringBuilder.length(); x++) {
            if (calcLogic.stringBuilder.charAt(x) == '(' && calcLogic.stringBuilder.charAt(x + 1) == ')') {
                calcLogic.stringBuilder.replace(x, x + 2, "(0)");
            }
        }
        addMultiply(calcLogic);
    }

    //Sprawdzanie problemu dzielenia przez 0!
    private static void divideByZero(CalcLogic calcLogic) {
        if (calcLogic.stringBuilder.charAt(calcLogic.stringBuilder.length() - 1) == '0') {
            for (int x = 0; x < calcLogic.stringBuilder.length(); x++) {
                if (calcLogic.stringBuilder.charAt(x) == '/' && calcLogic.stringBuilder.charAt(x + 1) == '0')
                    throw new ArithmeticException();
            }
        } else {
            for (int x = 0; x < calcLogic.stringBuilder.length(); x++) {
                if (calcLogic.stringBuilder.charAt(x) == '/' && calcLogic.stringBuilder.charAt(x + 1) == '0' && calcLogic.stringBuilder.charAt(x + 2) != '.')
                    throw new ArithmeticException();
            }
        }

        new CalcController(calcLogic);
    }
}
