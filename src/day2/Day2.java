package day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    private static final String RED = "red";
    private static final String BLUE = "blue";
    private static final String GREEN = "green";
    private static final int RED_COUNT = 12;
    private static final int BLUE_COUNT = 14;
    private static final int GREEN_COUNT = 13;

    public static void answer() {
        int sumOfGameIDs = 0;
        int sumOfPowers = 0;
        try {
            FileInputStream fis = new FileInputStream("src/day2/values.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                sumOfGameIDs += getValue(line);
                sumOfPowers  += powerOfSet(line);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("SumOfGameIDs: " + sumOfGameIDs);
        System.out.println("SumOfPowers: " + sumOfPowers);
    }

    private static int getValue(String line) {
        if (checkGame(line)) return getId(line);
        else return 0;
    }

    private static boolean checkGame(String line) {
        for (String s : getTries(line))
            if (!checkOneTry(s)) return false;

        return true;
    }
    private static String[] getTries(String line) {
        String withOutId = line.split(": ")[1];
        return withOutId.split("; ");
    }
    private static boolean checkOneTry(String line) {
        int amount;
        String color;
        String[] split = line.split(", ");
        for (String s : split) {
            amount = Integer.parseInt(s.split(" ")[0]);
            color = s.split(" ")[1];
            if (color.equals(BLUE) && amount > BLUE_COUNT) return false;
            if (color.equals(RED) && amount > RED_COUNT) return false;
            if (color.equals(GREEN) && amount > GREEN_COUNT) return false;
        }

        return true;
    }

    private static int getId(String line) {
        String[] split = line.split(":");
        String gameId = split[0];
        split = gameId.split(" ");
        System.out.println("getId: " + Integer.parseInt(split[1]));
        return Integer.parseInt(split[1]);
    }

    private static int powerOfSet(String line){
        int[] biggestOfGame = {0,0,0};
        int[] biggestOfTry;

        String[] tries = getTries(line);
        for (String s: tries) {
            biggestOfTry = biggestOfTry(s);
            if (biggestOfGame[0] < biggestOfTry[0] ) biggestOfGame[0] = biggestOfTry[0];
            if (biggestOfGame[1] < biggestOfTry[1] ) biggestOfGame[1] = biggestOfTry[1];
            if (biggestOfGame[2] < biggestOfTry[2] ) biggestOfGame[2] = biggestOfTry[2];
        }
        System.out.println("Red: " + biggestOfGame[0]);
        System.out.println("Green: " + biggestOfGame[1]);
        System.out.println("Blue: " + biggestOfGame[2]);
        System.out.println("-----");

        if (Arrays.stream(biggestOfGame).sum() == 0) {
            return 0;
        } else {
            int powerOfSet = 1;
            for (int i : biggestOfGame)
                if (i > 0) powerOfSet *= i;

            System.out.println("PowerOfSet: " + powerOfSet);
            return powerOfSet;
        }
    }

    private static int[] biggestOfTry(String line) {
        int[] colorValues= {0,0,0}; // RGB
        int amount = 0;
        String color = "";
        String[] pairs = line.split(", ");
        for (String s : pairs) {
            amount = Integer.parseInt(s.split(" ")[0]);
            color = s.split(" ")[1];
            if (color.equals(RED) && amount > colorValues[0]) colorValues[0] = amount;
            if (color.equals(GREEN) && amount > colorValues[1]) colorValues[1] = amount;
            if (color.equals(BLUE) && amount > colorValues[2]) colorValues[2] = amount;
        }

        return colorValues;
    }
}
