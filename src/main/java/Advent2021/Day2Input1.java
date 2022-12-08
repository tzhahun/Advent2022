package Advent2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day2/input.txt"));

        int horizontal =0;
        int vertical = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            int number = scanner.nextInt();
            if("forward".equals(line)){
                horizontal = horizontal + number;
            } else  if("up".equals(line)){
                vertical = vertical - number;
            }else  if("down".equals(line)){
                vertical = vertical + number;
            }
        }
        System.out.println(horizontal*vertical);
    }

}


