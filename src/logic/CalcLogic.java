package logic;

import java.util.ArrayList;

public class CalcLogic {

    public ArrayList<Double> actions = new ArrayList<>();
    public ArrayList<Character> characters = new ArrayList<>();

    public StringBuilder stringBuilder = new StringBuilder();
    public StringBuilder stringHelper = new StringBuilder();

    private Boolean isMultiply;
    private Boolean isBracket;

    private int leftBracket;
    private int rightBracket;

    public String helper;

    public int getLeftBracket() {
        return leftBracket;
    }

    public void setLeftBracket(int leftBracket) {
        this.leftBracket = leftBracket;
    }

    public int getRightBracket() {
        return rightBracket;
    }

    public void setRightBracket(int rightBracket) {
        this.rightBracket = rightBracket;
    }

    public Boolean getMultiply() {
        return isMultiply;
    }

    public void setMultiply(Boolean multiply) {
        isMultiply = multiply;
    }

    public Boolean getBracket() {
        return isBracket;
    }

    public void setBracket(Boolean bracket) {
        isBracket = bracket;
    }

    private static int brackets = 0;

    public static int getBrackets() {
        return brackets;
    }

    public static void setBrackets(int brackets) {
        CalcLogic.brackets = brackets;
    }

}
