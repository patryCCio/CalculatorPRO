package controller;

import data.CalcData;

public class CalcController{

    CalcData calcData;

    public void doAction(CalcData calcData) {
        calcData.helper = "";
        this.calcData = calcData;
        do{
            checkAction(calcData);
            System.out.println("Mnożenie dzielenie: " + calcData.isMultiply());
            System.out.println("Nawiasy: " + calcData.isBracket());
            System.out.println("Specjalne nawiasy: " + calcData.isSpecialBracket());
        }while(calcData.isSpecialBracket() || calcData.isBracket() || calcData.isMultiply());
        System.out.println("Przeszedłem dalej!");

        createArray();
    }

    private void createArray() {
        if(calcData.stringBuilder.charAt(0)!='-'){
            calcData.stringBuilder.replace(0, 0, "+");
        }

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            if(calcData.stringBuilder.charAt(x) == '+' || calcData.stringBuilder.charAt(x) == '-'){
                calcData.characters.add(calcData.stringBuilder.charAt(x));
            }else{
                do{
                    calcData.helper = calcData.helper + calcData.stringBuilder.charAt(x);
                    x++;
                }while(x < calcData.stringBuilder.length() && calcData.stringBuilder.charAt(x) != '-' && calcData.stringBuilder.charAt(x) != '+');
            calcData.actions.add(Double.valueOf(calcData.helper));
            calcData.helper = "";
            x--;
            }
        }

        calcData.stringBuilder.delete(0, calcData.stringBuilder.length());
        calcData.result = getResult(calcData);
        System.out.println(calcData.toString());
    }

    public static void checkAction(CalcData calcData){
        calcData.setMultiply(false);
        calcData.setBracket(false);
        calcData.setSpecialBracket(false);

        for(int x=2; x<calcData.stringBuilder.length(); x++){
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='*')calcData.setSpecialBracket(true);
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='/')calcData.setSpecialBracket(true);
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='-')calcData.setSpecialBracket(true);
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='+')calcData.setSpecialBracket(true);
        }

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            if(calcData.stringBuilder.charAt(x)=='/'||calcData.stringBuilder.charAt(x)=='*')calcData.setMultiply(true);
        }

        if(calcData.getLeftBracket()!=0){
            calcData.setBracket(true);
        }
    }

    public static double getResult(CalcData calcData) {
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
    }

}
