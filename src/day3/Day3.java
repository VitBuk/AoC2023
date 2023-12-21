package day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    private static final String EMPTY_LINE = "....................................." +
            ".......................................................................................................";
    public static void answer() {
        try {
            FileInputStream fis = new FileInputStream("src/day3/values.txt");
            Scanner sc = new Scanner(fis);
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

            System.out.println("Result: " + sumOfParts(lines));
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int sumOfParts (List<String> lines) {
        List<String> threeLines = new ArrayList<>();
        int sumOfParts = 0;

        for (int i=0; i < lines.size(); i++) {
            if (i > 0)
                threeLines.add(lines.get(i-1));
            else
                threeLines.add(EMPTY_LINE);

            threeLines.add(lines.get(i));

            if( i < lines.size()-1)
                threeLines.add(lines.get(i+1));
            else
                threeLines.add(EMPTY_LINE);

            sumOfParts += sumOfPartsForLine(threeLines);
            threeLines.clear();
        }

        return sumOfParts;
    }

    private static int sumOfPartsForLine(List<String> threeLines){
        String middleLine = threeLines.get(1);

        boolean inANumberSequence = false;
        boolean isAPart = false;
        int number =0;
        int numberStart=-1;
        int numberEnd=-1;
        int sumOfParts = 0;

        List<Character> charsAround;
        for (int i=0; i<middleLine.length(); i++) {
            if (Character.isDigit(middleLine.charAt(i))){
                if (!inANumberSequence) {
                    numberStart = i;
                    System.out.println("numberStart = " + numberStart);
                }

                inANumberSequence = true;
                charsAround = charsAround(threeLines, i);
                System.out.println("For char: " + middleLine.charAt(i));
                for (char c : charsAround) {
                    System.out.print(c);
                    System.out.println("------");
                }

                if (!isAPart){
                    isAPart = hasNonDigitSymbolAround(charsAround);
                }
                System.out.println("isAPart: " + isAPart);
            } else if (inANumberSequence) {
                numberEnd = i;
                if (isAPart) {
                    number = Integer.parseInt(middleLine.substring(numberStart, numberEnd));
                    sumOfParts += number;
                    System.out.println("Number: " + number);
                    System.out.println("SumOfParts: " + sumOfParts);
                    isAPart = false;
                }
                inANumberSequence = false;
            }

        }

        return sumOfParts;
    }

    private static boolean notAPoint(Character c) {
        return !c.equals('.');
    }

    private static boolean hasNonDigitSymbolAround(List<Character> charsAround) {
        for (char c : charsAround) {
            if (notAPoint(c))
                return true;
        }
        return false;
    }

    private static List<Character> charsAround(List<String> threeStrings, int indexOfChar){
        List<Character> charsAround = new ArrayList<>();
        int leftColumn = indexOfChar -1;
        int rightColumn = indexOfChar +1;

        if (leftColumn >= 0){
            charsAround.add(threeStrings.get(0).charAt(leftColumn));
            charsAround.add(threeStrings.get(1).charAt(leftColumn));
            charsAround.add(threeStrings.get(2).charAt(leftColumn));
        }
        charsAround.add(threeStrings.get(0).charAt(indexOfChar));
        charsAround.add(threeStrings.get(2).charAt(indexOfChar));
        if (rightColumn < threeStrings.get(0).length()) {
            charsAround.add(threeStrings.get(0).charAt(rightColumn));
            charsAround.add(threeStrings.get(1).charAt(rightColumn));
            charsAround.add(threeStrings.get(2).charAt(rightColumn));
        }

        return charsAround;
    }
}
