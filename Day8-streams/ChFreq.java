package MayBatchJava.Jun5A;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChFreq {
    public static void main(String[] args) {
        String input="hello world";

        Map<Character, Long> frequencyMap=input.chars()
            .mapToObj(c -> (char) c)
            .filter(Character::isLetterOrDigit)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        frequencyMap.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
