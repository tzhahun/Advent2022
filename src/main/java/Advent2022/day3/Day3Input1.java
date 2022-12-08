package Advent2022.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Map.entry;

public class Day3Input1 {

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
            String line = scanner.next();
            System.out.println(line);
            count = count + getCount( line);


        }
        System.out.println(count);
    }

    public static int getCount(String line) {
        char[] strings = line.toCharArray();
        Integer score = 0;
        boolean stop = false;
        int lineCount = 0;
        List<Character> used = new ArrayList<>();
        int lenght = line.length();
        for (int i = 0; i <= lenght / 2 - 1 && !stop; i++) {
            for (int initernal = lenght / 2; initernal < lenght && !stop; initernal++) {
                if (strings[i] == strings[initernal]) {
//                    if (!used.contains(strings[i])) {
                        score = SCORES.get(strings[i]);
                        if (score == null) {
                            char stringLowerCase = Character.toLowerCase(strings[i]);
                            score = SCORES.get(stringLowerCase);
                            score = score + 26;
                        }

                        System.out.println(strings[i] + " " + score);
                        lineCount = lineCount + score;
                        System.out.println(lineCount);
                       // used.add(strings[i]);
                    // can use stop or used.add and gettign the same result
                        stop =true;

//                    }
                }
            }

        }
        return lineCount;
    }
//Result: 7875
}



