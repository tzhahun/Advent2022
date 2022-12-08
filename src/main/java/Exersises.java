import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class Exersises {
    /*
    Write a simple application which, when given a list of integers:



            - Calculates and prints out the mode of the distribution

- Prints a bar chart showing the frequency of each element. For the sake of simplicity, the bar chart can be horizontal i.e. with the number on the y axis, and frequency on the x axis. Although a nice to have, the y axis doesn't have to be ordered.



    Consider the time and space complexity of your solution, aiming for one that is O(N) in both cases, where N is the size of the input collection.



    Initial signature: void analyse(Collection<Integer> values);



    Example:

    input: [1,3,3,1,4,3,3]



    output:

    mode=3

            1 | **

            3 | ****

            4 | *

     */

    public static void main(String[] s) {
        analyse(List.of(1, 3, 3, 1, 4, 3, 3));

    }

    static void analyse(Collection<Integer> values) {
        Map<Integer, Integer> distr = new TreeMap<>(Comparator.reverseOrder());
        AtomicInteger max = new AtomicInteger();
        AtomicInteger maxCount = new AtomicInteger();
        //n
        values.forEach(v -> {
                    Integer currentValue = distr.getOrDefault(v, 0);

                    currentValue++;

                    distr.put(v, currentValue);

                    if (currentValue > maxCount.get()) {
                        max.set(v);
                        maxCount.set(currentValue);
                    }
                }
        );
//        Map<Integer, Long> distr =  values.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        //n
        System.out.println("mode " + max.get());
        distr.entrySet().stream().forEach(e -> {
                    System.out.println(e.getKey() + "|" + "*".repeat(Math.toIntExact(e.getValue())));
                }
        );
    }





}
