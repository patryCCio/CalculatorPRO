package controller;

import data.CalcData;

public class SpecialBracketController implements CalcOperator {

    CalcData calcData;
    int actualSpecialBracket = 0;
    int start, end;
    double result;

    @Override
    public void createAction(CalcData calcData) {
        do {
            int where = CalcController.checkWhereSpecial(calcData, calcData.stringBuilder);
            CalcController.createSpecialBracket(calcData, calcData.specialBracket, where);
            CalcController.checkStartEnd(calcData, calcData.specialBracket);
            double result = CalcController.getResult(calcData);
            reverseAction(calcData, calcData.specialBracket, result);

            calcData.start = calcData.start - 1;
            calcData.end = calcData.end + 1;

            CalcController.saveResult(calcData, result);
            CalcController.checkAction(calcData);

            CalcController.deleteBuilder(calcData.bracket);

        } while (calcData.isBracket());
    }


    //2*(-3+2/3) 2*(-3+23+2) 2*(-3+23+2)+(-3+23-5)

    private void reverseAction(CalcData calcData, StringBuilder sb, double result){
        sb.append(calcData.stringBuilder.charAt(calcData.start-2));

        calcData.start--;

        while(calcData.start >= 0 && calcData.stringBuilder.charAt(calcData.start) != '(' && calcData.stringBuilder.charAt(calcData.start) != '-' && calcData.stringBuilder.charAt(calcData.start) != '+'){

        }

    }

    //3+3*(-3*2)
    //3*(-2*3+3) nie dziala

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
            if(start==0){
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
