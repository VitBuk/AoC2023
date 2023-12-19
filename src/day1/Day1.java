package day1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Day1 {
    private static final int smallestWord = 3;
    private static final int largestWord = 5;

    public int answer() {
        int sum = 0;
        try {
            FileInputStream fis = new FileInputStream("src/day1/values.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                sum += getValue(sc.nextLine());
            }
            sc.close();     //closes the scanner
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }

    private int getValue(String line) {
        boolean hasFirstDigit = false;
        int firstDigit = 0;
        int lastDigit = 0;
        int digit;
        for (int i=0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))){
                digit = Integer.parseInt(String.valueOf(line.charAt(i)));
                if (!hasFirstDigit){
                    firstDigit = digit;
                    hasFirstDigit = true;
                }
                lastDigit = digit;
            }
        }
        return firstDigit*10 + lastDigit;
    }

    private int getValuePartTwo(String line) {
        boolean hasFirstDigit = false;
        int firstDigit = 0;
        int lastDigit = 0;
        int digit;

        for (int i=0; i < line.length(); i++) {
            if (Character.isDigit() || isStartOfTheDigit)
        }
    }

    private boolean isStartOfTheDigit(String word) {
        for (int i=smallestWord; i < largestWord; i++) {
            
        }
    }
    private int wordToDigit (String word) {
        switch(word) {
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            default:
                return 0;
        }
    }
}
