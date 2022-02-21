package controller;

import data.CalcData;

public class BracketController implements CalcOperator {

    //operacja nawiasów
    @Override
    public void createAction(CalcData calcData) {
        do {
            CalcController.createBracket(calcData, calcData.bracket);
            CalcController.checkStartEnd(calcData, calcData.bracket);

            CalcController.createArray(calcData, calcData.bracket);
            double result = CalcController.getResult(calcData);

            calcData.start = calcData.start - 1;
            calcData.end = calcData.end + 1;

            CalcController.saveResult(calcData, result);
            CalcController.checkAction(calcData);

            CalcController.deleteBuilder(calcData.bracket);
            if(calcData.isSpecialBracket()){
                SpecialBracketController specialBracketController = new SpecialBracketController();
                specialBracketController.createAction(calcData);
            }

        } while (calcData.isBracket());
    }

}
