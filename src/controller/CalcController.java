package controller;

import data.CalcData;

public class CalcController{


    //sprawdza gdzie jest specjalna część działania: *(-
    public static int checkWhereSpecial(CalcData calcData, StringBuilder stringBuilder) {
        int result = 0;
        for(int x = 2; x<stringBuilder.length(); x++){
            if(stringBuilder.charAt(x)=='-' && stringBuilder.charAt(x-1)=='(' && stringBuilder.charAt(x-2)=='*') result = x;
            if(stringBuilder.charAt(x)=='-' && stringBuilder.charAt(x-1)=='(' && stringBuilder.charAt(x-2)=='/') {
                calcData.setReverseDivide(true);
                result = x;
            }
            if(stringBuilder.charAt(x)=='-' && stringBuilder.charAt(x-1)=='(' && stringBuilder.charAt(x-2)=='-') result = x;
            if(stringBuilder.charAt(x)=='-' && stringBuilder.charAt(x-1)=='(' && stringBuilder.charAt(x-2)=='+') result = x;
        }
        return result;
    }

    public static void checkIsBracket(CalcData calcData, int where) {
        calcData.setOtherSpecial(false);
        if(where>2){
            if(calcData.stringBuilder.charAt(where-3)==')'){
                calcData.setOtherSpecial(true);
            }
        }
    }

    public static void createOtherSpecial(CalcData calcData, StringBuilder sb,int where) {
        int x = where-2;
        double result = 0;

        while(calcData.stringBuilder.charAt(x) != '('){
            x--;
        }

        int from = x;
        x++;

        while(calcData.stringBuilder.charAt(x) != ')'){
            sb.append(calcData.stringBuilder.charAt(x));
            x++;
        }


        checkAction(calcData, sb);

        if(calcData.isMultiply()){
            do{
                createMultiply(sb, calcData.multiply);
                checkStartEnd(calcData, calcData.multiply, from);


                createArray(calcData, calcData.multiply);
                result = getResult(calcData);
                saveResult(calcData, result);

                calcData.multiply.delete(0, 1);

                checkStartEnd(calcData, calcData.multiply, sb);
                saveResult(calcData, result, sb);

                deleteBuilder(calcData.multiply);
                checkAction(calcData, sb);
            }while(calcData.isMultiply());
        }


    }

    //zarządza całym działaniem
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

    //utwórz i przypisz działanie mnożenia
    public static void createMultiply(CalcData calcData, StringBuilder sb){
        int x=0;

        while(calcData.stringBuilder.charAt(x)!='*' && calcData.stringBuilder.charAt(x)!='/'){
            x++;
        }

        while(x>=0 && calcData.stringBuilder.charAt(x) != '+' && calcData.stringBuilder.charAt(x) != '-' && calcData.stringBuilder.charAt(x) != '('){
            x--;
        }

        x++;

        do{
            sb.append(calcData.stringBuilder.charAt(x));
            x++;
        }while(x< calcData.stringBuilder.length() && calcData.stringBuilder.charAt(x)!='+' && calcData.stringBuilder.charAt(x) != '-');

    }

    //utwórz i przypisz działanie mnożenia ale dla konkretnego stringbuildera (gdyż chcemy wykonywać jakieś działanie z nawiasów)
    public static void createMultiply(StringBuilder sb, StringBuilder multiply){
        int x=0;

        while(x < sb.length() && sb.charAt(x)!='*' && sb.charAt(x)!='/'){
            x++;
        }

        x--;

        while(x>=0 && sb.charAt(x) != '+' && sb.charAt(x) != '-' && sb.charAt(x) != '('){
            x--;
        }

        x++;

        do{
            multiply.append(sb.charAt(x));
            x++;
        }while(x< sb.length() && sb.charAt(x)!='+' && sb.charAt(x) != '-');

    }

    //utwórz i przypisz do odpowiedniego stringbuildera działanie zawarte w nawiasie
    public static void createBracket(CalcData calcData, StringBuilder sb) {
        int actualBracket = 0;
        int x = 0;
        double result = 0;

        do{
            if(calcData.stringBuilder.charAt(x) == '(') actualBracket++;
            x++;
        }while(actualBracket != calcData.getLeftBracket());

        int from = x;

        do{
            sb.append(calcData.stringBuilder.charAt(x));
            x++;
        }while (calcData.stringBuilder.charAt(x) != ')');

        checkAction(calcData, sb);

        if(calcData.isMultiply()){
            do{
                createMultiply(calcData.bracket, calcData.multiply);
                checkStartEnd(calcData, calcData.multiply, from);


                createArray(calcData, calcData.multiply);
                result = getResult(calcData);
                saveResult(calcData, result);

                calcData.multiply.delete(0, 1);

                checkStartEnd(calcData, calcData.multiply, sb);
                saveResult(calcData, result, sb);

                deleteBuilder(calcData.multiply);
                checkAction(calcData, sb);
            }while(calcData.isMultiply());
        }

    }

