package controller;

import logic.CalcLogic;

public class BracketController implements CalcOperator {

    CalcLogic calcLogic;

    @Override
    public void centralLoop(CalcLogic calcLogic) {
        this.calcLogic = calcLogic;
        System.out.println("Z nawiasami");
    }

    @Override
    public void createAction() {

    }

    @Override
    public void repairAction(double result) {

    }
}
