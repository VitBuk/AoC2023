package day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Day4 {

    public static void answer() {
        try {
            FileInputStream fis = new FileInputStream("src/day4/values.txt");
            Scanner sc = new Scanner(fis);
            List<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }

            System.out.println("Sum of Points: " + sumOfPoints(lines));
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int sumOfPoints(List<String> lines) {
        int sumOfPoints = 0;

        for (String line : lines) {
            String[] strCard = line.split(": ");
            System.out.println("StrCard[0]: " + strCard[0]);
            System.out.println("StrCard[1]: " + strCard[1]);

            String[] strNumbers = strCard[1].split("\\| ");
            String[] strWinNumbers = strNumbers[0].split(" ");
            String[] strPlayerNumbers = strNumbers[1].split(" ");
            Set<Integer> winNumbers = new HashSet<>();
            Set<Integer> playerNumbers = new HashSet<>();
            int linePoints = 0;

            for (String s1 : strWinNumbers) {
                if (!s1.equals("")) {
                    if (s1.charAt(0) == ' ')
                        winNumbers.add(Integer.parseInt(s1.substring(1, 2)));
                    else
                        winNumbers.add(Integer.parseInt(s1));
                }
            }

            for (String s2 : strPlayerNumbers) {
                if (!s2.equals("")) {
                    if (s2.charAt(0) == ' ')
                        playerNumbers.add(Integer.parseInt(s2.substring(1, 2)));
                    else
                        playerNumbers.add(Integer.parseInt(s2));
                }
            }

            System.out.print("Contains: ");
            for (int i : playerNumbers) {
                if (winNumbers.contains(i)) {
                    System.out.print(i + ",");
                    if (linePoints == 0)
                        linePoints = 1;
                    else
                        linePoints *= 2;
                }
            }

            sumOfPoints += linePoints;
            System.out.println("LinePoints: " + linePoints);
            System.out.println("SumOfPoints: = " + sumOfPoints);
            System.out.println("-----");
        }

        return sumOfPoints;
    }
}
