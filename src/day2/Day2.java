package day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    private static final String RED = "red";
    private static final String BLUE = "blue";
    private static final String GREEN = "green";
    private static final int RED_COUNT = 12;
    private static final int BLUE_COUNT = 14;
    private static final int GREEN_COUNT = 13;

    public static int answer() {
        int sumOfGameIDs = 0;
        try {
            FileInputStream fis = new FileInputStream("src/day2/values.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                sumOfGameIDs += getValue(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sumOfGameIDs;
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
        System.out.println("checkOneTry line: " + line);
        int amount;
        String color;
        String[] split = line.split(", ");
        for (String s: split) {
            System.out.println(s);
        }
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

    private static int powerOfSet(){
        return 0;
    }
}
