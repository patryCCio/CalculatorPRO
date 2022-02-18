package controller;

import data.CalcData;

public class MultiplyController implements CalcOperator{
    CalcData calcData;
    private int start = 0;
    private int end = 0;

    @Override
    public void createAction(CalcData calcData) {
        this.calcData = calcData;
        do{
            multiplyAction();
            CalcController.checkAction(calcData);
        }while(calcData.isMultiply());
    }

    private void multiplyAction() {
        int x=0;
        System.out.println(calcData.stringBuilder);
        while(calcData.stringBuilder.charAt(x)!='*' && calcData.stringBuilder.charAt(x)!='/'){
            x++;
        }

        while(x>=0 && calcData.stringBuilder.charAt(x) != '+' && calcData.stringBuilder.charAt(x) != '-'){
            x--;
        }

        if(x!=0 && calcData.stringBuilder.charAt(0)!='-')x++;
        start = x;

        do{
            calcData.stringHelper.append(calcData.stringBuilder.charAt(x));
            x++;
        }while(x< calcData.stringBuilder.length() && calcData.stringBuilder.charAt(x)!='+' && calcData.stringBuilder.charAt(x) != '-');
        end = x;

        System.out.println("String helper: " + calcData.stringHelper);
        createMultiplyAction();
    }

    private void createMultiplyAction() {
        if(calcData.stringHelper.charAt(0)!='-') calcData.stringHelper.replace(0, 0, "+");
        for(int x = 0; x< calcData.stringHelper.length(); x++){
            if(calcData.stringHelper.charAt(x)=='/'|| calcData.stringHelper.charAt(x)=='+'|| calcData.stringHelper.charAt(x)=='-'|| calcData.stringHelper.charAt(x)=='*'){
                calcData.characters.add(calcData.stringHelper.charAt(x));
            }else{
                do{
                    calcData.helper = calcData.helper + calcData.stringHelper.charAt(x);
                    x++;
                }while(x< calcData.stringHelper.length() && calcData.stringHelper.charAt(x) != '*' && calcData.stringHelper.charAt(x) != '/');
                calcData.actions.add(Double.valueOf(calcData.helper));
                calcData.helper = "";
                x--;
            }
        }

        calcData.stringHelper.delete(0, calcData.stringHelper.length());

        double result = CalcController.getResult(calcData);
        repairAction(result);
    }

    @Override
    public void repairAction(double result) {
        if(start==0){
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }
    }

    public static void specialBracketMultiply(CalcData calcData){

    }
}
