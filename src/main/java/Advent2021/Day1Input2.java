package Advent2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Input2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day1/input2.txt"));

        int count = 0;
        int[] currentWindow = { scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
        while (scanner.hasNextInt()) {
            int next = scanner.nextInt();

            if (currentWindow[1]+currentWindow[2]+ next > currentWindow[0]+currentWindow[1]+currentWindow[2]) {
                count++;
            }
            currentWindow[0] = currentWindow[1];
            currentWindow[1] = currentWindow[2];
            currentWindow[2] = next;

        }
        System.out.println(count);
    }

}


