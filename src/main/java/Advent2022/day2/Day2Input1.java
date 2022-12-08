package Advent2022.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;

public class Day2Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day2/input.txt"));
//        Scanner scanner = new Scanner(new File("day2/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

//        A Y  - win =6  Rock -paper
//        B X - loss = 0  PAper-rock
//        C Z  - draw = 3 -Scissors - Scissors
//        C Y    =loss  0      Scissors defeats Paper
//        C X    - win 6   = Rock defeats Scissors
//        A-Z    - loss  0     = Rock defeats Scissors
//        B-Z     =win 6       Scissors defeats Paper

        //Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock.
//        A for Rock, B for Paper, and C for Scissors.
        // Paper (Y)  - 2 - B
       // Rock (X) - 1  - A
        //Scissors(Z) -3  - C

        Map<String, Integer> scoreForShape = Map.of(
                "Y",2,
                "X", 1,
                "Z", 3
        );
        Map<String, Integer> scoreForRound = Map.of(
                "A Y",6,
                "B X",0,
                "C Z",3,
                "C Y",0,
                "C X",6,
                "A Z",0,
                "B Z",6,
                "B Y",3,
                "A X",3
                );


//        int countMostCallories = 0;
        int scoreTotal = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();

            Integer score = scoreForRound.get(line);
            String[] splitString = line.split(" ");
            String secondString = splitString[1];
            Integer shapeScore = scoreForShape.get(secondString);
            scoreTotal = scoreTotal + score + shapeScore;
            System.out.println(line + " " +score + " " +  shapeScore  + " " + scoreTotal);


        }
        System.out.println(scoreTotal);
    }
//Result:13009
    //Result smaple:

}


