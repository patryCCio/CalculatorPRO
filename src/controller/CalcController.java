package controller;

import logic.CalcLogic;

public class CalcController{

    public void checkAction(CalcLogic calcLogic) {
        calcLogic.setMultiply(false);
        calcLogic.setBracket(false);

        do{
            for(int x=0; x<calcLogic.stringBuilder.length(); x++){
                if(calcLogic.stringBuilder.charAt(x)=='(')calcLogic.setBracket(true);
                if(calcLogic.stringBuilder.charAt(x)=='*'||calcLogic.stringBuilder.charAt(x)=='/')calcLogic.setMultiply(true);
            }

            if(calcLogic.getBracket()){
                BracketController bracketController = new BracketController();
                bracketController.centralLoop(calcLogic);
            }else{
                SimpleController simpleController = new SimpleController();
                simpleController.centralLoop(calcLogic);
            }

        }while(calcLogic.getMultiply() || calcLogic.getBracket());


        System.out.println("Wszystko poszło zgodnie z planem!");
        System.out.println("Działanie otrzymane z MainApp: " + calcLogic.stringBuilder);
        System.out.println("Ile nawiasów: " + calcLogic.getLeftBracket());
        System.out.println("Czy jest mnożenie lub dzielenie: " + calcLogic.getMultiply());
        System.out.println("Czy są nawiasy: " + calcLogic.getBracket());
    }

}
