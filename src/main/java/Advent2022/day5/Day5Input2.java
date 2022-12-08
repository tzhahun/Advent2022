package Advent2022.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Input2 {


    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day5/input2.txt"));
//        Scanner scanner = new Scanner(new File("day5/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

//       List<Deque<String>> stacks = new ArrayList<>();
//        Deque<String> stack1 = new ArrayDeque<>(List.of("Z", "N"));
//        Deque<String> stack2 = new ArrayDeque<>(List.of("M", "C", "D"));
//        Deque<String> stack3 = new ArrayDeque<>(List.of("P"));
//        stacks.add(stack1);
//        stacks.add(stack2);
//        stacks.add(stack3);


        List<Deque<String>> stacks = new ArrayList<>();
        Deque<String> stack1 = new ArrayDeque<>(List.of("W", "R", "F"));
        Deque<String> stack2 = new ArrayDeque<>(List.of("T", "H", "M","C", "D", "V", "W", "P"));
        Deque<String> stack3 = new ArrayDeque<>(List.of("P", "M", "Z","N", "L"));
        Deque<String> stack4 = new ArrayDeque<>(List.of("J", "C", "H","R"));
        Deque<String> stack5 = new ArrayDeque<>(List.of("C", "P", "G","H", "Q", "T", "B"));
        Deque<String> stack6 = new ArrayDeque<>(List.of("G", "C", "W","L", "F", "Z"));
        Deque<String> stack7 = new ArrayDeque<>(List.of("W", "V", "L","Q", "Z", "J", "G", "C"));
        Deque<String> stack8 = new ArrayDeque<>(List.of("P", "N", "R","F", "W", "T", "V", "C"));
        Deque<String> stack9 = new ArrayDeque<>(List.of("J", "W", "H", "G", "R", "S", "V"));
        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
        stacks.add(stack5);
        stacks.add(stack6);
        stacks.add(stack7);
        stacks.add(stack8);
        stacks.add(stack9);

        String count = "";
        while (scanner.hasNext()) {
            String line = scanner.next();
            count = getCount(stacks, line);

        }
        System.out.println(count);
    }

    public static String getCount(List<Deque<String>> stacks, String line) {
        int count = 0;
        System.out.println(line);


        String[] splitString = line.split(" ");
        int howMany = Integer.parseInt(splitString[1]);
        int fromIndex = Integer.parseInt(splitString[3]) - 1;
        int toIndex = Integer.parseInt(splitString[5]) - 1;

        Deque<String> stackFrom = stacks.get(fromIndex);
        Deque<String> stackTo = stacks.get(toIndex);
        Deque<String> newStack = new ArrayDeque<>();
        for (int i = 0; i < howMany; i++) {
            newStack.addLast(stackFrom.removeLast());
        }


        int size = newStack.size();
        for (int i = 0; i < size; i++) {
            stackTo.addLast(newStack.removeLast());

        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < stacks.size(); i++) {
            Deque<String> strings = stacks.get(i);
            if (strings.size() > 0) {
                output.append(strings.getLast());
            }
        }
        System.out.println(output);

        return output.toString();
    }
//Result:
}


