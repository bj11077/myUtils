package somework.regularEx;

import java.util.regex.Pattern;

public class LastCarNumber {
    public static void main(String[] args) {

//        String regex ="([0-9]+)([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{1})";


        // 차량번호검증
        String regex ="([0-9]+)([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{1})([0-9]{4})";

        String car1 = "12허2323";

        //앞자리추출
        String frontRegex ="([0-9]+)([ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{1})";

        if(Pattern.matches(regex,car1)){
            System.out.println(car1.replaceAll(frontRegex,""));
        }

        String car2 = "32가123";
        String car3 = "34g232";

//        String car4 = car1.replaceAll(regex,"");
//        System.out.println(car4);

        System.out.println(Pattern.matches(regex,car1));
        System.out.println(Pattern.matches(regex,car2));
        System.out.println(Pattern.matches(regex,car3));

    }
}
