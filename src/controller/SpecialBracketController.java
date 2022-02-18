package controller;

import data.CalcData;

public class SpecialBracketController implements CalcOperator {

    CalcData calcData;
    int actualSpecialBracket = 0;
    int start, end;

    @Override
    public void createAction(CalcData calcData) {
        this.calcData = calcData;
        do {
            checkSpecialBracket();
            CalcController.checkAction(calcData);
        } while (calcData.isSpecialBracket());
    }


    //2*(-3+2/3) 2*(-3+23+2) 2*(-3+23+2)+(-3+23-5)
    private void checkSpecialBracket() {
        double result = 0;
        actualSpecialBracket = 0;
        int x = 2;
        do {
            specialBracket(x);
            x++;
        } while (actualSpecialBracket != CalcData.howSpecialBracket);
        x--;
        start = x-1;
        do{
            calcData.stringHelper.append(calcData.stringBuilder.charAt(x));
            x++;
        }while(calcData.stringBuilder.charAt(x) != ')');
        end = x+1;

        CalcController.checkStringHelper(calcData);
        if(calcData.isMultiply()){
            do{
                CalcController.createActionMultiply(calcData);
            }while(calcData.isMultiply());
            CalcController.createArrayStringHelper(calcData);
            result = CalcController.getResult(calcData);
        }else{
            CalcController.createArrayStringHelper(calcData);
            result = CalcController.getResult(calcData);
        }

        if(result>0){
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            if(calcData.stringBuilder.charAt(start-1)=='-'){
                result = result - 2*result;
                calcData.stringBuilder.replace(start-1, end, "+" + result);
            }else if(calcData.stringBuilder.charAt(start-1)=='+'){
                calcData.stringBuilder.replace(start-1, end, String.valueOf(result));
            }else{
                createActionMultiply(result, start, end);
            }
        }

        CalcController.checkAction(calcData);

    }

    //3+3*(-3*2)

    private void createActionMultiply(double result, int start, int end) {
        calcData.helper = "";
        calcData.stringHelper.append(result);
        calcData.stringHelper.append(calcData.stringBuilder.charAt(start-1));
        start--;
        while(start >= 0 && calcData.stringBuilder.charAt(start) != '(' && calcData.stringBuilder.charAt(start) != '-' && calcData.stringBuilder.charAt(start) != '+'){
            start--;
        }
        start++;
        int x = start;
        while(calcData.stringBuilder.charAt(x)!= '('){
            calcData.stringHelper.append(calcData.stringBuilder.charAt(x));
            x++;
        }
        calcData.stringHelper.delete(calcData.stringHelper.length()-1, calcData.stringHelper.length());

        CalcController.createArrayStringHelper(calcData);
        result = CalcController.getResult(calcData);

        if(result>=0){
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            if(calcData.stringBuilder.charAt(start-1)=='+'){
                calcData.stringBuilder.replace(start-1, end, String.valueOf(result));
            }else{
                result -= 2*result;
                calcData.stringBuilder.replace(start-1, end, "+" + result);
            }
        }

    }

    private void specialBracket(int x) {
        if (calcData.stringBuilder.charAt(x) == '-' && calcData.stringBuilder.charAt(x - 1) == '(' && calcData.stringBuilder.charAt(x - 2) == '*') {
            ++actualSpecialBracket;
        }
        if (calcData.stringBuilder.charAt(x) == '-' && calcData.stringBuilder.charAt(x - 1) == '(' && calcData.stringBuilder.charAt(x - 2) == '/') {
            ++actualSpecialBracket;
        }
        if (calcData.stringBuilder.charAt(x) == '-' && calcData.stringBuilder.charAt(x - 1) == '(' && calcData.stringBuilder.charAt(x - 2) == '-') {
            ++actualSpecialBracket;
        }
        if (calcData.stringBuilder.charAt(x) == '-' && calcData.stringBuilder.charAt(x - 1) == '(' && calcData.stringBuilder.charAt(x - 2) == '+') {
            ++actualSpecialBracket;
        }
    }

    @Override
    public void repairAction(double result) {

    }
}
