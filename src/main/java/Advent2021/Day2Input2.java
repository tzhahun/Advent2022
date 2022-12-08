package Advent2021;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2Input2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day2/input2.txt"));

        int horizontal =0;
        int aim = 0;
        int depth = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            int number = scanner.nextInt();
            if("forward".equals(line)){
                horizontal = horizontal + number;
                depth = depth + aim*number;
            } else  if("up".equals(line)){
                aim = aim - number;
            }else  if("down".equals(line)){
                aim = aim + number;
            }
        }
        System.out.println(horizontal*depth);
    }

}


