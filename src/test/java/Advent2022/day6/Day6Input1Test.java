package Advent2022.day6;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Day6Input1Test {

    @ParameterizedTest

//
//    abcd,4
//    aabcd,5

//    bvwbjplbgvbhsrlpgdmjqwftvncz,5
//    nppdvjthqldpwncqszvftbrmjlhg,6
//    nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10
//    zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11

    @CsvSource(textBlock = """               
                 abcd,4
              
 """)
    void getCount1(String line, int expected) {
        assertTrue(Day6Input1.getCount(line) ==expected);

    }

    @ParameterizedTest
    @CsvSource(textBlock = """              
               aabcd,5
              
 """)
    void getCount2(String line, int expected) {
        assertTrue(Day6Input1.getCount(line) ==expected);

    }

    @ParameterizedTest
    @CsvSource(textBlock = """
               
                mjqjpqmgbljsphdztnvjfqwrcgsmlb ,7
              
 """)
    void getCount3(String line, int expected) {
        assertTrue(Day6Input1.getCount(line) ==expected);

    }
    @ParameterizedTest
    @CsvSource(textBlock = """
                          
                           bvwbjplbgvbhsrlpgdmjqwftvncz,5
                           nppdvjthqldpwncqszvftbrmjlhg,6
                           nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10
                           zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11
                         
            """)
    void getCount4(String line, int expected) {
        assertTrue(Day6Input1.getCount(line) ==expected);

    }
}