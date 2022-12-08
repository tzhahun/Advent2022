package Advent2022.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4Input2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day4/input2.txt"));
//        Scanner scanner = new Scanner(new File("day4/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int count = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            count = count+ getCount(line);

        }
        System.out.println(count);
    }

    public static int getCount(String line) {
        int count =0;
        System.out.println(line);

        String[] pair = line.split(",");
        String[] firstInts = pair[0].split("-");
        String[] secondInts = pair[1].split("-");
        int firstIntFirstPair = Integer.parseInt(firstInts[0]);
        int sedonfIntFirstPair = Integer.parseInt(firstInts[1]);
        int firstIntSecondPair = Integer.parseInt(secondInts[0]);
        int secondIntSecondPAir = Integer.parseInt(secondInts[1]);
        if(firstIntFirstPair <= firstIntSecondPair && sedonfIntFirstPair >= secondIntSecondPAir){
            count ++;
            System.out.println("contains in first pair");
        }else if( firstIntFirstPair >= firstIntSecondPair && sedonfIntFirstPair <= secondIntSecondPAir){
            count ++;
            System.out.println("contains in second pair");
        }else if( sedonfIntFirstPair >= firstIntSecondPair && sedonfIntFirstPair < secondIntSecondPAir){
            count ++;
            System.out.println("sedonfIntFirstPair > firstIntSecondPair && sedonfIntFirstPair < secondIntSecondPAir");
        }
        else if( firstIntFirstPair > firstIntSecondPair  && firstIntFirstPair <=secondIntSecondPAir ){
            count ++;
            System.out.println("firstIntFirstPair > firstIntSecondPair   && firstIntFirstPair <secondIntSecondPAir");
        }

        return count;
    }
//Result:883
    //wwonrng 698
    //828 is too low

}


