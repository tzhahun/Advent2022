package Advent2022.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day14Input1 {

    public static final char CHAR_O = 'O';
    private static int rockColumnsSize = 600;
//    private static int rockRowsSize = 200;
    private static int rockRowsSize = 10;

    private static int sandFallColum = 500;

    static int countUnitOfSand = 0;
    record Index(Integer column, Integer row) {
    }

    private static char[][] rock = new char[rockColumnsSize][rockRowsSize];

    public static void main(String[] args) throws FileNotFoundException {

//        Scanner scanner = new Scanner(new File("day14/input.txt"));
        Scanner scanner = new Scanner(new File("day14/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));



        while (scanner.hasNext()) {
            String line1 = scanner.next();
            System.out.println(line1);
            String[] patterns = line1.split(" -> ");
            List<Index> rockIndexes = Arrays.stream(patterns).map(p -> p.split(",")).map(split -> new Index(Integer.parseInt(split[0]), Integer.parseInt(split[1]))).toList();
            update(rock, rockIndexes);
        }
//        print(rock);

        while (placedPieceOfSand(sandFallColum, rock)) {
            countUnitOfSand++;
            print(rock);
        }
        print(rock);

        System.out.println(countUnitOfSand);
    }

    private static boolean placedPieceOfSand(int sandFallColum, char[][] rock) {
        boolean placedSand = false;
        int currentRow = 0;
        int currentColumn = sandFallColum;
        int rowWhereSandIs = 0;
        //Fall down first
        while (currentRow < rockRowsSize && rock[currentColumn][currentRow] != '#' && rock[currentColumn][currentRow] != CHAR_O) {
            currentRow++;
        }
        if (rock[currentColumn][currentRow] == '#' || (rock[currentColumn][currentRow] == CHAR_O  && rock[currentColumn + 1][currentRow] == CHAR_O && rock[currentColumn - 1][currentRow] == CHAR_O)) {
            rock[currentColumn][currentRow-1] = CHAR_O;
            System.out.println(currentColumn +" "+ (currentRow-1));

            return true;
            //Is there space to the right or left

        } else if ((rock[currentColumn + 1][currentRow] != CHAR_O && rock[currentColumn + 1][currentRow] != '#') || (rock[currentColumn - 1][currentRow] != CHAR_O && rock[currentColumn - 1][currentRow] != '#')) {
            //Try to fall further
            //Try to fall to the left diganly


            if (rock[currentColumn - 1][currentRow ] != CHAR_O && rock[currentColumn - 1][currentRow ] != '#') {
                while (currentRow < rockRowsSize && currentColumn < rockColumnsSize && rock[currentColumn][currentRow] != '#' && rock[currentColumn][currentRow] != CHAR_O) {
                    currentRow++;
                    currentColumn--;
                }
                if (currentRow == rockRowsSize) {
                    return false;
                }
                rock[currentColumn-1][currentRow] = CHAR_O;
                System.out.println(currentColumn-1 +" "+ currentRow);

                return true;

            }
            //Try to fall to the right diganly

            if ((rock[currentColumn + 1][currentRow ] != CHAR_O && rock[currentColumn + 1][currentRow ] != '#')) {
//                currentColumn++;
                while (currentRow < rockRowsSize && currentColumn < rockColumnsSize && rock[currentColumn][currentRow] != '#' && rock[currentColumn][currentRow] != CHAR_O) {
                    currentRow++;
                    currentColumn++;
                }
                if (currentRow == rockRowsSize) {
                    return false;
                }
                rock[currentColumn+1][currentRow] = CHAR_O;
                System.out.println(currentColumn+1 +" "+ currentRow);
                return true;


            }

        } else if (currentRow==rockRowsSize) {
            return false;
        } else {
            currentRow++;
            rock[currentColumn][currentRow] = CHAR_O;
            System.out.println(currentColumn +" "+ currentRow);

        }


        return placedSand;
    }

    public static char[][] update(char[][] rock, List<Index> rockIndexes) {
        for (int i = 0; i < rockIndexes.size() - 1; i++) {
            Index indexCurrent = rockIndexes.get(i);
            Integer currentColumn = indexCurrent.column;
            Integer currentRow = indexCurrent.row;
            Index indexNext = rockIndexes.get(i + 1);
            Integer nextColumn = indexNext.column;
            Integer nextRow = indexNext.row;
            rock[currentColumn][currentRow] = '#';

            if (currentColumn.compareTo(nextColumn) > 0) {
                //column decreasing
                for (int k = currentColumn - 1; k >= nextColumn; k--) {
                    rock[k][currentRow] = '#';
                }
            } else if (currentColumn.compareTo(nextColumn) < 0) {
                //column increasing
                for (int k = currentColumn + 1; k <= nextColumn; k++) {
                    rock[k][currentRow] = '#';
                }
            } else if (currentRow.compareTo(nextRow) > 0) {
                //row decreasing
                for (int k = currentRow + 1; k >= nextRow; k--) {
                    rock[currentColumn][k] = '#';
                }
            } else if (currentRow.compareTo(nextRow) < 0) {
                //row increasing
                for (int k = currentRow + 1; k <= nextRow; k++) {
                    rock[currentColumn][k] = '#';
                }
            }
        }

        return rock;
    }

    private static void print(char[][] rock) {
        StringBuilder builder = new StringBuilder();
        for (int k = 0; k < rockRowsSize; k++) {
            for (int i = 0; i < rockColumnsSize; i++) {
                char c = rock[i][k];
                builder.append(c == '\0' ? "." : c);
            }
            builder.append(('\n'));

        }
        System.out.println(builder);
    }

    public static boolean isInRightOrder(String line1, String line2) {
        int count = 0;
        System.out.println(line1 + " vs " + line2);

        String[] pair = line1.split(",");
        String[] firstInts = pair[0].split("-");
        String[] secondInts = pair[1].split("-");
        int firstIntFirstPair = Integer.parseInt(firstInts[0]);
        int sedonfIntFirstPair = Integer.parseInt(firstInts[1]);
        if (firstIntFirstPair <= Integer.parseInt(secondInts[0]) && sedonfIntFirstPair >= Integer.parseInt(secondInts[1])) {
            count++;
            System.out.println("contains in first pair");
        } else if (firstIntFirstPair >= Integer.parseInt(secondInts[0]) && sedonfIntFirstPair <= Integer.parseInt(secondInts[1])) {
            count++;
            System.out.println("contains in second pair");
        }
        return false;
    }
//Result: 515
}


