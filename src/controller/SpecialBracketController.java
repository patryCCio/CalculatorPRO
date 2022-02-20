package controller;

import data.CalcData;

public class SpecialBracketController implements CalcOperator {

    @Override
    public void createAction(CalcData calcData) {
        do {
            int where = CalcController.checkWhereSpecial(calcData, calcData.stringBuilder);
            CalcController.createSpecialBracket(calcData, calcData.specialBracket, where);
            CalcController.checkStartEnd(calcData, calcData.specialBracket);

            if(calcData.isReverse()){
                reverseAction(calcData, calcData.specialBracket);
                calcData.actions.clear();
                calcData.characters.clear();

                CalcController.createArray(calcData, calcData.specialBracket);
                double result = CalcController.getResult(calcData);
                CalcController.saveResult(calcData, result);
                CalcController.checkAction(calcData);
            }
            CalcController.deleteBuilder(calcData.specialBracket);
            CalcController.checkAction(calcData);

        } while (calcData.isSpecialBracket());
    }

    private void reverseAction(CalcData calcData, StringBuilder sb){

        calcData.start = calcData.start - 2;
        sb.append(calcData.stringBuilder.charAt(calcData.start));


        while(calcData.start >= 0 && calcData.stringBuilder.charAt(calcData.start) != '(' && calcData.stringBuilder.charAt(calcData.start) != '-' && calcData.stringBuilder.charAt(calcData.start) != '+'){
            calcData.start--;
        }

        calcData.start++;
        int x = calcData.start;

        while(calcData.stringBuilder.charAt(x)!='('){
            sb.append(calcData.stringBuilder.charAt(x));
            x++;
        }
        sb.delete(sb.length()-1, sb.length());

        calcData.end++;
    }

}
