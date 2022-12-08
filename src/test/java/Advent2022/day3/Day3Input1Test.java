package Advent2022.day3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Day3Input1Test {

    @ParameterizedTest
    @CsvSource(textBlock = """
                vJrwpWtwJgWrhcsFMMfFFhFp, 16
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL, 38
                PmmdzqPrVvPwwTWBwg,42
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,22
                ttgJtRGJQctTZtZT,20
                CrZsJsPPZsGzwwsLwLmpwMDw ,19   
 """)
    void getCount1(String line, int output) {
        assertTrue(Day3Input1.getCount(line) == output);
    }


    @ParameterizedTest
    @CsvSource(textBlock = """ 
                AAA ,27 
 """)
    void getCount2(String line, int output) {
        assertTrue(Day3Input1.getCount(line) == output);
    }
}