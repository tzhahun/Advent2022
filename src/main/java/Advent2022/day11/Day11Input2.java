package Advent2022.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day11Input2 {

    static List<List<BigDecimal>> itemsPosession = new ArrayList<>();

    record Operation(String operation, String byWhatValue) {
    }

    static Map<Integer, Operation> operations = new HashMap<>();
    static Map<Integer, BigDecimal> test = new HashMap<>();
    static Map<Integer, Integer> ifTrue = new HashMap<>();
    static Map<Integer, Integer> ifFalse = new HashMap<>();
    static Map<Integer, Integer> inspectedCount = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day11/input2.txt"));
//        Scanner scanner = new Scanner(new File("day11/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        int currentProcessingMonkey = 0;
        int rounds = 10000;
        BigDecimal worryyLevelReduction = new BigDecimal(9699690);
        while (scanner.hasNext()) {
            String line = scanner.next();
            System.out.println(line);
            if (line.startsWith("Monkey ")) {
                currentProcessingMonkey = Integer.parseInt(line.substring(line.indexOf(" ") + 1, line.indexOf(":")));
                itemsPosession.add(new ArrayList<>());
            } else if (line.contains("Starting items:")) {
                String onlyIntegers = line.substring(line.indexOf(":") + 1);
                String[] arrayOfInts = onlyIntegers.split(",");
                for (String arrayOfInt : arrayOfInts) {
                    itemsPosession.get(currentProcessingMonkey).add(BigDecimal.valueOf(Long.parseLong(arrayOfInt.trim())));
                }
            } else if (line.contains("Operation:")) {
                String ops = line.substring(line.indexOf("old") + 4);
                String[] operationAndValue = ops.split(" ");
                Operation op = new Operation(operationAndValue[0], operationAndValue[1]);
                operations.put(currentProcessingMonkey, op);
            } else if (line.contains("Test:")) {
                String[] value = line.split(" ");
                test.put(currentProcessingMonkey, BigDecimal.valueOf(Long.parseLong(value[5])));
            } else if (line.contains("If true:")) {
                String[] value = line.split("If true: throw to monkey ");
                ifTrue.put(currentProcessingMonkey, Integer.parseInt(value[1]));
            } else if (line.contains("If false:")) {
                String[] value = line.split("If false: throw to monkey ");
                ifFalse.put(currentProcessingMonkey, Integer.parseInt(value[1]));
            }
        }
        System.out.println(" itemsPosession " + itemsPosession);
        System.out.println(" counts " + inspectedCount);

        for (int i = 1; i <= rounds; i++) {
            AtomicInteger currentMonkey = new AtomicInteger();

            itemsPosession.forEach(listItems -> {
                Operation op = operations.get(currentMonkey.get());
                while (!listItems.isEmpty()) {
                    BigDecimal item = listItems.get(0);
                    processWorry(worryyLevelReduction, currentMonkey.get(), op, item);
                    listItems.remove(0);
                }
                currentMonkey.getAndIncrement();
            });

            System.out.println("After round  " + i + " itemsPosession " + itemsPosession);
            System.out.println("After round  " + i + " counts " + inspectedCount);

        }
        System.out.println(inspectedCount);
    }

    private static BigDecimal processWorry(BigDecimal worryyLevelReduction, int currentMonkey, Operation op, BigDecimal item) {
        System.out.println("currentMonkey "+ currentMonkey + " inspect  " + item);
        inspectedCount.merge(currentMonkey, 1, Integer::sum);

        BigDecimal result = new BigDecimal(0);
        BigDecimal byWhatValue = new BigDecimal(0);
        int nextMonkey;
        if ("old".equals(op.byWhatValue)) {
            byWhatValue = item;
        } else {
            byWhatValue =  BigDecimal.valueOf(Long.parseLong(op.byWhatValue));
        }
        if ("*".equals(op.operation)) {
            result = item.multiply(byWhatValue);
            System.out.println(" multiply  " + item+ " *" + byWhatValue +"=" + result);

        } else {
            System.out.println(" add  " + item+ " + " + byWhatValue +"=" + result);
            result = item.add(byWhatValue);

        }

        BigDecimal remainder = result.remainder(worryyLevelReduction);
        if( BigDecimal.ZERO.compareTo(remainder) != 0 && BigDecimal.ONE.compareTo(remainder) != 0  ){
            result = remainder;
        }


        if (result.remainder(test.get(currentMonkey)).equals(BigDecimal.ZERO)) {
            nextMonkey = ifTrue.get(currentMonkey);
            System.out.println(" Current worry level is divisible by " + test.get(currentMonkey));
        } else {
            nextMonkey = ifFalse.get(currentMonkey);

            System.out.println(" Current worry level is not divisible by " + test.get(currentMonkey));
        }

        //Move itmes increment count
        itemsPosession.get(nextMonkey).add(result);
        System.out.println(" Item with worry level " + result + " is thrown to monkey " + nextMonkey);

        return result;
    }


//Result: 161943*156056 = 25272176808
}

