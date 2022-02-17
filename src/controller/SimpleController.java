package controller;

import logic.CalcLogic;

public class SimpleController implements CalcOperator {

    CalcLogic calcLogic;
    private int start = 0;
    private int end = 0;

    @Override
    public void centralLoop(CalcLogic calcLogic) {
        start = 0;
        end = 0;
        this.calcLogic = calcLogic;
        if(calcLogic.getMultiply()){
            multiplyAction();
        }
    }

    private void multiplyAction() {
        int x=0;
        System.out.println(calcLogic.stringBuilder);
        while(calcLogic.stringBuilder.charAt(x)!='*' && calcLogic.stringBuilder.charAt(x)!='/'){
            x++;
        }

        while(x>=0 && calcLogic.stringBuilder.charAt(x) != '+' && calcLogic.stringBuilder.charAt(x) != '-'){
            x--;
        }
        start = x+1;
        if(x!=0)x++;

        do{
            calcLogic.stringHelper.append(calcLogic.stringBuilder.charAt(x));
            x++;
        }while(x<calcLogic.stringBuilder.length() && calcLogic.stringBuilder.charAt(x)!='+' && calcLogic.stringBuilder.charAt(x) != '-');
        end = x;

        System.out.println("String helper: " + calcLogic.stringHelper);
        createAction();
    }

    @Override
    public void createAction() {
        if(calcLogic.stringHelper.charAt(0)!='-')calcLogic.stringHelper.replace(0, 0, "+");
        for(int x=0; x<calcLogic.stringHelper.length(); x++){
            if(calcLogic.stringHelper.charAt(x)=='/'||calcLogic.stringHelper.charAt(x)=='+'||calcLogic.stringHelper.charAt(x)=='-'||calcLogic.stringHelper.charAt(x)=='*'){
                calcLogic.characters.add(calcLogic.stringHelper.charAt(x));
            }else{
                do{
                    calcLogic.helper = calcLogic.helper + calcLogic.stringHelper.charAt(x);
                    x++;
                }while(x<calcLogic.stringHelper.length() && calcLogic.stringHelper.charAt(x) != '*' && calcLogic.stringHelper.charAt(x) != '/');
                calcLogic.actions.add(Double.valueOf(calcLogic.helper));
                calcLogic.helper = "";
                x--;
            }
        }

        calcLogic.stringHelper.delete(0, calcLogic.stringHelper.length());

        double result = getResult();
        repairAction(result);
        System.out.println(getResult());
    }

    private double getResult() {
        double result = 0;
        if(calcLogic.actions.size() != calcLogic.characters.size()){
            System.out.println(calcLogic.actions);
            System.out.println(calcLogic.characters);
            System.out.println("Błąd!");
        }
        double a = 0;
        char operator = '0';
        for(int x=0; x<calcLogic.actions.size(); x++){
            a = calcLogic.actions.get(x);
            operator = calcLogic.characters.get(x);

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
        calcLogic.actions.clear();
        calcLogic.characters.clear();
        return result;
    }

    @Override
    public void repairAction(double result) {
        if(start==0){
            calcLogic.stringBuilder.replace(start, end, String.valueOf(result));
        }else{
            if(result<0){

            }else{
                calcLogic.stringBuilder.replace(start, end, String.valueOf(result));
            }
        }
        calcLogic.setMultiply(false);
    }
}
