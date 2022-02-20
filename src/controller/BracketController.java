package controller;

import data.CalcData;

public class BracketController implements CalcOperator {

    //3*(-2*3+3) -> bad
    CalcData calcData;
    int actualBracket;

    @Override
    public void createAction(CalcData calcData) {
        this.calcData = calcData;
        do {
            CalcController.createBracket(calcData, calcData.bracket, actualBracket);
            CalcController.checkStartEnd(calcData, calcData.bracket);

            CalcController.createArray(calcData, calcData.bracket);
            double result = CalcController.getResult(calcData);

            calcData.start = calcData.start - 1;
            calcData.end = calcData.end + 1;

            CalcController.saveResult(calcData, result);
            CalcController.checkAction(calcData);

            CalcController.deleteBuilder(calcData.bracket);
            if(calcData.isSpecialBracket()){
                int where = CalcController.checkWhereSpecial(calcData, calcData.stringBuilder);
                CalcController.createSpecialBracket(calcData, calcData.stringBuilder, where);
            }

        } while (calcData.isBracket());
    }

    @Override
    public void repairAction(double result) {

    }


}
