package somework.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectExam {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(5,3,6,7);

//        System.out.println(list.stream().collect(Collectors.maxBy(Comparator.naturalOrder())));;

        List<ExDto> exList = Arrays.asList(new ExDto("t1","a",1000),new ExDto("t1","b",1500)
                , new ExDto("t2","c",2000), new ExDto("t2","d",900),
                new ExDto("t3","f",2500),new ExDto("t4","d",0));

        Map<String, List<ExDto>> collect = exList.stream().collect(Collectors.groupingBy(ExDto::getExType));


//        System.out.println(exList.stream().collect(Collectors.groupingBy(ExDto::getExType,Collectors.mapping(ExDto::getName,Collectors.toList()))).toString());;

//        System.out.println(collect.toString());







    }
}
