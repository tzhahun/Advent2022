package Advent2022.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.entry;

public class Day3Input2 {


    public static final Map<Character, Integer> SCORES = Map.ofEntries(
            entry('a', 1),
            entry('b', 2),
            entry('c', 3),
            entry('d', 4),
            entry('e', 5),
            entry('f', 6),
            entry('g', 7),
            entry('h', 8),
            entry('i', 9),
            entry('j', 10),
            entry('k', 11),
            entry('l', 12),
            entry('m', 13),
            entry('n', 14),
            entry('o', 15),
            entry('p', 16),
            entry('q', 17),
            entry('r', 18),
            entry('s', 19),
            entry('t', 20),
            entry('u', 21),
            entry('v', 22),
            entry('w', 23),
            entry('x', 24),
            entry('y', 25),
            entry('z', 26));

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day3/input.txt"));
//        Scanner scanner = new Scanner(new File("day3/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int count = 0;
        while (scanner.hasNext()) {
            String line1 = scanner.next();
            String line2 = scanner.next();
            String line3 = scanner.next();
            System.out.println(line1);
            System.out.println(line2);
            System.out.println(line3);
            count = count + getCount(line1, line2, line3);


        }
        System.out.println(count);
    }

    public static int getCount(String line1, String line2, String line3) {
        char[] strings1 = line1.toCharArray();
        char[] strings2 = line2.toCharArray();
        char[] strings3 = line3.toCharArray();
        Integer score = 0;
        boolean stop = false;
        int lineCount = 0;
        List<Character> used = new ArrayList<>();
        for (int i = 0; i < strings1.length  && !stop; i++) {
            for (int initernal = 0; initernal < strings2.length  && !stop; initernal++) {
                for (int inner = 0; inner < strings3.length  && !stop; inner++) {
                    if (strings1[i] == strings2[initernal] && strings2[initernal] == strings3[inner]) {
                        score = SCORES.get(strings1[i]);
                        if (score == null) {
                            char stringLowerCase = Character.toLowerCase(strings1[i]);
                            score = SCORES.get(stringLowerCase);
                            score = score + 26;
                        }

                        System.out.println(strings1[i] + " " + score);
                        lineCount = lineCount + score;
                        System.out.println(lineCount);
                        score = 0;
//                            used.add(strings1[i]);
;
                        stop=true;
                    }
                }

            }
        }
        return lineCount;

    }
//2330 is too low
//2479
}