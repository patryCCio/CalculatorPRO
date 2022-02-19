package controller;

import data.CalcData;

public class BracketController implements CalcOperator {

    //3*(-2*3+3)
    CalcData calcData;
    int actualBracket;

    //(3+2)-(3*3-4)
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
            if(calcData.isSpecialBracket())CalcController.createSpecialBracket(calcData);

        } while (calcData.isBracket());
    }

    //2+(2*2-5) !!
    private void bracketMultiplyAction() {
        /*
        int x=0;
        do{
            x++;
        }while(calcData.stringHelper.charAt(x) !='*' && calcData.stringHelper.charAt(x) != '/');

        do{
            x--;
        }while (x>=0 && calcData.stringHelper.charAt(x) != '+' && calcData.stringHelper.charAt(x) != '-');

        if(x!=0 && calcData.stringHelper.charAt(0) != '-' && calcData.stringHelper.charAt(0) != '+')x++;
        start = x;

        do{
            calcData.stringSpecialHelper.append(calcData.stringHelper.charAt(x));
            x++;
        }while(x <calcData.stringHelper.length() && calcData.stringHelper.charAt(x) != '+' && calcData.stringHelper.charAt(x) != '-');
        end = x;

        CalcController.createMultiplyAction(calcData, start, end);

         */

    }

    @Override
    public void repairAction(double result) {
        /*
        if(result>0){
            calcData.stringBuilder.replace(start-1, end, String.valueOf(result));
        }else{
            if(calcData.stringBuilder.charAt(start-1)=='-'){
                result = result - 2*result;
                calcData.stringBuilder.replace(start-1, end, "+" + result);
            }else{
                calcData.stringBuilder.replace(start-1, end, String.valueOf(result));
            }
        }

        CalcController.checkAction(calcData);
 */
    }


}
