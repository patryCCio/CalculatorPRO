package controller;

import logic.CalcLogic;

public class CalcController {
    CalcLogic calcLogic;

    public CalcController(CalcLogic calcLogic) {
        this.calcLogic = calcLogic;
        checkAction();
    }

    private void checkAction(){
        System.out.println("Wszystko poszło zgodnie z planem!");
        System.out.println("Działanie otrzymane z MainApp: " + calcLogic.stringBuilder);
    }

}
