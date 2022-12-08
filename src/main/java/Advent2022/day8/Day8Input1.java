package Advent2022.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day8/input.txt"));
//        Scanner scanner = new Scanner(new File("day8/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int count = 0;
        int rows = 99;
        int columns = 99;
        int[][] trees = new int[rows][columns];
        int edgeTreesCount = rows+rows+columns+columns -4;

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
                if (isTreeVisibleFromRight(trees, treeRow, column, trees[treeRow][column])) {
                    System.out.println(trees[treeRow][column] + " visible from right in index " + treeRow + " " + column);
                    count++;
                } else if (isTreeVisibleFromLeft(trees, treeRow, column, trees[treeRow][column], columns)) {
                    System.out.println(trees[treeRow][column] + " visible from left in index " + treeRow + " " + column);
                    count++;
                } else if (isTreeVisibleFromTop(trees, treeRow, column, trees[treeRow][column])) {
                    System.out.println(trees[treeRow][column] + " visible from top in index " + treeRow + " " + column);
                    count++;
                } else if (isTreeVisibleFromBottom(trees, treeRow, column, trees[treeRow][column], rows)) {
                    System.out.println(trees[treeRow][column] + " visible from bottom in index " + treeRow + " " + column);
                    count++;
                }
            }
        }
        System.out.println(count + edgeTreesCount);
    }

    public static boolean isTreeVisibleFromRight(int[][] trees, int treeRow, int column, int currentTreeValue) {
        for (int i = 0; i < column; i++) {
            if (currentTreeValue <= trees[treeRow][i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTreeVisibleFromLeft(int[][] trees, int treeRow, int column, int currentTreeValue, int columns) {
        for (int i = columns - 1; i > column; i--) {
            if (currentTreeValue <= trees[treeRow][i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTreeVisibleFromTop(int[][] trees, int treeRow, int column, int currentTreeValue) {
        for (int i = 0; i < treeRow; i++) {
            if (currentTreeValue <= trees[i][column]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isTreeVisibleFromBottom(int[][] trees, int treeRow, int column, int currentTreeValue, int rows) {
        for (int i = rows - 1; i > treeRow; i--) {
            if (currentTreeValue <= trees[i][column]) {
                return false;
            }
        }
        return true;
    }
// 405 is too low
//Result: 1684
}

