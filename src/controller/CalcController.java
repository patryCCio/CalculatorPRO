package controller;

import logic.CalcLogic;

public class CalcController {
    CalcLogic calcLogic;

    public CalcController(CalcLogic calcLogic) {
        this.calcLogic = calcLogic;
        checkAction();
    }

    private void checkAction() {
        calcLogic.setMultiply(false);
        calcLogic.setBracket(false);

        for(int x=0; x<calcLogic.stringBuilder.length(); x++){
            if(calcLogic.stringBuilder.charAt(x)=='(')calcLogic.setBracket(true);
            if(calcLogic.stringBuilder.charAt(x)=='*'||calcLogic.stringBuilder.charAt(x)=='/')calcLogic.setMultiply(true);
        }
        if(calcLogic.getLeftBracket()>0)calcLogic.setBracket(true);
        System.out.println("Wszystko poszło zgodnie z planem!");
        System.out.println("Działanie otrzymane z MainApp: " + calcLogic.stringBuilder);
        System.out.println("Ile nawiasów: " + calcLogic.getLeftBracket());
        System.out.println("Czy jest mnożenie lub dzielenie: " + calcLogic.getMultiply());
        System.out.println("Czy są nawiasy: " + calcLogic.getBracket());
    }

}
