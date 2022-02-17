package controller;

import logic.CalcLogic;

public interface CalcOperator {

    void centralLoop(CalcLogic calcLogic);

    void createAction();

    void repairAction();
}
