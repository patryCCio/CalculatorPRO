package controller;

import logic.CalcLogic;

public class SimpleController implements CalcOperator {

    CalcLogic calcLogic;

    @Override
    public void centralLoop(CalcLogic calcLogic) {
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

        if(x!=0)x++;

        do{
            calcLogic.stringHelper.append(calcLogic.stringBuilder.charAt(x));
            x++;
        }while(x<calcLogic.stringBuilder.length() && calcLogic.stringBuilder.charAt(x)!='+' && calcLogic.stringBuilder.charAt(x) != '-');


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

        getResult();
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

        }
        return 0;
    }

    @Override
    public void repairAction() {

    }
}
