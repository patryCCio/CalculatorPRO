package logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class CalcLogic {

    private ArrayList<Double> actions = new ArrayList<>();
    private ArrayList<Character> characters = new ArrayList<>();

    private String helper;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public ArrayList<Double> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Double> actions) {
        this.actions = actions;
    }
}
