package day3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Day3 {
    private static final int lineLength = 140;
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

            System.out.println("Sum of Parts: " + sumOfParts(lines));
            System.out.println("Sum of Gear Ratios: " + sumOfGearRatios(lines));
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
                }

                inANumberSequence = true;
                charsAround = charsAround(threeLines, i);

                if (!isAPart){
                    isAPart = hasNonDigitSymbolAround(charsAround);
                }

                if (i == lineLength-1 || !Character.isDigit(middleLine.charAt(i+1))) {
                    numberEnd = i+1;
                    if (isAPart) {
                        number = Integer.parseInt(middleLine.substring(numberStart, numberEnd));
                        sumOfParts += number;
                        isAPart = false;
                    }
                    inANumberSequence = false;
                }
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
            if (Character.isDigit(threeStrings.get(1).charAt(leftColumn)))
                charsAround.add('.');
            else
                charsAround.add(threeStrings.get(1).charAt(leftColumn));

            charsAround.add(threeStrings.get(2).charAt(leftColumn));
        }
            charsAround.add(threeStrings.get(0).charAt(indexOfChar));
            charsAround.add(threeStrings.get(2).charAt(indexOfChar));

        if (rightColumn < threeStrings.get(0).length()) {
            charsAround.add(threeStrings.get(0).charAt(rightColumn));

            if (Character.isDigit(threeStrings.get(1).charAt(rightColumn)))
                charsAround.add('.');
            else
                charsAround.add(threeStrings.get(1).charAt(rightColumn));

            charsAround.add(threeStrings.get(2).charAt(rightColumn));
        }

        return charsAround;
    }

    private static int sumOfGearRatios (List<String> lines){
        List<String> threeLines = new ArrayList<>();
        int sumOfGearRatios = 0;

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

            sumOfGearRatios += sumOfGearRatiosForLine(threeLines, i);
            threeLines.clear();
        }
        return sumOfGearRatios;
    }
    private static int sumOfGearRatiosForLine (List<String> threeLines, int middleLineIndex ){
        int sumOfGearRatiosForLine = 0;
        for (int i=0; i<threeLines.get(1).length(); i++){
            if (threeLines.get(1).charAt(i) == '*')
                sumOfGearRatiosForLine += getGearRatio(threeLines, middleLineIndex, i);
        }

        return sumOfGearRatiosForLine;
    }
    private static int getGearRatio(List<String> threeLines, int middleLineIndex, int gearIndex) {
        Set<Number> numbersInGear = new HashSet<>();
        String upperLine = threeLines.get(0);
        String middleLine = threeLines.get(1);
        String downLine = threeLines.get(2);
        int result = 0;

        for (int i=-1; i<2; i++ ) {
            if (Character.isDigit(upperLine.charAt(gearIndex + i)))
                numbersInGear.add(getNumberByItsDigit(upperLine, middleLineIndex, gearIndex + i));
            if (Character.isDigit(downLine.charAt(gearIndex + i)))
                numbersInGear.add(getNumberByItsDigit(downLine, middleLineIndex, gearIndex + i));
        }

        if (Character.isDigit(middleLine.charAt(gearIndex -1)))
            numbersInGear.add(getNumberByItsDigit(middleLine, middleLineIndex, gearIndex -1));
        if (Character.isDigit(middleLine.charAt(gearIndex + 1)))
            numbersInGear.add(getNumberByItsDigit(middleLine, middleLineIndex, gearIndex + 1));

        System.out.println("numbersInGear.size(): " + numbersInGear.size());
        if (numbersInGear.size() == 2){
            result=1;
            System.out.println("Result before for: " + result);
            for (Number number : numbersInGear) {
                System.out.println("Value of a number: " + number.getValue());
                result *= number.getValue();
            }

        }
        System.out.println("result: " + result);
        return result;
    }

    private static Number getNumberByItsDigit(String line, int lineIndex, int digitIndex) {
        int startIndex=digitIndex;
        int endIndex = digitIndex+1;
        for (int i=0; i<digitIndex; i++) {
            if (Character.isDigit(line.charAt(digitIndex-i))){
                startIndex = digitIndex-i;
            } else break;
        }

        for (int i=0; i<line.length()-digitIndex; i++) {
            if (Character.isDigit(line.charAt(digitIndex+i))){
                endIndex = digitIndex+i;
            } else break;

        }
        return new Number(lineIndex, startIndex, endIndex);
    }
}
