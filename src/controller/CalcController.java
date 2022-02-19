package controller;

import data.CalcData;

public class CalcController{


    public void doAction(CalcData calcData) {
        calcData.helper = "";
        checkAction(calcData);
        do{
            if(calcData.isSpecialBracket()){
                SpecialBracketController specialBracketController = new SpecialBracketController();
                specialBracketController.createAction(calcData);
            }
            if(calcData.isBracket()){
                BracketController bracketController = new BracketController();
                bracketController.createAction(calcData);
            }
            if(calcData.isMultiply()){
                MultiplyController multiplyController = new MultiplyController();
                multiplyController.createAction(calcData);
            }
            if(calcData.isNormal()){
                NormalController normalController = new NormalController();
                normalController.createAction(calcData);
            }
        }while(!calcData.isNormal());

        printResult(calcData);
    }

    public static void createMultiply(CalcData calcData, StringBuilder sb){
        int x=0;

        while(calcData.stringBuilder.charAt(x)!='*' && calcData.stringBuilder.charAt(x)!='/'){
            x++;
        }

        while(x>=0 && calcData.stringBuilder.charAt(x) != '+' && calcData.stringBuilder.charAt(x) != '-'){
            x--;
        }

        if(x!=0 && calcData.stringBuilder.charAt(0)!='-')x++;

        do{
            sb.append(calcData.stringBuilder.charAt(x));
            x++;
        }while(x< calcData.stringBuilder.length() && calcData.stringBuilder.charAt(x)!='+' && calcData.stringBuilder.charAt(x) != '-');

    }

    public static void createArray(CalcData calcData, StringBuilder sb){
        calcData.helper = "";

        if(sb.charAt(0)!='-' && sb.charAt(0) != '+') sb.replace(0, 0, "+");

        for(int x=0; x<sb.length(); x++){
            if(sb.charAt(x) == '+' || sb.charAt(x) == '-' || sb.charAt(x) == '/' || sb.charAt(x) == '*'){
                calcData.characters.add(sb.charAt(x));
            }else{
                do{
                    calcData.helper = calcData.helper + sb.charAt(x);
                    x++;
                }while(x < sb.length() && sb.charAt(x) != '-' && sb.charAt(x) != '+' && sb.charAt(x) != '*' && sb.charAt(x) != '/');
                calcData.actions.add(Double.valueOf(calcData.helper));
                calcData.helper = "";
                x--;
            }
        }

        sb.delete(0, sb.length());
    }

    public static void saveResult(CalcData calcData, double result){
        if(result >= 0){
            calcData.stringBuilder.replace(calcData.start, calcData.end, String.valueOf(result));
        }else{
            if(calcData.start == 0){
                calcData.stringBuilder.replace(calcData.start, calcData.end, String.valueOf(result));
            }else{

            }
        }
    }

    public static void printResult(CalcData calcData){
        System.out.println(calcData.toString() + "\n");
    }

    public static void createActionMultiply(CalcData calcData, int start, int end){
        int x=0;

        while(calcData.stringHelper.charAt(x)!='*' && calcData.stringHelper.charAt(x)!='/'){
            x++;
        }

        while(x>=0 && calcData.stringHelper.charAt(x) != '+' && calcData.stringHelper.charAt(x) != '-'){
            x--;
        }
//3-(-3*2+3)
        if(x!=0) {
            start = x;
            x++;
        }
        else if (calcData.stringHelper.charAt(0)=='-'){
            start = x;
            calcData.stringSpecialHelper.append(calcData.stringHelper.charAt(x));
            x++;
        }else{
            start = x;
        }


        do{
            calcData.stringSpecialHelper.append(calcData.stringHelper.charAt(x));
            x++;
        }while(x < calcData.stringHelper.length() && calcData.stringHelper.charAt(x)!='+' && calcData.stringHelper.charAt(x) != '-');
        end = x;

        System.out.println("String special helper: " + calcData.stringSpecialHelper);
        createMultiplyAction(calcData, start, end);
    }

    public static void checkStartEnd(CalcData calcData, StringBuilder sb){
        int start;
        int end;

        calcData.start = 0;
        calcData.end = 0;

        start = calcData.stringBuilder.indexOf(sb.toString());
        end = start + sb.length();

        calcData.start = start;
        calcData.end = end;
    }

