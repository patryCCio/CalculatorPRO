package controller;

import data.CalcData;

public class BracketController implements CalcOperator {

    //3*(-2*3+3)
    CalcData calcData;
    int actualBracket;
    int start, end;

    @Override
    public void createAction(CalcData calcData) {
        this.calcData = calcData;
        do {
            checkBracket();
            CalcController.checkAction(calcData);
        } while (calcData.isBracket());
    }

    private void checkBracket() {

        double result = 0;
        actualBracket = 0;

        int x = 0;

        do{
            howBracket(x);
            x++;
        }while(actualBracket != calcData.getLeftBracket());

        start = x;

        do{
            calcData.stringHelper.append(calcData.stringBuilder.charAt(x));
            x++;
        }while (calcData.stringBuilder.charAt(x) != ')');
        end = x + 1;

        CalcController.checkStringHelper(calcData);
        if(calcData.isMultiply()){
            do{
                bracketMultiplyAction();
                start++;
            }while(calcData.isMultiply());
            CalcController.createArrayStringHelper(calcData);
            result = CalcController.getResult(calcData);
        }else{
            CalcController.createArrayStringHelper(calcData);
            result = CalcController.getResult(calcData);
        }

        repairAction(result);
    }
    //2+(2*2-5) !!
    private void bracketMultiplyAction() {
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

    }

    private void howBracket(int x) {
        if (calcData.stringBuilder.charAt(x) == '(') actualBracket++;
    }


    @Override
    public void repairAction(double result) {

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

    }
}
