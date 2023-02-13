package somework;

import oracle.security.crypto.core.MD5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoOneLine {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
//        IntStream.iterate(1, e -> e + 1).limit(45).parallel().boxed().sorted((a,b)-> new Random().nextInt(2)-1).limit(6).sorted().forEachOrdered(System.out::println);
//        Arrays.asList(51204,54952,51116).stream().map(Character::toChars).forEach(System.out::println);

//        Character.toch

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\bbdt\\res.txt"));

//        "테스트".chars().forEach(e-> {
//            try {
//                bw.write(String.valueOf(e)+" ");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        });

        Arrays.asList("테스트".split("")).stream().map(String::getBytes).forEach(e->{
            String res = "";

            System.out.println(new String(e));
            for (byte b : e) {
                res += b +" ";
            }
            try {
                bw.write(res+"\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bw.close();

    }
    }
