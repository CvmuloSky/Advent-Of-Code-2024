import java.io.*;
import java.util.*;
public class Day2Part1 {
    public static void main(String[] args) {
        String fileName = "in.txt";
        try {
            List<List<Integer>> lists = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    List<Integer> list = new ArrayList<>();
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        list.add(Integer.parseInt(part));
                    }
                    lists.add(list);
                }
            }
            int safeCount = 0;
            for (List<Integer> list : lists) {
                if (isSafe(list)) {
                    safeCount++;
                }
            }
            System.out.println("Number of safe lists: " + safeCount);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
    private static boolean isSafe(List<Integer> list) {
        if (list.size() < 2)
            return false;
        List<Integer> differences = new ArrayList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            int diff = list.get(i + 1) - list.get(i);
            differences.add(diff);
        }
        for (int diff : differences) {
            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }
        }
        boolean increasing = true;
        boolean decreasing = true;
        for (int diff : differences) {
            if (diff <= 0)
                increasing = false;
            if (diff >= 0)
                decreasing = false;
        }
        return increasing || decreasing;
    }
}
