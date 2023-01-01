package Advent2022.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Day25Input1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("day25/input.txt"));
//        Scanner scanner = new Scanner(new File("day25/a_sample.txt"));
        scanner.useDelimiter(System.getProperty("line.separator"));

        BigDecimal sum = BigDecimal.ZERO;
        while (scanner.hasNext()) {
            String line = scanner.next();
            sum = sum.add(converToDecimalNumber(line));

        }
        System.out.println(sum);


        System.out.println(convertToSnafu(sum));
    }

    private static String convertToSnafu(BigDecimal decimal) {
        StringBuilder snafu = new StringBuilder();

        while (decimal.setScale(0, RoundingMode.DOWN).compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal five = BigDecimal.valueOf(5);
            BigDecimal reminder = decimal.remainder(five);
            snafu.append(convertDecimalToSnafu(reminder.intValue()));
            if (reminder.compareTo(BigDecimal.valueOf(2)) > 0) {
                decimal = decimal.add(BigDecimal.valueOf(2));
            }
            decimal = decimal.divide(five);

        }
        return snafu.reverse().toString();

    }

    public static BigDecimal converToDecimalNumber(String line) {
        char[] chars = line.toCharArray();
        BigDecimal count = BigDecimal.ZERO;
        int power = chars.length - 1;
        for (int i = 0; i < chars.length; i++, power--) {
            BigDecimal valueOf = BigDecimal.valueOf(convertNumber(chars[i]));
            count = count.add(valueOf.multiply(BigDecimal.valueOf(Math.pow(5, power))));
//            System.out.println(valueOf + " " + power + " " + valueOf * Math.pow(5, power));

        }
        System.out.println(line + " " + count);

        return count;
    }

    private static int convertNumber(int i) {
        switch (i) {
            case '2' -> {
                return 2;
            }
            case '1' -> {
                return 1;
            }
            case '0' -> {
                return 0;
            }
            case '-' -> {
                return -1;
            }
            case '=' -> {
                return -2;
            }
        }
        return 0;
    }

    private static char convertDecimalToSnafu(int i) {
        switch (i) {
            case 2 -> {
                return '2';
            }
            case 1 -> {
                return '1';
            }
            case 0 -> {
                return '0';
            }
            case 3 -> {
                return '=';
            }
            case 4 -> {
                return '-';
            }
        }
        return 0;
    }
//Result:2-2=21=0021=-02-1=-0
}


