package Advent2022.day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Day23Input1 {

    //Getting out of index exception had to pad input with dots from each direction
    //Firts part had to copy from printed output in concole and in notepad useing find funciton count dots as code to find the first/last column and row was thoring out of index
    //Second part i just increased rounds to 1000 and then printed round and  moves map and when map was empty i took the round number
    private static List<List<String>> mapOfTheBoard = new ArrayList<>();

    record Direction(String direction, int rowIndex, int columnIndex) {
    }

    record CurrentElfLocation(int row, int column) {
    }

    record NewElfLocation(int row, int column) {
    }

    record Move(List<Direction> directionsToCheck, Direction toMove) {
    }

    private static List<Move> listOfMovesToGoOver = new ArrayList<>();

    static Map<NewElfLocation, List<CurrentElfLocation>> proposedMoves = new HashMap<>();
    static int limitRows = 6;
    static int limitColumns = 6;

    static Direction north = new Direction("N", -1, 0);
    static Direction south = new Direction("S", 1, 0);
    static Direction west = new Direction("W", 0, -1);
    static Direction east = new Direction("E", 0, 1);
    static Direction ne = new Direction("NE", -1, 1);
    static Direction nw = new Direction("NW", -1, -1);
    static Direction se = new Direction("SE", 1, 1);
    static Direction sw = new Direction("SW", 1, -1);
    static List<Direction> allDirections = List.of(north, south, west, east, ne, nw, se, sw);


    public static void main(String[] args) throws FileNotFoundException {


        listOfMovesToGoOver.add(new Move(List.of(north, ne, nw), north));
        listOfMovesToGoOver.add(new Move(List.of(south, se, sw), south));
        listOfMovesToGoOver.add(new Move(List.of(west, nw, sw), west));
        listOfMovesToGoOver.add(new Move(List.of(east, ne, se), east));

        Scanner scanner = new Scanner(new File("day23/input2.txt"));
//        Scanner scanner = new Scanner(new File("day23/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        loadMapOfTheBoard(scanner);
        int timesToMove = 1000;
        int i = 1;
        while (i <= timesToMove) {
            System.out.println("round " + i);
            moveElfes();
            i++;
            Move move = listOfMovesToGoOver.remove(0);
            listOfMovesToGoOver.add(move);
        }


        long count = getCount();

        System.out.println(count);
    }

    private static void moveElfes() {
        AtomicInteger intCountRow = new AtomicInteger();
        mapOfTheBoard.forEach(row -> {
            AtomicInteger intCountColumn = new AtomicInteger();
            row.forEach(column -> {
//                                    int indexOfRow = mapOfTheBoard.indexOf(row);
//                                    int indexOfColumn = row.indexOf(column);
                        int indexOfRow = intCountRow.get();
                        int indexOfColumn = intCountColumn.get();
                        if ("#".equals(column)) {
                            AtomicBoolean allowedMoveFound = new AtomicBoolean(false);
                            boolean needsToMove = needsToMove(indexOfRow, indexOfColumn);
//                            System.out.println("needsToMove " + needsToMove);
//                            System.out.println(indexOfRow + "  " + indexOfColumn);
                            if (needsToMove) {
                                listOfMovesToGoOver.forEach(move -> {
                                    if (!allowedMoveFound.get() && isAllowedMove(indexOfRow, indexOfColumn, move)) {
                                        int newRow = indexOfRow + move.toMove().rowIndex();
                                        int newColumn = indexOfColumn + move.toMove().columnIndex();
//                                    if (newRow <= limitRows && newRow >= 0 && newColumn <= limitColumns && newColumn >= 0) {
                                        NewElfLocation newLoc = new NewElfLocation(newRow, newColumn);
                                        CurrentElfLocation currentElfLocation = new CurrentElfLocation(indexOfRow, indexOfColumn);
//                                        System.out.println("new Proposed move" + currentElfLocation + newLoc);
                                        proposedMoves.computeIfAbsent(newLoc, k -> new ArrayList<>()).add(currentElfLocation);
                                        allowedMoveFound.set(true);
//                                    }
                                    }
                                });
                            }
                        }
                        intCountColumn.getAndIncrement();

                    }
            );
            intCountRow.getAndIncrement();
        });

        System.out.println(proposedMoves);


        final Map<NewElfLocation, List<CurrentElfLocation>> incrementedProposedMovesColumns = adjustForNegativeIndex();

//        System.out.println(incrementedProposedMovesColumns);

        incrementedProposedMovesColumns.forEach((k, v) -> {
//                    if (v.size() == 1) {
                    move(k, v.get(0));
//                    }
                }
        );

//        System.out.println(getStringBuilder());
        proposedMoves.clear();
    }

    private static StringBuilder getStringBuilder() {
        StringBuilder builder = new StringBuilder();
        mapOfTheBoard.forEach(l -> {
            l.forEach(c -> {
                builder.append(c);
            });
            builder.append("\n");
        });
        return builder;
    }

    private static Map<NewElfLocation, List<CurrentElfLocation>> adjustForNegativeIndex() {
        for (Iterator<Map.Entry<NewElfLocation, List<CurrentElfLocation>>> it = proposedMoves.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<NewElfLocation, List<CurrentElfLocation>> entry = it.next();

            if (entry.getValue().size() > 1) {
                it.remove();
            }
        }

        //negative index incrase list and adjsust new locations
        final Map<NewElfLocation, List<CurrentElfLocation>> incrementedProposedMoves = new HashMap<>();
        if (proposedMoves.keySet().stream().anyMatch(k -> k.row() < 0)) {
            updateMapOfElfsNewRow();
            proposedMoves.forEach((k, v) -> {
                CurrentElfLocation currentElfLocation = v.get(0);
                incrementedProposedMoves.put(new NewElfLocation(k.row() + 1, k.column()), List.of(new CurrentElfLocation(currentElfLocation.row() + 1, currentElfLocation.column())));

            });
        } else {
            incrementedProposedMoves.putAll(proposedMoves);
        }
        //Adjust columns
        final Map<NewElfLocation, List<CurrentElfLocation>> incrementedProposedMovesColumns = new HashMap<>();

        if (incrementedProposedMoves.keySet().stream().anyMatch(k -> k.column() < 0)) {
            updateMapOfElfsNewColumn();
            incrementedProposedMoves.forEach((k, v) -> {
                CurrentElfLocation currentElfLocation = v.get(0);
                incrementedProposedMovesColumns.put(new NewElfLocation(k.row(), k.column() + 1), List.of(new CurrentElfLocation(currentElfLocation.row(), currentElfLocation.column() + 1)));

            });
        } else {
            incrementedProposedMovesColumns.putAll(incrementedProposedMoves);
        }
        return incrementedProposedMovesColumns;
    }

    private static void updateMapOfElfsNewRow() {
        int rowSize = mapOfTheBoard.size();
        List<String> newRow = new ArrayList<>(rowSize);
        for (int i = 0; i < rowSize; i++) {
            newRow.add(".");
        }
        mapOfTheBoard.add(0, newRow);
    }

    private static void updateMapOfElfsNewColumn() {
        mapOfTheBoard.forEach(list -> list.add(0, "."));
    }

    private static boolean needsToMove(int indexOfRow, int indexOfColumn) {

        for (Direction direction : allDirections) {
            int rowIndex = direction.rowIndex();
            int columnIndex = direction.columnIndex();

            if (indexOfRow + rowIndex >= 0 && indexOfColumn + columnIndex >= 0
                    && indexOfRow + rowIndex < mapOfTheBoard.size()
                    && indexOfColumn + columnIndex < mapOfTheBoard.get(0).size()
                    && "#".equals(mapOfTheBoard.get(indexOfRow + rowIndex).get(indexOfColumn + columnIndex))) {
                return true;
            }

        }
        return false;
    }

    private static void move(NewElfLocation newLoc, CurrentElfLocation currentLoc) {
//        System.out.println("move " + currentLoc + newLoc);
        mapOfTheBoard.get(currentLoc.row()).set(currentLoc.column(), ".");
        mapOfTheBoard.get(newLoc.row()).set(newLoc.column(), "#");


    }

    private static boolean isAllowedMove(int indexOfRow, int indexOfColumn, Move move) {
        AtomicBoolean isAllowedMove = new AtomicBoolean(true);
        move.directionsToCheck().forEach(direction -> {
            int newRowIndex = indexOfRow + direction.rowIndex();
            int newColumn = indexOfColumn + direction.columnIndex();
//            if (newRowIndex < limitRows && newRowIndex >= 0 && newColumn < limitColumns && newColumn >= 0) {
//                System.out.println(newRowIndex + " " + newColumn);
            try {
                boolean containsElf = "#".equals(mapOfTheBoard.get(newRowIndex).get(newColumn));
                if (containsElf) {
                    isAllowedMove.set(false);
                }
            }catch(IndexOutOfBoundsException i){
                System.out.println("IndexOutOfBoundsException " + newRowIndex + "  " + newColumn);

            }
//            }
        });
        return isAllowedMove.get();
    }


    private static void loadMapOfTheBoard(Scanner scanner) {
        while (scanner.hasNext()) {
            String line = scanner.next();
            char[] chars = line.toCharArray();
            List<String> list = new ArrayList<>();
            for (Character c : chars) {
                list.add(String.valueOf(c));
            }
            mapOfTheBoard.add(list);

        }
//        mapOfTheBoard.forEach(System.out::println);
//        System.out.println(getStringBuilder());

    }

    public static long getCount() {
        int firstRowIndex = findFirstRowIndex();
        int lastRowIndex = findLastRowIndex();
        int firstColumnIndex = findFirstColumnIndex();
        int lastColumnIndex = findLastColumnIndex();
//        System.out.println("firstRowIndex " + firstRowIndex +" lastRowIndex "+lastRowIndex + " firstColumnIndex"+firstColumnIndex+ " lastColumnIndex"+lastColumnIndex + " ");
        AtomicLong count = new AtomicLong();
        for(int row =firstRowIndex; row <= lastRowIndex; row++ ){
           List<String> rowList =  mapOfTheBoard.get(row);
            for(int column =firstColumnIndex; column <= lastColumnIndex; column++ ) {
                if (".".equals(rowList.get(column))) {
                    count.getAndIncrement();
                }
            }
        }
//        mapOfTheBoard.forEach(row ->
//        {
//
//            row.forEach(column -> {
//                if (".".equals(column)) {
//                    count.getAndIncrement();
//                }
//            });
//        });

        return count.get();
    }

    private static int findLastColumnIndex() {
        int columnIndex = mapOfTheBoard.size()-1;
        for(int row =mapOfTheBoard.size()-1; row >=0 ; row-- ){
            List<String> rowList =  mapOfTheBoard.get(row);
            if ("#".equals(rowList.get(columnIndex))){
                break;
            }else{
                columnIndex--;
            }

        }
//        return columnIndex+2;
        return 223;
    }

    private static int findFirstColumnIndex() {
        int columnIndex = 0;
        for(int row =0; row <= mapOfTheBoard.size()-1; row++ ){
            List<String> rowList =  mapOfTheBoard.get(row);
            if ("#".equals(rowList.get(columnIndex))){
                break;
            }else{
                columnIndex++;
            }

        }
//        return columnIndex-1;
        return 144;

    }

    private static int findLastRowIndex() {
        boolean foundRow = false;
        int rowIndex = mapOfTheBoard.size();
        for(int row = mapOfTheBoard.size()-1; !foundRow && row >=0 ; row-- ){
            List<String> rowList =  mapOfTheBoard.get(row);
            for(int column =0; column < mapOfTheBoard.get(0).size(); column++ ) {
                if ("#".equals(rowList.get(column))) {
                    foundRow = true;
                    rowIndex = row;
                    break;
                }
            }
        }
        return 154;
    }

    private static int findFirstRowIndex() {
        boolean foundRow = false;
        int rowIndex = 0;
        for(int row =0; !foundRow && row <= mapOfTheBoard.size(); row++ ){
            List<String> rowList =  mapOfTheBoard.get(row);
            for(int column =0; column <= mapOfTheBoard.get(0).size()-1; column++ ) {
                if ("#".equals(rowList.get(column))) {
                    foundRow = true;
                    rowIndex = row;
                    break;
                }
            }
        }
        return 75;
    }

}


//648 is too low