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

        }else{
            CalcController.createArrayStringHelper(calcData);
            result = CalcController.getResult(calcData);
        }

        if(result>0){
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            System.out.println("Ujemny result");
        }

        if(calcData.stringBuilder.charAt(start-1)=='*'){

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