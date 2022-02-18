package data;

import java.util.ArrayList;

public class CalcData {

    public ArrayList<Double> actions = new ArrayList<>();
    public ArrayList<Character> characters = new ArrayList<>();

    public StringBuilder stringBuilder = new StringBuilder();
    public StringBuilder multiply = new StringBuilder();
    public StringBuilder bracket = new StringBuilder();
    public StringBuilder specialBracket = new StringBuilder();
    public StringBuilder normal = new StringBuilder();

    public StringBuilder stringHelper = new StringBuilder();
    public StringBuilder stringSpecialHelper = new StringBuilder();

    private boolean isMultiply;
    private boolean isBracket;
    private boolean isSpecialBracket;
    public static int howSpecialBracket = 0;

    public double result;

    private int leftBracket;
    private int rightBracket;

    public String helper;

    public boolean isMultiply() {
        return isMultiply;
    }

    public void setMultiply(boolean multiply) {
        isMultiply = multiply;
    }

    public boolean isBracket() {
        return isBracket;
    }

    public void setBracket(boolean bracket) {
        isBracket = bracket;
    }

    public boolean isSpecialBracket() {
        return isSpecialBracket;
    }

    public void setSpecialBracket(boolean specialBracket) {
        isSpecialBracket = specialBracket;
    }

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

    @Override
    public String toString() {
        return "Wynik: " + result;
    }
}
