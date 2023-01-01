package Advent2022.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day24Input1 {


    record Blizzard(Position position, String direction) {
    }

    static List<Position> edges = new ArrayList<>();
    static List<Blizzard> blizzards = new ArrayList<>();

    static Position startingExpeditionLocation = new Position(0, 1);
    static Position destinationLocation;

    static int numberOfRows;
    static int numberOfColumns;

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day24/input.txt"));
//        Scanner scanner = new Scanner(new File("day24/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int lineNumber = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            char[] chars = line.toCharArray();
            int columnNumber = 0;
            for (Character c : chars) {
                final Position position = new Position(lineNumber, columnNumber);
                columnNumber++;
                switch (c) {
                    case '#' -> edges.add(position);
                    case '>' -> blizzards.add(new Blizzard(position, ">"));
                    case '<' -> blizzards.add(new Blizzard(position, "<"));
                    case '^' -> blizzards.add(new Blizzard(position, "^"));
                    case 'v' -> blizzards.add(new Blizzard(position, "v"));
                }
            }
            numberOfColumns = chars.length-1;

            lineNumber++;
        }
        destinationLocation = new Position(lineNumber - 1, numberOfColumns - 1);
        numberOfRows = lineNumber - 1;
        edges.add(new Position(startingExpeditionLocation.getRow() - 1, startingExpeditionLocation.getColumn()));
        Set<Position> possibleExpeditonLocations = new HashSet<>();
        possibleExpeditonLocations.add(startingExpeditionLocation);

        int countMoves = 0;

        while (!possibleExpeditonLocations.contains(destinationLocation)) {
            moveBlizzardsByOne();
            Set<Position> positionsToMoveInto = new HashSet<>();
            //Check available locations for each possibleExpsditin locatin
            possibleExpeditonLocations.forEach(loc -> {
                Set<Position> available = checkAvailable(loc);
                if (!available.isEmpty()) {
                    positionsToMoveInto.addAll(available);
                }
            });
            System.out.println("Progress" + countMoves +" possibleExpeditonLocations size " +possibleExpeditonLocations.size()+" positionsToMoveInto size " +positionsToMoveInto.size());
            System.out.println(possibleExpeditonLocations);

            possibleExpeditonLocations = positionsToMoveInto;
            countMoves++;
        }


        System.out.println(countMoves);
    }

    private static Set<Position> checkAvailable(Position loc) {
        Set<Position> possibleExpeditonLocations = new HashSet<>();
        //check >
        Position toCheck = new Position(loc.getRow(), loc.getColumn() + 1);
        checkFreePosition(possibleExpeditonLocations, toCheck);
        //check <
        Position toCheckLeft = new Position(loc.getRow(), loc.getColumn() - 1);
        checkFreePosition(possibleExpeditonLocations, toCheckLeft);
        Position toCheckUp = new Position(loc.getRow() - 1, loc.getColumn());
        checkFreePosition(possibleExpeditonLocations, toCheckUp);
        Position toCheckDown = new Position(loc.getRow() + 1, loc.getColumn());
        checkFreePosition(possibleExpeditonLocations, toCheckDown);
        //Waiting position
        checkFreePosition(possibleExpeditonLocations, loc);
        return possibleExpeditonLocations;
    }

    private static void checkFreePosition(Set<Position> possibleExpeditonLocations, Position toCheck) {
        if (blizzards.stream().noneMatch(b -> b.position().equals(toCheck)) && edges.stream().noneMatch(b -> b.equals(toCheck))) {
            possibleExpeditonLocations.add(toCheck);
        }
    }


    public static void moveBlizzardsByOne() {
        blizzards.forEach(b -> {
            Position position = b.position();
            if (">".equals(b.direction())) {
                position.setColumn(b.position().getColumn() + 1);
                if (edges.contains(position)) {
                    position.setColumn(1);
                }
            } else if ("<".equals(b.direction())) {
                position.setColumn(b.position().getColumn() - 1);
                if (edges.contains(position)) {
                    position.setColumn(numberOfColumns - 1);
                }
            }
            if ("v".equals(b.direction())) {
                position.setRow(b.position().getRow() + 1);
                if (edges.contains(position)) {
                    position.setRow(1);
                }
            }
            if ("^".equals(b.direction())) {
                position.setRow(b.position().getRow() - 1);
                if (edges.contains(position)) {
                    position.setRow(numberOfRows - 1);
                }
            }
        });
    }

//Result: 277
}
