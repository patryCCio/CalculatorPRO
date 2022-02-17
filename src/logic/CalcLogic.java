package logic;

import controller.CalcController;
import data.CalcData;

import java.io.BufferedReader;
import java.io.IOException;

public class CalcLogic {

    CalcData calcData = new CalcData();

    //Wpisywanie działania
    public void typeAction(BufferedReader bufferedReader) throws IOException {
        System.out.print("Działanie: ");
        calcData.helper = bufferedReader.readLine();
        calcData.stringBuilder.append(calcData.helper);

        if(checkCorrect())throw new IOException("Niewłaściwie zakończone lub rozpoczęte działanie!");
        for (int x = 0; x < calcData.stringBuilder.length(); x++) {
            checkBrackets(x);
            checkCorrectAction(x);
            fillEmptyBrackets(x);
        }
        divideByZero();
        addMultiply();
        if (calcData.getLeftBracket() != calcData.getRightBracket()) throw new IOException("Niewłaściwie skonstruowane nawiasy! Za mała liczba nawiasów!");

        //Jeżeli się wszystko zgadza to przechodzimy do CalcController
        CalcController calcController = new CalcController();
        calcController.doAction(calcData);

    }

    //Sprawdzanie nawiasów, zamiana przecinków na kropki, nawiasów kwadratowych na okrągłe
    private void checkBrackets(int x) {
        if (calcData.stringBuilder.charAt(x) == '(') {
            calcData.setLeftBracket(calcData.getLeftBracket() + 1);
        }
        if (calcData.stringBuilder.charAt(x) == ')') {
            calcData.setRightBracket(calcData.getRightBracket() + 1);
        }
        if ((calcData.stringBuilder.charAt(x) == '[')) {
            calcData.stringBuilder.replace(x, x + 1, "(");
            calcData.setLeftBracket(calcData.getLeftBracket() + 1);
        }
        if (calcData.stringBuilder.charAt(x) == ']') {
            calcData.stringBuilder.replace(x, x + 1, ")");//zabezpieczenie przed nawiasami kwadratowymi
            calcData.setRightBracket(calcData.getRightBracket() + 1);
        }
        if (calcData.stringBuilder.charAt(x) == ',') {
            calcData.stringBuilder.replace(x, x + 1, ".");//zabezpieczenie przed przecinkiem
        }

        if (calcData.stringBuilder.charAt(x) == ' ') calcData.stringBuilder.replace(x, x, "");
    }


    //Sprawdzanie początku i zakończeń działania
    private boolean checkCorrect(){
        boolean isIO = false;

        if (calcData.stringBuilder.charAt(0) != '0' && calcData.stringBuilder.charAt(0) != '1' && calcData.stringBuilder.charAt(0) != '2' && calcData.stringBuilder.charAt(0) != '3' &&
                calcData.stringBuilder.charAt(0) != '4' && calcData.stringBuilder.charAt(0) != '5' && calcData.stringBuilder.charAt(0) != '6' && calcData.stringBuilder.charAt(0) != '7' &&
                calcData.stringBuilder.charAt(0) != '8' && calcData.stringBuilder.charAt(0) != '9' && calcData.stringBuilder.charAt(0) != '(' && calcData.stringBuilder.charAt(0) != '-'){
            isIO = true;
        }
        if (calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '0' && calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '1' &&
                calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '2' && calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '3' &&
                calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '4' && calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '5' &&
                calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '6' && calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '7' &&
                calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '8' && calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != '9' &&
                calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) != ')'){
            isIO = true;
        }
        return isIO;
    }

    //Sprawdzanie działania pod względem logiki
    private void checkCorrectAction(int x) throws IOException, NumberFormatException{
            if (x != 0 && calcData.stringBuilder.charAt(x) == '.' && calcData.stringBuilder.charAt(x - 1) == '.')
                throw new IOException("Kropka po kropce...!");

            if (calcData.stringBuilder.charAt(x) != '0' && calcData.stringBuilder.charAt(x) != '1' && calcData.stringBuilder.charAt(x) != '2' && calcData.stringBuilder.charAt(x) != '3' &&
                    calcData.stringBuilder.charAt(x) != '4' && calcData.stringBuilder.charAt(x) != '5' && calcData.stringBuilder.charAt(x) != '6' && calcData.stringBuilder.charAt(x) != '7' &&
                    calcData.stringBuilder.charAt(x) != '8' && calcData.stringBuilder.charAt(x) != '9' && calcData.stringBuilder.charAt(x) != '(' && calcData.stringBuilder.charAt(x) != ')' &&
                    calcData.stringBuilder.charAt(x) != '.' && calcData.stringBuilder.charAt(x) != '+' && calcData.stringBuilder.charAt(x) != '-' && calcData.stringBuilder.charAt(x) != '*' && calcData.stringBuilder.charAt(x) != '/')
                throw new NumberFormatException("Nie można podawać innych znaków niż: od 0 do 9; (; ); ,; .; +; -; *; /; [; ].");
    }

    //dodawanie znakow mnozenia np. 3(2+8)(4-3) = 3*(2+8)*(4-3)
    private void addMultiply() {
        for (int x = 1; x < calcData.stringBuilder.length(); x++) {
            if (x + 1 < calcData.stringBuilder.length() && calcData.stringBuilder.charAt(x - 1) != '(' && calcData.stringBuilder.charAt(x) == '(' && calcData.stringBuilder.charAt(x - 1) != '/' && calcData.stringBuilder.charAt(x - 1) != '*' && calcData.stringBuilder.charAt(x - 1) != '-' && calcData.stringBuilder.charAt(x - 1) != '+') {
                calcData.stringBuilder.replace(x, x + 1, "*(");
            }
        }
        for (int x = 0; x < calcData.stringBuilder.length() - 1; x++) {
            if (x != 0 && calcData.stringBuilder.charAt(x + 1) != ')' && calcData.stringBuilder.charAt(x) == ')' && calcData.stringBuilder.charAt(x + 1) != '/' && calcData.stringBuilder.charAt(x + 1) != '*' && calcData.stringBuilder.charAt(x + 1) != '-' && calcData.stringBuilder.charAt(x + 1) != '+') {
                calcData.stringBuilder.replace(x, x + 1, ")*");
            }
        }
    }

    //Uzupełnianie pustych nawiasów np. ()() = 0*0
    private void fillEmptyBrackets(int x) {
        if (calcData.stringBuilder.charAt(x) == '(' && calcData.stringBuilder.charAt(x + 1) == ')') {
            calcData.stringBuilder.replace(x, x + 2, "(0)");
        }
    }

    //Sprawdzanie problemu dzielenia przez 0!
    private void divideByZero() throws ArithmeticException{
        if (calcData.stringBuilder.charAt(calcData.stringBuilder.length() - 1) == '0') {
            for (int x = 0; x < calcData.stringBuilder.length(); x++) {
                if (calcData.stringBuilder.charAt(x) == '/' && calcData.stringBuilder.charAt(x + 1) == '0')
                    throw new ArithmeticException();
            }
        } else {
            for (int x = 0; x < calcData.stringBuilder.length(); x++) {
                if (calcData.stringBuilder.charAt(x) == '/' && calcData.stringBuilder.charAt(x + 1) == '0' && calcData.stringBuilder.charAt(x + 2) != '.')
                    throw new ArithmeticException();
            }
        }

    }

}
