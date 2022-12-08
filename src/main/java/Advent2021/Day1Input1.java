package Advent2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day1/input.txt"));

        int count = 0;
        int[] currentWindow = { scanner.nextInt()};
        while (scanner.hasNextInt()) {
            int next = scanner.nextInt();
            if (next > currentWindow[0]) {
                count++;
            }
            currentWindow[0] = next;
        }
        System.out.println(count);
    }

}


