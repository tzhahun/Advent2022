package Advent2022.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Day10Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day10/input.txt"));
//        Scanner scanner = new Scanner(new File("day10/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        Set<Integer> cyclesToCheck = Set.of(20,60,100,140,180,220);
        int x = 1;
        int currentCycle = 0;
        int totalStrength = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(line);
            String[] instr = line.split(" ");
            String instruction = instr[0];


            if("addx".equals(instruction)){
                int value = Integer.parseInt(instr[1]);
                currentCycle++;
                //check
                totalStrength = getTotalStrength(cyclesToCheck, x, currentCycle, totalStrength);
                currentCycle++;
                //check
                totalStrength = getTotalStrength(cyclesToCheck, x, currentCycle, totalStrength);
                x = x+ value;
            } else if ("noop".equals(instruction)){
                currentCycle++;
                //check
                totalStrength = getTotalStrength(cyclesToCheck, x, currentCycle, totalStrength);

            }

        }
        System.out.println(totalStrength);
    }

    private static int getTotalStrength(Set<Integer> cyclesToCheck, int x, int currentCycle, int totalStrength) {
        if(cyclesToCheck.contains(currentCycle)){
           int strength =  x * currentCycle;
           totalStrength = totalStrength + strength;
           System.out.println("x " + x + "currentCycle " + currentCycle + "totalStrength "  +totalStrength);
        }
        return totalStrength;
    }


//Result: 12740
}


