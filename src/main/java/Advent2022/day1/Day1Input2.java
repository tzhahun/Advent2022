package Advent2022.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day1Input2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day1/input2.txt"));
//        Scanner scanner = new Scanner(new File("day1/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));


        List<Integer> callories = new ArrayList<>();
        callories.add(0);
        int countCurrentElf = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            if ("".equals(line)) {
                callories.add(countCurrentElf);
                countCurrentElf = 0;
            } else {
                countCurrentElf = countCurrentElf + Integer.parseInt(line);
            }
        }
        callories.add(countCurrentElf);

        List<Integer> calloriesTop = callories.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
        int result = calloriesTop.stream().mapToInt(Integer::intValue).sum();
        System.out.println(result);
    }
    //Expected output
    //45000 for sample
//208437 for part 2
}