    //utwórz i przypisz do odpowiedniego stringbuildera działanie specjalne zawarte w nawiasie
    public static void createSpecialBracket(CalcData calcData, StringBuilder sb, int where) {

        calcData.setReverse(false);
        int x = where;
        double result = 0;

        while(calcData.stringBuilder.charAt(x) != ')'){
            sb.append(calcData.stringBuilder.charAt(x));
            x++;
        }

        checkAction(calcData, calcData.specialBracket);

        if(calcData.isMultiply()){
            do{
                createMultiplySpecial(sb, calcData.multiply);
                checkStartEnd(calcData, calcData.multiply);


                createArray(calcData, calcData.multiply);
                result = getResult(calcData);
                saveResult(calcData, result);
                if(calcData.multiply.charAt(0)=='+')calcData.multiply.delete(0, 0);
                checkStartEnd(calcData, calcData.multiply, sb);
                saveResultSpecial(calcData, result, sb);

                deleteBuilder(calcData.multiply);
                checkAction(calcData, sb);
            }while(calcData.isMultiply());
        }
        createArray(calcData, sb);
        result = CalcController.getResult(calcData);
        checkStartEnd(calcData, sb, where);
        saveResult(calcData, result);
        sb.replace(0, sb.length(), String.valueOf(result));

        if(result<0)calcData.setReverse(true);
        else{
            checkStartEnd(calcData, sb);
            calcData.stringBuilder.replace(calcData.start-1, calcData.end+1, String.valueOf(result));
        }

        checkAction(calcData);
    }

    //utwórz i przypisz działanie mnożenia dla konkretnego stringbuildera
    private static void createMultiplySpecial(StringBuilder sb, StringBuilder multiply) {
        int x=0;

        while(x < sb.length() && sb.charAt(x)!='*' && sb.charAt(x)!='/'){
            x++;
        }

        x--;

        while(x>=0 && sb.charAt(x) != '+' && sb.charAt(x) != '-' && sb.charAt(x) != '('){
            x--;
        }

        if(sb.charAt(0)!='-')x++;


        do{
            multiply.append(sb.charAt(x));
            x++;
        }while(x< sb.length() && sb.charAt(x)!='+' && sb.charAt(x) != '-');

        if(multiply.charAt(0)=='+')multiply.delete(0, 1);
    }

    //dodatkowe zabezpieczenie, aby nie wyszukało nam takiego samego działania które występuje wcześniej!
    public static void checkStartEnd(CalcData calcData, StringBuilder sb, int from){

        calcData.start = calcData.stringBuilder.indexOf(sb.toString(), from);
        calcData.end = calcData.start + sb.length();

    }

    //sprawdź gdzie jest dany urywek działania w konkretnym stringbuilderze
    public static void checkStartEnd(CalcData calcData, StringBuilder multiply, StringBuilder bracket){

        calcData.start = bracket.indexOf(multiply.toString());
        calcData.end = calcData.start + multiply.length();

    }

    //sprawdź gdzie jest dany urywek działania w głównym stringbuilderze
    public static void checkStartEnd(CalcData calcData, StringBuilder sb){

        calcData.start = calcData.stringBuilder.indexOf(sb.toString());
        calcData.end = calcData.start + sb.length();

    }

