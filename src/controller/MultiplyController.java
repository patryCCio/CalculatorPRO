package controller;

import data.CalcData;

public class MultiplyController implements CalcOperator{

    @Override
    public void createAction(CalcData calcData) {
        do{
            CalcController.createMultiply(calcData, calcData.multiply);
            CalcController.checkStartEnd(calcData, calcData.multiply);

            CalcController.createArray(calcData, calcData.multiply);
            double result = CalcController.getResult(calcData);

            CalcController.saveResult(calcData, result);
            CalcController.checkAction(calcData);
        }while(calcData.isMultiply());
    }


    @Override
    public void repairAction(double result) {
       /* if(start==0){
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }

        */
    }

    public static void specialBracketMultiply(CalcData calcData){

    }
}
