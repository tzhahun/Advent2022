package Advent2022.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Input2 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day6/input2.txt"));
//        Scanner scanner = new Scanner(new File("day6/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int count = 0;
        while (scanner.hasNext()) {
            String line = scanner.next();
            count = getCount(line);

        }
        System.out.println(count);
    }

    public static int getCount(String line) {
        int count =0;
        System.out.println(line);

        char[] characters = line.toCharArray();
        int useChar = 14;
        //abcal
        for(int i=0; i<characters.length-useChar+1; i++){

            Integer i1 = compare(characters, useChar-1, i, 1, characters[i],useChar-1);
            System.out.println(i1);

            if (i1 != 0) return i1;
        }



        return count;
    }
    //bvwbjplb gvbhsrlpg dmjqwftvncz
    private static Integer compare(char[] characters, int useChar, int i, int kPassed, char toCompare,int stillToCompareCount) {
        Integer result = 0;
        boolean stop = false;
        for(int k = i + kPassed, loopCount =0, last =useChar+ i + kPassed-1 ; loopCount< useChar && stillToCompareCount!=0 && k<characters.length ; k++, loopCount++){
            char character = characters[k];
            System.out.println(toCompare +" "+ character);
            if(toCompare == character){
                stop = true;
                break;
            }else if(k==last && stillToCompareCount==1) {
                result =  k+1;
                stop=true;
            }
//mjq jpqmgbljsphdztnvjfqwrcgsmlb
        }
        if (stop) {
            return result;
        }else if (  result==0 && stillToCompareCount!=0 ){
            //characters.length kPassed+i &&
            return  compare(characters, stillToCompareCount-1, i, kPassed+1, characters[kPassed+i], stillToCompareCount-1);
        }else{
            return characters.length;

        }
    }
//Result: 2414
}





