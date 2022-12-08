package Advent2022.day5;

import Advent2022.day4.Day4Input2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Day5Input2Test {

    @ParameterizedTest
    @CsvSource(value = {
            "5-7,7-9: 1",
            "2-8,3-7: 1",
            "6-6,4-6: 1",
            "2-6,4-8: 1",
            "2-4,6-8: 0",
            "2-3,4-5: 0",
            "2-2,2-2: 1",
            "1-3,2-5: 1",
            "5-5,2-5: 1",
            "35-82,34-52: 1",
            "95-98,15-29: 0",
            "97-99,1-97: 1",





    },delimiter = ':')
    void getCount1(String line, int output) {
        assertTrue(Day4Input2.getCount(line) == output);
   }

}