    //utwórz 2 arraylisty, żeby mógł wykonać działanie
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
        if(calcData.isReverseDivide()&&calcData.actions.size()>1){
            calcData.setReverseDivide(false);
            double helper1 = calcData.actions.get(0);
            double helper2 = calcData.actions.get(1);
            calcData.actions.clear();
            calcData.actions.add(helper2);
            calcData.actions.add(helper1);
        }
    }

    //wyczyść konkretnego stringbuildera
    public static void deleteBuilder(StringBuilder sb){
        sb.delete(0, sb.length());
    }

    //zapisz rezultat do stringbuildera
    public static void saveResult(CalcData calcData, double result){
        //if(calcData.start > 0&&calcData.stringBuilder.charAt(calcData.start-1)!='-'&&calcData.stringBuilder.charAt(calcData.start-1)!='+'&&calcData.stringBuilder.charAt(calcData.start-1)!='/'&&calcData.stringBuilder.charAt(calcData.start-1)!='*')calcData.start++;


        if(result >= 0){
            calcData.stringBuilder.replace(calcData.start, calcData.end, String.valueOf(result));
        }else{
            if(calcData.start == 0){
                calcData.stringBuilder.replace(calcData.start, calcData.end, String.valueOf(result));
            }else{
                if(calcData.stringBuilder.charAt(calcData.start-1)=='-'){
                    result = result - 2*result;
                    calcData.stringBuilder.replace(calcData.start - 1, calcData.end, "+" + result);
                }else if(calcData.stringBuilder.charAt(calcData.start-1)=='+'){
                    calcData.stringBuilder.replace(calcData.start - 1, calcData.end, String.valueOf(result));
                }else if(calcData.stringBuilder.charAt(calcData.start-1) == '*' || calcData.stringBuilder.charAt(calcData.start-1) == '/'){
                    calcData.stringBuilder.replace(calcData.start, calcData.end, "(" + result + ")");
                }else if(calcData.stringBuilder.charAt(calcData.start - 1) == '('){
                    calcData.stringBuilder.replace(calcData.start, calcData.end, String.valueOf(result));
                }
            }
        }
    }

    //zapisz rezultat do konkretnego stringbuildera
    public static void saveResult(CalcData calcData, double result, StringBuilder sb){
        if(result >= 0){
            sb.replace(calcData.start, calcData.end, String.valueOf(result));
        }else{
            if(calcData.start == 0){
                sb.replace(calcData.start, calcData.end, String.valueOf(result));
            }else{
                sb.replace(calcData.start, calcData.end, String.valueOf(result));
            }
        }
    }

    //zapisz rezultat do specjalnego stringbuildera
    public static void saveResultSpecial(CalcData calcData, double result, StringBuilder sb){
        if(result >= 0){
            sb.replace(calcData.start+1, calcData.end, String.valueOf(result));
        }else{
            if(calcData.start == 0){
                sb.replace(calcData.start, calcData.end, String.valueOf(result));
            }else{
                sb.replace(calcData.start, calcData.end, String.valueOf(result));
            }
        }
    }

    //wypisz wynik
    public static void printResult(CalcData calcData){
        System.out.println(calcData.toString() + "\n");
    }

    //sprawdź czy jest mnożenie itd w głównym stringbuilderze
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
        }

        for(int x=0; x<calcData.stringBuilder.length(); x++){
            if(calcData.stringBuilder.charAt(x)=='/'||calcData.stringBuilder.charAt(x)=='*')calcData.setMultiply(true);
            if(calcData.stringBuilder.charAt(x)=='('){
                calcData.setLeftBracket(calcData.getLeftBracket()+1);
                calcData.setBracket(true);
            }
            if(calcData.stringBuilder.charAt(x)==')'){
                calcData.setRightBracket(calcData.getRightBracket()+1);
                calcData.setBracket(true);
            }

        }

        if(calcData.getLeftBracket()!=0){
            calcData.setBracket(true);
        }

        if(!calcData.isBracket()&&!calcData.isMultiply()&&!calcData.isSpecialBracket())calcData.setNormal(true);
    }

    //sprawdź czy jest mnożenie itd w konkretnym stringbuilderze
    public static void checkAction(CalcData calcData, StringBuilder sb){
        calcData.setLeftBracket(0);
        calcData.setRightBracket(0);

        calcData.setMultiply(false);
        calcData.setBracket(false);
        calcData.setSpecialBracket(false);
        calcData.setNormal(false);

        for(int x=0; x<sb.length(); x++){
            if(sb.charAt(x)=='/'||sb.charAt(x)=='*')calcData.setMultiply(true);
            if(sb.charAt(x)=='('){
                calcData.setLeftBracket(calcData.getLeftBracket()+1);
                calcData.setBracket(true);
            }
            if(sb.charAt(x)==')'){
                calcData.setRightBracket(calcData.getRightBracket()+1);
                calcData.setBracket(true);
            }

        }

    }

    //oblicz i zwróć wynik
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
