package controller;

import data.CalcData;

public class SpecialBracketController implements CalcOperator{

    CalcData calcData;
    @Override
    public void createAction(CalcData calcData){
        this.calcData = calcData;
        do{
            CalcController.checkAction(calcData);
            checkSpecialBracket();
        }while(calcData.isSpecialBracket());
    }

    private void checkSpecialBracket() {

    }

    @Override
    public void repairAction(double result) {

    }
}
