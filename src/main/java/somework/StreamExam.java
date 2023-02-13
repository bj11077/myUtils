package somework;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExam {

    public static void main(String[] args) throws NoSuchAlgorithmException {


        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");


        List<String> list2 = list1.stream().map(e -> e + "2").collect(Collectors.toList());


        for (int i = 0; i < list1.size(); i++) {
            System.out.println("list1 => "+ list1.get(i));
        }

        for (int i = 0; i < list2.size(); i++) {
            System.out.println("list2 => "+ list2.get(i));
        }


    }
}
