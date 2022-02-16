package logic;

import java.util.ArrayList;

public class CalcLogic {

    private ArrayList<Double> actions = new ArrayList<>();
    private ArrayList<Character> characters = new ArrayList<>();

    StringBuilder stringBuilder = new StringBuilder();

    private Boolean isDivide;
    private Boolean isMultiply;
    private Boolean isBracket;

    private String helper;

    public Boolean getDivide() {
        return isDivide;
    }

    public void setDivide(Boolean divide) {
        isDivide = divide;
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

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public ArrayList<Double> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Double> actions) {
        this.actions = actions;
    }
}
