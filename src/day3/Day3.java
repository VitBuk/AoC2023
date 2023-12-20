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
        }

        return sumOfParts;
    }

    private static int sumOfPartsForLine(List<String> threeLines){
        String upLine = threeLines.get(0);
        String middleLine = threeLines.get(1);
        String downLine = threeLines.get(2);

        boolean inANumberSequence = false;
        boolean isAPart = false;
        int number=0;
        int numberStart=-1;
        int numberEnd=-1;

        List<Character> charsAround = new ArrayList<>();
        for (int i=0; i<middleLine.length(); i++) {
            if (Character.isDigit(middleLine.charAt(i))){
                if (!inANumberSequence) {
                    numberStart = i;
                }

                hasNonDigitSymbolAround(charsAround);
            }
            if(!inANumberSequence) {
                if (Character.isDigit(middleLine.charAt(i))){
                    numberStart = i;
                }
                if ()
            }
           if (Character.isDigit(middleLine.charAt(i))){
               numberStart = i;
           }
        }
    }

    private static boolean isNonDigitSymbol(Character c) {
        return !Character.isDigit(c) && !c.equals('.');
    }

    private static boolean hasNonDigitSymbolAround(List<Character> charsAround) {
        for (char c : charsAround) {
            if (isNonDigitSymbol(c))
                return true;
        }
        return false;
    }

    private static List<Character> charsAround(List<String> threeStrings, int indexOfChar){
        List<Character> charsAround = new ArrayList<>();
        int leftColumn = indexOfChar -1;
        int sameColumn = indexOfChar;
        int rightColumn = indexOfChar +1;

        if (leftColumn >= 0){
            charsAround.add(threeStrings.get(0).charAt(leftColumn));
            charsAround.add(threeStrings.get(1).charAt(leftColumn));
            charsAround.add(threeStrings.get(2).charAt(leftColumn));
        }
        charsAround.add(threeStrings.get(0).charAt(sameColumn));
        charsAround.add(threeStrings.get(2).charAt(sameColumn));
        if (rightColumn <= threeStrings.get(0).length()) {
            charsAround.add(threeStrings.get(0).charAt(rightColumn));
            charsAround.add(threeStrings.get(1).charAt(rightColumn));
            charsAround.add(threeStrings.get(2).charAt(rightColumn));
        }
    }
}
