package Advent2022.day5;

import Advent2022.day4.Day4Input1;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Day5Input1Test {

    @ParameterizedTest


    @CsvSource(textBlock = """
                move 1 from 2 to 1 ,DCP
//                move 3 from 1 to 3,CZ
 """)
    void getCount1(String line, String output) {
//        assertTrue(Day5Input1.getCount(line).equals(output));

    }

}