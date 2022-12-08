package Advent2022.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day7/input.txt"));
//        Scanner scanner = new Scanner(new File("day7/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int count = 0;
        Deque<String> stack1 = new ArrayDeque<>();
        Map<String, Integer> dirSizes = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.next();

            String[] splitLine = line.split(" ");
            if ("cd".equals(splitLine[1]) && !"..".equals(splitLine[2])) {
                stack1.addLast(splitLine[2]);
                dirSizes.put(splitLine[2], 0);
                System.out.println("add dir " + splitLine[2]);
            } else if ("cd".equals(splitLine[1])) {
                if (stack1.size() > 0) {
                    String lastDir = stack1.removeLast();
                    Integer dirSize = dirSizes.remove(lastDir);
                    if(dirSize != null) {
                        System.out.println("remove dir " + lastDir + " size " + dirSize);


                        if (dirSize <= 100000) {
                            count = count + dirSize;
                        }
                    }else{
                        System.out.println("remove dir " + lastDir + " size null");

                    }
                }
            } else {
                int size = 0;
                try {
                    size = Integer.parseInt(splitLine[0]);
                } catch (NumberFormatException e) {
                    System.out.println("not number");
                }
                if (size > 0) {
                    System.out.println("adding size " + size);

                    int finalSize = size;
                    dirSizes.replaceAll((k, v) -> v + finalSize);
                }
            }


        }
        System.out.println(count);
    }


//Result: 1644735
}


