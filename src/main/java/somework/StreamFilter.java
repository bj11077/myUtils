package somework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {

    public static void main(String[] args) {
        StreamFilter streamFilter = new StreamFilter();

        List<TestDto> dtoList =streamFilter.init();


        List<TestDto> twoList = dtoList.stream().filter(e -> e.b > 2).collect(Collectors.toList());

//        dtoList.forEach(System.out::println);

        twoList.forEach(System.out::println);




    }

    public List<TestDto> init(){
        List<TestDto> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new TestDto(i+"a",i));
        }
        return list;
    }


    class TestDto{
        String a;
        int b;

        public TestDto() {
        }

        public TestDto(String a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return "TestDto{" +
                    "a='" + a + '\'' +
                    ", b=" + b +
                    '}';
        }
    }

}
