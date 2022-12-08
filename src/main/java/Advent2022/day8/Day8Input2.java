package Advent2022.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day8Input2 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day8/input2.txt"));
//        Scanner scanner = new Scanner(new File("day8/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));


        int count = 0;
        int rows = 99;
        int columns = 99;
        int[][] trees = new int[rows][columns];
        int topScore = 0;

        int rowCounter = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                trees[rowCounter][i] = Integer.parseInt(String.valueOf(chars[i]));
            }
            rowCounter++;
        }

        for (int treeRow = 1; treeRow < rows - 1; treeRow++) {
            for (int column = 1; column < columns - 1; column++) {
                int countFromRight = isTreeVisibleFromRight(trees, treeRow, column, trees[treeRow][column]);
                System.out.println(trees[treeRow][column] + "  from right in index " + treeRow + " " + column + " " + countFromRight);
                int countFromLeft = isTreeVisibleFromLeft(trees, treeRow, column, trees[treeRow][column], columns);
                System.out.println(trees[treeRow][column] + "  from left in index " + treeRow + " " + column + " " + countFromLeft);

                int countFromTop = isTreeVisibleFromTop(trees, treeRow, column, trees[treeRow][column]);
                System.out.println(trees[treeRow][column] + "  from top in index " + treeRow + " " + column + " " + countFromTop);
                int countFromBottom = isTreeVisibleFromBottom(trees, treeRow, column, trees[treeRow][column], rows);
                System.out.println(trees[treeRow][column] + "  from bottom in index " + treeRow + " " + column + " " + countFromBottom);
                count = countFromRight * countFromLeft * countFromTop * countFromBottom;
                System.out.println(count);
                if(topScore< count){
                    topScore=count;
                }
            }

        }

        System.out.println(topScore);
    }

    public static int isTreeVisibleFromRight(int[][] trees, int treeRow, int column, int currentTreeValue) {
        int count = 0;
        for (int i = column-1; i >= 0; i--) {
            if (currentTreeValue > trees[treeRow][i]) {
                count ++;
            }else if (currentTreeValue <= trees[treeRow][i]) {
                count ++;
                break;
            }

        }
        return count;
    }

    public static int isTreeVisibleFromLeft(int[][] trees, int treeRow, int column, int currentTreeValue, int columns) {
        int count = 0;
        for (int i = column+1; i < columns; i++) {
            if (currentTreeValue > trees[treeRow][i]) {
                count ++;
            }else if (currentTreeValue <= trees[treeRow][i]) {
                count ++;
                break;
            }

        }
        return count;
    }

    public static int isTreeVisibleFromTop(int[][] trees, int treeRow, int column, int currentTreeValue) {

        int count = 0;
        for (int i = treeRow-1; i >= 0; i--) {
            if (currentTreeValue > trees[i][column]) {
                count ++;
            }else if (currentTreeValue <= trees[i][column]) {
                count ++;
                break;
            }

        }
        return count;
    }

    public static int isTreeVisibleFromBottom(int[][] trees, int treeRow, int column, int currentTreeValue, int rows) {
        int count = 0;
        for (int i = treeRow+1; i < rows; i++) {
            if (currentTreeValue > trees[i][column]) {
                count ++;
            }else if (currentTreeValue <= trees[i][column]) {
                count ++;
                break;
            }

        }
        return count;
    }
// 405 is too low
//Result: 1684
}




