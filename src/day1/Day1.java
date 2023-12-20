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
                sum += getValuePartTwo(sc.nextLine());
            }
            sc.close();
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
        int digitFromChar;
        int digitFromWord;


        for (int i=0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))){
                digitFromChar = Integer.parseInt(String.valueOf(line.charAt(i)));
                if (!hasFirstDigit) {
                    firstDigit = digitFromChar;
                    hasFirstDigit = true;
                }
                lastDigit = digitFromChar;
            }

            digitFromWord = wordToDigit(line.substring(i));
            if (digitFromWord != 0){
                if (!hasFirstDigit) {
                    firstDigit = digitFromWord;
                    hasFirstDigit = true;
                }
                lastDigit = digitFromWord;
            }
        }
        return firstDigit*10 + lastDigit;
    }

    private int wordToDigit (String word) {
        if (word.startsWith("one")) return 1;
        else if (word.startsWith("two")) return 2;
        else if (word.startsWith("three")) return 3;
        else if (word.startsWith("four")) return 4;
        else if (word.startsWith("five")) return 5;
        else if (word.startsWith("six")) return 6;
        else if (word.startsWith("seven")) return 7;
        else if (word.startsWith("eight")) return 8;
        else if (word.startsWith("nine")) return 9;
        else return 0;
    }
}
