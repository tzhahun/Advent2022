package Advent2022.day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day17Input1 {

    static Set<Position> minusFigureCoordinates = Set.of(
            Position.builder().row(0).column(2).build(),
            Position.builder().row(0).column(3).build(),
            Position.builder().row(0).column(4).build(),
            Position.builder().row(0).column(5).build()
    );

    static Set<Position> plusFigureCoordinates = Set.of(
            Position.builder().row(0).column(3).build(),
            Position.builder().row(1).column(2).build(),
            Position.builder().row(1).column(3).build(),
            Position.builder().row(1).column(4).build(),
            Position.builder().row(2).column(3).build()
    );

    static Set<Position> lFigureCoordinates = Set.of(
            Position.builder().row(0).column(4).build(),
            Position.builder().row(1).column(4).build(),
            Position.builder().row(2).column(4).build(),
            Position.builder().row(2).column(2).build(),
            Position.builder().row(2).column(3).build()
    );

    static Set<Position> iFigureCoordinates = Set.of(
            Position.builder().row(0).column(2).build(),
            Position.builder().row(1).column(2).build(),
            Position.builder().row(2).column(2).build(),
            Position.builder().row(3).column(2).build()
    );

    static Set<Position> oFigureCoordinates = Set.of(
            Position.builder().row(0).column(2).build(),
            Position.builder().row(0).column(3).build(),
            Position.builder().row(1).column(2).build(),
            Position.builder().row(1).column(3).build()
    );

    static List<Set<Position>> listOfFigures = List.of(minusFigureCoordinates, plusFigureCoordinates, lFigureCoordinates, iFigureCoordinates, oFigureCoordinates);


    static int numberOfRowsBetweenFigureAndEdgeAtStart = 3;

    static Map<Integer, List<Boolean>> bottomEdges = new HashMap<>() {{
        put(0, new LinkedList<>());
        put(1, new LinkedList<>());
        put(2, new LinkedList<>());
        put(3, new LinkedList<>());
        put(4, new LinkedList<>());
        put(5, new LinkedList<>());
        put(6, new LinkedList<>());
    }};

    static Map<Integer, Integer> columnsHight = new HashMap<>() {{
        put(0, 0);
        put(1, 0);
        put(2, 0);
        put(3, 0);
        put(4, 0);
        put(5, 0);
        put(6, 0);
    }};

    public static void main(String[] args) throws FileNotFoundException {

//        Scanner scanner = new Scanner(new File("day17/input.txt"));
        Scanner scanner = new Scanner(new File("day17/a_sample.txt"));

//        int maxRocks = 2022;
//        int maxRocks = 10;
        int maxRocks = 1;
        int currentRocks = 0;
        String line = scanner.next();
        char[] moves = line.toCharArray();

        int nextMovesIndex = 0;
        for (int f = 0; currentRocks < maxRocks; f++) {
            currentRocks++;
            System.out.println("ROCK " + currentRocks);
            if (f == listOfFigures.size()) {
                f = 0;
            }

            Set<Position> figurePositions = listOfFigures.get(f);
            System.out.println("figurePositions " + figurePositions);
            int height = getHeight(figurePositions);
            updateBottomEdgeForNewStart(height);
            System.out.println("bottomEdges at start " + bottomEdges);
            Set<Position> newPositions = new HashSet<>();

            boolean hasCrossedBottomEdge = false;
            while (!hasCrossedBottomEdge) {
                System.out.println("nextMovesIndex " + nextMovesIndex);

                char c = moves[nextMovesIndex];
                if ('<' == c) {
                    newPositions = moveLeft(figurePositions);
                    if (isWithInBoundaries(newPositions) && !hasCrossedEdge(newPositions)) {
                        figurePositions = newPositions;
                    }
                } else {
                    newPositions = moveRight(figurePositions);
                    if (isWithInBoundaries(newPositions) && !hasCrossedEdge(newPositions)) {
                        figurePositions = newPositions;
                    }
                }
                System.out.println("figurePositions " + figurePositions);
                newPositions = moveDown(figurePositions);
                hasCrossedBottomEdge = hasCrossedEdge(newPositions);
                if (hasCrossedBottomEdge) {
                    adjustBottomEdgeForNewFallenFigure(figurePositions);
                } else {
                    figurePositions = newPositions;
                }
                System.out.println("figurePositions " + figurePositions);
                nextMovesIndex++;
                if (nextMovesIndex == moves.length) {
                    nextMovesIndex = 0;
                }
            }


        }


        System.out.println(columnsHight);
        System.out.println(columnsHight.values().stream().max(Comparator.naturalOrder()).get());

    }

    private static int getHeight(Set<Position> figurePositions) {
        return figurePositions.stream().max(Comparator.comparingInt(Position::getRow)).get().getRow() - figurePositions.stream().min(Comparator.comparingInt(Position::getRow)).get().getRow() + 1;
    }


    public static Set<Position> moveRight(Set<Position> figurePositions) {
        System.out.println("moveRight ");
        return figurePositions.stream().map(p -> new Position(p.getRow(), p.getColumn() + 1)).collect(Collectors.toSet());
    }

    public static Set<Position> moveLeft(Set<Position> figurePositions) {
        System.out.println("moveLeft ");
        return figurePositions.stream().map(p -> new Position(p.getRow(), p.getColumn() - 1)).collect(Collectors.toSet());
    }

    public static Set<Position> moveDown(Set<Position> figurePositions) {
        System.out.println("moveDown ");
        return figurePositions.stream().map(p -> new Position(p.getRow() + 1, p.getColumn())).collect(Collectors.toSet());
    }


    public static boolean isWithInBoundaries(Set<Position> figurePositions) {
        return figurePositions.stream().noneMatch(p -> p.getColumn() < 0 || p.getColumn() > 6);
    }

    //return crossed section
    private static boolean hasCrossedEdge(Set<Position> figurePositions) {
        return figurePositions.stream().anyMatch(Day17Input1::isPositionMatches);
    }


    private static boolean isPositionMatches(Position position) {
        List<Boolean> booleans = bottomEdges.get(position.getColumn());
        int row = position.getRow();
        if(booleans != null && booleans.size() > row) {
            return booleans.get(row);
        }else{
            return true;
        }
    }

    private static void updateBottomEdgeForNewStart(int newFigureHight) {
        AtomicInteger minRow = getMinRowOfBottomEdge();
        int howManyRowsToAdjustBy = newFigureHight + numberOfRowsBetweenFigureAndEdgeAtStart - minRow.get();
        bottomEdges.forEach((k, v) -> {
            for (int i = 0; i < howManyRowsToAdjustBy; i++) {
                v.add(i, false);
            }
        });
    }

    private static AtomicInteger getMinRowOfBottomEdge() {
        AtomicInteger minRow = new AtomicInteger(0);
        //Check the first 4 rows to see by how many rows to adjust
        for (int i = 0; i <= 3 && minRow.get() < 0; i++) {
            int finalI = i;
            bottomEdges.forEach((k, v) -> {
                if (v.size()>finalI && v.get(finalI)) {
                    minRow.set(finalI);
                }
            });
        }
        return minRow;
    }

    //    Increase column hight
    private static void adjustBottomEdgeForNewFallenFigure(Set<Position> figurePositions) {

        AtomicInteger minRowOfEdge = getMinRowOfBottomEdge();

        for (Integer columnIndex : columnsHight.keySet()) {
            List<Position> positions = figurePositions.stream().filter(p -> columnIndex.equals(p.getColumn())).toList();
            Optional<Position> min = positions.stream().min(Comparator.comparingInt(Position::getRow));
//            Optional<Position> max = figurePositions.stream().filter(p -> columnIndex.equals(p.getColumn())).max(Comparator.comparingInt(Position::getRow));
            //check min row posistion is not higher than bottom edge
            if (min.isPresent() && minRowOfEdge.get() > min.get().getRow()) {
                int heightToIncreaseBy = min.get().getRow() - minRowOfEdge.get();
                positions.forEach(p -> bottomEdges.get(columnIndex).set(p.getRow(), true));
                columnsHight.computeIfPresent(columnIndex, (k, v) -> v + (heightToIncreaseBy));
            }

        }
        System.out.println("columnsHight " + columnsHight);

        System.out.println("bottomEdges " + bottomEdges);

    }
//Result: 515

}


