package Advent2022.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class Day10Input2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day10/input2.txt"));
//        Scanner scanner = new Scanner(new File("day10/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));
        StringBuilder builder = new StringBuilder();
        Set<Integer> cyclesToCheck = Set.of(40,80,120,160,200,240);
        int x = 1;
        int currentCycle = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(line);
            String[] instr = line.split(" ");
            String instruction = instr[0];


            if("addx".equals(instruction)){
                int value = Integer.parseInt(instr[1]);
                currentCycle++;
                //check
                appendChar(builder, x, currentCycle);
                currentCycle = getTotalStrength(cyclesToCheck, x, currentCycle,builder);
                currentCycle++;
                //check
                appendChar(builder, x, currentCycle);
                currentCycle = getTotalStrength(cyclesToCheck, x, currentCycle, builder);
                x = x+ value;
            } else if ("noop".equals(instruction)){
                currentCycle++;
                //check
                appendChar(builder, x, currentCycle);
                currentCycle = getTotalStrength(cyclesToCheck, x, currentCycle, builder);

            }

        }

    }

    private static void appendChar(StringBuilder builder, int x, int currentCycle) {
        if(currentCycle == x || currentCycle == x +1 || currentCycle == x +2 ){
            System.out.println("x " + x + " currentCycle " + currentCycle + "  append #" );
            builder.append("#");
            System.out.println(builder);
        }else{
            System.out.println("x " + x + " currentCycle " + currentCycle + "  append." );
            builder.append(".");
            System.out.println(builder);
        }
    }

    private static int getTotalStrength(Set<Integer> cyclesToCheck, int x, int currentCycle,  StringBuilder builder) {
        if(cyclesToCheck.contains(currentCycle)){
            currentCycle = currentCycle -40;
            System.out.println("x " + x + " currentCycle " + currentCycle );
            builder.append("\n");
            System.out.println(builder.toString());
        }
        return currentCycle;
    }

//Result:RBPARAGF
//
//        ###..###..###...##..###...##...##..####.
//        #..#.#..#.#..#.#..#.#..#.#..#.#..#.#....
//        #..#.###..#..#.#..#.#..#.#..#.#....###..
//        ###..#..#.###..####.###..####.#.##.#....
//        #.#..#..#.#....#..#.#.#..#..#.#..#.#....
//        #..#.###..#....#..#.#..#.#..#..###.#....

}


