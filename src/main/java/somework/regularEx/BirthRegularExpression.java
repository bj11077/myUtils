package somework.regularEx;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class BirthRegularExpression {

    public static Map<String, String> expressionMap  = new HashMap() {{
        put("EX1", "([0-9]{4})(-)(0[0-9]|1[0-2])(-)([0-2][0-9]|3[0-1])");
        put("EX2", "([0-9]{4})(0[0-9]|1[0-2])([0-2][0-9]|3[0-1])");
        put("EX3","([0-9]{2})(-)(0[0-9]|1[0-2])(-)([0-2][0-9]|3[0-1])");
        put("EX4","([0-9]{2})(0[0-9]|1[0-2])([0-2][0-9]|3[0-1])");
    }};


    public static void main(String[] args) {

        String birthFullAndHyphen = "1990-10-06";
        String birthFullWithoutHyphen = "19901006";
        String birthWithHyphen = "90-10-06";
        String birthWithoutHyphen = "901006";






    }



    public String getMatchExpression(String birth){
        for (String key : expressionMap.keySet()) {
            if(Pattern.matches(expressionMap.get(key),birth)){
                return key;
            }
        }
        return null;
    }

    public String getConvertValue(String express, String birth){
        String convertValue="";

        switch (express){
            case "EX1":
                convertValue = birth;
                break;
            case "EX2":
                convertValue = birth.substring(0,4)+"-"+birth.substring(4,6)+"-"+birth.substring(6);
                break;
            case "EX3":
                convertValue = getFullYear(birth.substring(0,2)) + birth.substring(2);
                break;
            case "EX4":
                convertValue = getFullYear(birth.substring(0,2)) + "-" + birth.substring(3,5)+"-"+birth.substring(6);
                break;
        }
        return convertValue;
    }

    public String getFullYear(String yy){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        String nowYY = sdf.format(new Date()).substring(2);
        int frontYY =  Integer.parseInt(sdf.format(new Date()).substring(0,2));
        return Integer.parseInt(yy) <= Integer.parseInt(nowYY)? frontYY +yy : frontYY - 1 +yy ;
    }

}
