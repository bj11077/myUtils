package somework.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IntArrayConcat {

    public static void main(String[] args) {
            int[] arr1 = {1,2,3,4,5};
            int[] arr2 = {3,4,5,6,7,8};

        List<Integer> result = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).boxed().collect(Collectors.toList());
    }
}
