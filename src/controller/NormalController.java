package controller;

import data.CalcData;

public class NormalController implements CalcOperator{

    //zwykła operacja
    @Override
    public void createAction(CalcData calcData) {

        if(calcData.stringBuilder.charAt(0)=='+')calcData.stringBuilder.delete(0, 1);

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            calcData.normal.append(calcData.stringBuilder.charAt(x));
        }

        CalcController.checkStartEnd(calcData, calcData.normal);
        CalcController.createArray(calcData, calcData.normal);
        CalcController.deleteBuilder(calcData.normal);
        calcData.result = CalcController.getResult(calcData);

        calcData.stringBuilder.delete(calcData.start, calcData.end);

    }

}
