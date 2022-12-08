package Advent2022.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day1/input.txt"));
//        Scanner scanner = new Scanner(new File("day1/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int countMostCallories = 0;
        int countCurrentElf = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(line);
            if("".equals(line)){
                if(countMostCallories <countCurrentElf ){
                    countMostCallories = countCurrentElf;
                }
                countCurrentElf = 0;

            }else{
                countCurrentElf =countCurrentElf+ Integer.parseInt(line);
            }
        }
        System.out.println(countMostCallories);
    }
//Result: 69795
}


