package controller;

import logic.CalcLogic;

public class CalcController {
    CalcLogic calcLogic;

    public CalcController(CalcLogic calcLogic) {
        this.calcLogic = calcLogic;
        checkAction();
    }

    private void checkAction() {
        if(calcLogic.getLeftBracket()>0)calcLogic.setBracket(true);
        System.out.println("Wszystko poszło zgodnie z planem!");
        System.out.println("Działanie otrzymane z MainApp: " + calcLogic.stringBuilder);
        System.out.println("Ile nawiasów: " + calcLogic.getLeftBracket());
        System.out.println("Czy jest mnożenie lub dzielenie: " + calcLogic.getMultiply());
        System.out.println("Czy są nawiasy: " + calcLogic.getBracket());
    }

}
