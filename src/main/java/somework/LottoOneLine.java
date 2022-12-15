package somework;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoOneLine {

    public static void main(String[] args) {
        IntStream.iterate(1, e -> e + 1).limit(45).boxed().sorted((a,b)-> new Random().nextInt(2)-1).limit(6).sorted().forEach(System.out::println);
    }


}
