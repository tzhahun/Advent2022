package Advent2022.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day9Input1 {

    record Index(Integer row, Integer column) {
    }



    record Indexes(Integer headRow, Integer headColumn, Integer tailRow, Integer tailColumn) {
    }


    static Set<Index> pointsVisited = new HashSet<>();

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day9/input.txt"));
//        Scanner scanner = new Scanner(new File("day9/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));
        int currentHeadRow = 4;
        int currentHeadColumn = 0;
        int currentTailRow = 4;
        int currentTailColumn = 0;
        pointsVisited.add(new Index(currentTailRow, currentTailColumn));

        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(line);
            Indexes indexes = getIndexes(currentHeadRow, currentHeadColumn, currentTailRow, currentTailColumn, pointsVisited, line);

            currentHeadRow = indexes.headRow;
            currentHeadColumn = indexes.headColumn;
            currentTailRow = indexes.tailRow;
            currentTailColumn = indexes.tailColumn;
        }
        System.out.println(pointsVisited.size());


    }

    public static Indexes getIndexes(int currentHeadRow, int currentHeadColumn, int currentTailRow, int currentTailColumn, Set<Index> pointsVisited, String line) {

        String[] pos = line.split(" ");
        Index oldTailIndex = new Index(currentTailRow, currentTailColumn);
        Index newTailIndex;
        //Move head and then tail
        if ("R".equals(pos[0])) {
            for (int i = 0; i < Integer.parseInt(pos[1]); i++) {
                currentHeadColumn++;
                newTailIndex = moveTail(currentHeadRow, currentHeadColumn, currentTailRow, currentTailColumn);

                if (oldTailIndex != newTailIndex) {
                    pointsVisited.add(newTailIndex);
                }
                currentTailRow = newTailIndex.row;
                currentTailColumn = newTailIndex.column;
                System.out.println(currentHeadRow +" "+ currentHeadColumn+" " +  newTailIndex + " " + pointsVisited.size());
            }
        } else if ("L".equals(pos[0])) {
            for (int i = 0; i < Integer.parseInt(pos[1]); i++) {
                currentHeadColumn--;
                newTailIndex = moveTail(currentHeadRow, currentHeadColumn, currentTailRow, currentTailColumn);
                if (oldTailIndex != newTailIndex) {
                    pointsVisited.add(newTailIndex);
                }
                currentTailRow = newTailIndex.row;
                currentTailColumn = newTailIndex.column;
                System.out.println(currentHeadRow +" "+ currentHeadColumn+" " +  newTailIndex + " " + pointsVisited.size());

            }
        } else if ("D".equals(pos[0])) {
            for (int i = 0; i < Integer.parseInt(pos[1]); i++) {
                currentHeadRow++;
                newTailIndex = moveTail(currentHeadRow, currentHeadColumn, currentTailRow, currentTailColumn);
                if (oldTailIndex != newTailIndex) {
                    pointsVisited.add(newTailIndex);
                }
                currentTailRow = newTailIndex.row;
                currentTailColumn = newTailIndex.column;
                System.out.println(currentHeadRow +" "+ currentHeadColumn+" " +  newTailIndex + " " + pointsVisited.size());

            }
        } else if ("U".equals(pos[0])) {
            for (int i = 0; i < Integer.parseInt(pos[1]); i++) {
                currentHeadRow--;
                newTailIndex = moveTail(currentHeadRow, currentHeadColumn, currentTailRow, currentTailColumn);
                if (oldTailIndex != newTailIndex) {
                    pointsVisited.add(newTailIndex);
                }
                currentTailRow = newTailIndex.row;
                currentTailColumn = newTailIndex.column;
                System.out.println(currentHeadRow +" "+ currentHeadColumn+" " +  newTailIndex + " " + pointsVisited.size());
            }
        }
        return new Indexes(currentHeadRow, currentHeadColumn, currentTailRow, currentTailColumn);
    }

    private static Index moveTail(int currentHeadRow, int currentHeadColumn, int currentTailRow, int currentTailColumn) {
        if (currentHeadRow == currentTailRow && currentTailColumn == currentHeadColumn) {
            //Head covers tail , tail does not move
            //Head and tail have the same indexes
        } else if (currentHeadRow == currentTailRow && Math.abs(currentTailColumn - currentHeadColumn) == 2) {
            //If the head is ever two steps directly left, or right from the tail, the tail must also move one step in that direction so it remains close enough:
            if (currentTailColumn > currentHeadColumn) {
                currentTailColumn--;
            } else {
                currentTailColumn++;
            }
        } else if (currentTailColumn == currentHeadColumn && Math.abs(currentHeadRow - currentTailRow) == 2) {
            //If the head is ever two steps directly up, down from the tail, the tail must also move one step in that direction so it remains close enough:
            if (currentTailRow > currentHeadRow) {
                currentTailRow--;
            } else {
                currentTailRow++;
            }
        } else if (currentHeadRow != currentTailRow && currentTailColumn != currentHeadColumn) {
            //tail always moves one step diagonally to keep up
            if (Math.abs(currentHeadRow - currentTailRow) == 2 && currentTailRow > currentHeadRow) {
                //then update  row
                currentTailRow--;
                //And update  column
                if (currentTailColumn > currentHeadColumn) {
                    currentTailColumn--;
                } else {
                    currentTailColumn++;
                }
            } else  if (Math.abs(currentHeadRow - currentTailRow) == 2 && currentTailRow < currentHeadRow)  {
                //update tail row
                currentTailRow++;
                //And update  column
                if (currentTailColumn > currentHeadColumn) {
                    currentTailColumn--;
                } else {
                    currentTailColumn++;
                }
            }

            if (Math.abs(currentTailColumn - currentHeadColumn) == 2 && currentTailColumn > currentHeadColumn) {
                currentTailColumn--;
                if (currentTailRow > currentHeadRow) {
                    currentTailRow--;
                } else {
                    currentTailRow++;
                }
            } else  if (Math.abs(currentTailColumn - currentHeadColumn) == 2 && currentTailColumn < currentHeadColumn){
                currentTailColumn++;
                if (currentTailRow > currentHeadRow) {
                    currentTailRow--;
                } else {
                    currentTailRow++;
                }
            }
        }
        return new Index(currentTailRow, currentTailColumn);
    }
//Result: 6266
}

