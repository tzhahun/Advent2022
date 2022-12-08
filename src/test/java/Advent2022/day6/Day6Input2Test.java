package Advent2022.day6;

import Advent2022.day4.Day4Input2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Day6Input2Test {








    @ParameterizedTest
    @CsvSource(textBlock = """
                          
            mjqjpqmgbljsphdztnvjfqwrcgsmlb,19
                                                                   bvwbjplbgvbhsrlpgdmjqwftvncz,23
                                                                   nppdvjthq ldpwncqszvftbr mjlhg,23
                                                                   nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29
                                                                   zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26                                                              
                         
            """)
            void getCount4(String line, int expected) {
        assertTrue(Day6Input2.getCount(line) ==expected);

    }

}