    public static void createMultiplyAction(CalcData calcData, int start, int end) {
        calcData.helper = "";
        int startSpecial=0;
        int endSpecial=0;

        if(calcData.stringSpecialHelper.charAt(0)!='-') calcData.stringSpecialHelper.replace(0, 0, "+");
        for(int x = 0; x< calcData.stringSpecialHelper.length(); x++){
            if(calcData.stringSpecialHelper.charAt(x)=='/'|| calcData.stringSpecialHelper.charAt(x)=='+'|| calcData.stringSpecialHelper.charAt(x)=='-'|| calcData.stringSpecialHelper.charAt(x)=='*'){
                calcData.characters.add(calcData.stringSpecialHelper.charAt(x));
            }else{
                do{
                    calcData.helper = calcData.helper + calcData.stringSpecialHelper.charAt(x);
                    x++;
                }while(x< calcData.stringSpecialHelper.length() && calcData.stringSpecialHelper.charAt(x) != '*' && calcData.stringSpecialHelper.charAt(x) != '/');
                calcData.actions.add(Double.valueOf(calcData.helper));
                calcData.helper = "";
                x--;
            }
        }
        if(calcData.stringSpecialHelper.charAt(0)=='+'){
            calcData.stringSpecialHelper.delete(0, 1);
            int length = calcData.stringSpecialHelper.length();
            startSpecial = calcData.stringBuilder.indexOf(String.valueOf(calcData.stringSpecialHelper));
            endSpecial = startSpecial + length;
            startSpecial--;
            start--;
            calcData.stringSpecialHelper.replace(0, 0, "+");
        }else{
            int length = calcData.stringSpecialHelper.length();
            startSpecial = calcData.stringBuilder.indexOf(String.valueOf(calcData.stringSpecialHelper));
            endSpecial = startSpecial + length;
        }

        calcData.stringSpecialHelper.delete(0, calcData.stringSpecialHelper.length());
        double result = CalcController.getResult(calcData);
        if(result>=0){
            calcData.stringBuilder.replace(startSpecial+1, endSpecial, String.valueOf(result));
            calcData.stringHelper.replace(start+1, end, String.valueOf(result));
        }else{
            calcData.stringBuilder.replace(startSpecial, endSpecial, String.valueOf(result));
            calcData.stringHelper.replace(start, end, String.valueOf(result));
        }
        checkStringHelper(calcData);
    }


    public static void createArrayStringHelper(CalcData calcData){
        calcData.helper = "";
        if(calcData.stringHelper.charAt(0)!='-' && calcData.stringHelper.charAt(0) != '+'){
            calcData.stringHelper.replace(0, 0, "+");
        }

        for(int x=0; x<calcData.stringHelper.length(); x++){
            if(calcData.stringHelper.charAt(x) == '+' || calcData.stringHelper.charAt(x) == '-' || calcData.stringHelper.charAt(x) == '*' || calcData.stringHelper.charAt(x) == '/'){
                calcData.characters.add(calcData.stringHelper.charAt(x));
            }else{
                do{
                    calcData.helper = calcData.helper + calcData.stringHelper.charAt(x);
                    x++;
                }while(x < calcData.stringHelper.length() && calcData.stringHelper.charAt(x) != '-' && calcData.stringHelper.charAt(x) != '+' && calcData.stringHelper.charAt(x) != '*' && calcData.stringHelper.charAt(x) != '/');
                calcData.actions.add(Double.valueOf(calcData.helper));
                calcData.helper = "";
                x--;
            }
        }

        calcData.stringHelper.delete(0, calcData.stringHelper.length());
    }

    public static void checkStringHelper(CalcData calcData){
        calcData.setMultiply(false);
        for(int x=0; x<calcData.stringHelper.length(); x++){
            if(calcData.stringHelper.charAt(x) == '*' || calcData.stringHelper.charAt(x) == '/') calcData.setMultiply(true);
        }
    }

    public static void checkAction(CalcData calcData){
        CalcData.howSpecialBracket = 0;
        calcData.setLeftBracket(0);
        calcData.setRightBracket(0);

        calcData.setMultiply(false);
        calcData.setBracket(false);
        calcData.setSpecialBracket(false);
        calcData.setNormal(false);

        for(int x=2; x<calcData.stringBuilder.length(); x++){
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='*'){
                calcData.setSpecialBracket(true);
                CalcData.howSpecialBracket++;
            }
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='/'){
                calcData.setSpecialBracket(true);
                CalcData.howSpecialBracket++;
            }
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='-'){
                calcData.setSpecialBracket(true);
                CalcData.howSpecialBracket++;
            }
            if(calcData.stringBuilder.charAt(x)=='-'&&calcData.stringBuilder.charAt(x-1)=='('&&calcData.stringBuilder.charAt(x-2)=='+'){
                calcData.setSpecialBracket(true);
                CalcData.howSpecialBracket++;
            }
            if(calcData.stringBuilder.charAt(x)=='('){
                calcData.setLeftBracket(calcData.getLeftBracket()+1);
                calcData.setBracket(true);
            }
            if(calcData.stringBuilder.charAt(x)==')'){
                calcData.setRightBracket(calcData.getRightBracket()+1);
                calcData.setBracket(true);
            }
        }

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            if(calcData.stringBuilder.charAt(x)=='/'||calcData.stringBuilder.charAt(x)=='*')calcData.setMultiply(true);
        }

        if(calcData.getLeftBracket()!=0){
            calcData.setBracket(true);
        }

        if(!calcData.isBracket()&&!calcData.isMultiply()&&!calcData.isSpecialBracket())calcData.setNormal(true);
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
