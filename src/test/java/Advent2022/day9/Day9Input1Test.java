package Advent2022.day9;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Input1Test {


    @ParameterizedTest
    @CsvSource(textBlock = """        
             4,0,4,0 ,R 4, 4, 4,4, 3,4
                    4,4,4, 3,U 4,0,4,1,4, 4
                    0,4,1,4,L 3, 0,1,0,2, 3
                    0,1,0,2, D 1, 1,1,0,2,1
                    1,1,0,2,R 4, 1,5,1,4,3
                    1,5,1,4, D 1, 2,5,1,4,1
                     2,5,1,4,L 5,2,0,2,1,4
            """)
    void getIndexes(Integer headRow, Integer headColumn, Integer tailRow, Integer tailColumn, String line,
                    Integer expectedHeadRow, Integer expectedHeadColumn, Integer expectedTailRow, Integer expectedTailColumn, Integer expectedPointsVisited) {
        Set<Day9Input1.Index> pointsVisited = new HashSet<>();
        Day9Input1.Indexes indexes = Day9Input1.getIndexes(headRow, headColumn, tailRow, tailColumn, pointsVisited, line);
        assertEquals(indexes.headRow(), expectedHeadRow);
        assertEquals(indexes.headColumn(), expectedHeadColumn);
        assertEquals(indexes.tailRow(), expectedTailRow);
        assertEquals(indexes.tailColumn(), expectedTailColumn);
        assertEquals(pointsVisited.size(), expectedPointsVisited);
    }
}