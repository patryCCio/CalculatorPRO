package controller;

import data.CalcData;

public class NormalController implements CalcOperator{
    CalcData calcData = new CalcData();


    @Override
    public void createAction(CalcData calcData) {
        this.calcData = calcData;
        calcData.start = 0; calcData.end = 0;

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            calcData.normal.append(calcData.stringBuilder.charAt(x));
        }

        CalcController.checkStartEnd(calcData, calcData.normal, calcData.start, calcData.end);
        //CalcController.createNormal(calcData, calcData.normal);

        calcData.stringBuilder.delete(calcData.start, calcData.end);
    }

    @Override
    public void repairAction(double result) {

    }
}
