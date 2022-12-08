package Advent2022.day4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Day4Input1Test {

    @ParameterizedTest
    @CsvSource(value = {
            "2-8,3-7: 1",
            "6-6,4-6: 1",
            "2-6,4-8: 0"
    },delimiter = ':')

    void getCount1(String line, int output) {
        assertTrue(Day4Input1.getCount(line) == output);
    }

}