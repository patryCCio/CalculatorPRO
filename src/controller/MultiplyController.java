package controller;

import data.CalcData;

public class MultiplyController implements CalcOperator{
    CalcData calcData;
    private int start = 0;
    private int end = 0;

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
        createAction(calcData);
    }

    @Override
    public void createAction(CalcData calcData) {
       /* if(calcData.stringHelper.charAt(0)!='-') calcData.stringHelper.replace(0, 0, "+");
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

        CalcController calcController = new CalcController();
        double result = calcController.getResult();
        repairAction(result);
    }

    private double getResult() {
        double result = 0;
        if(calcData.actions.size() != calcData.characters.size()){
            System.out.println(calcData.actions);
            System.out.println(calcData.characters);
            System.out.println("Błąd!");
        }
        double a = 0;
        char operator = '0';
        for(int x = 0; x< calcData.actions.size(); x++){
            a = calcData.actions.get(x);
            operator = calcData.characters.get(x);

            if(operator == '-'){
                result = result - a;
            }else if(operator == '+'){
                result = result + a;
            }else if(operator == '/'){
                result = result / a;
            }else if(operator == '*'){
                result = result * a;
            }else{
                System.out.println("Coś poszło nie tak!");
            }
        }
        calcData.actions.clear();
        calcData.characters.clear();
        return result;

        */
    }

    @Override
    public void repairAction(double result) {
        if(start==0){
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            calcData.stringBuilder.replace(start, end, String.valueOf(result));
        }
        calcData.setMultiply(false);
    }
}
