package controller;

import data.CalcData;

public class NormalController implements CalcOperator{


    @Override
    public void createAction(CalcData calcData) {

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            calcData.normal.append(calcData.stringBuilder.charAt(x));
        }

        CalcController.checkStartEnd(calcData, calcData.normal);
        CalcController.createArray(calcData, calcData.normal);
        CalcController.deleteBuilder(calcData.normal);
        calcData.result = CalcController.getResult(calcData);

        calcData.stringBuilder.delete(calcData.start, calcData.end);

    }

    @Override
    public void repairAction(double result) {

    }
}
