package Advent2022.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2Input2 {

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
                "A Y",3,
                "B X",0,
                "C Z",6,
                "C Y",3,
                "C X",0,
                "A Z",6,
                "B Z",6,
                "B Y",3,
                "A X",0
        );

        Map<String,String> shapeToChoose = Map.of(
                "6A","Y",
                "0B","X",
                "3C","Z",
                "0C","Y",
                "6C","X",
                "0A","Z",
                "6B","Z",
                "3B","Y",
                "3A","X"
        );


//        int countMostCallories = 0;
        int scoreTotal = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();

            Integer score = scoreForRound.get(line);
            String[] splitString = line.split(" ");
            String firstString = splitString[0];
            String letter= shapeToChoose.get(score+firstString);
            Integer shapeScore  = scoreForShape.get(letter);
            scoreTotal = scoreTotal + score + shapeScore;
            System.out.println(line + " " +score + " " +  shapeScore  + " " + scoreTotal);


        }
        System.out.println(scoreTotal);
    }
//Result:

}


