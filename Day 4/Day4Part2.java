import java.io.*;
import java.util.*;

public class Day4Part2 {
    public static void main(String[] args) {
        String fileName = "Day 4/inreal.txt";
        int count = 0;
        try {
            List<List<String>> lists = new LinkedList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    List<String> list = new ArrayList<>();
                    String[] parts = line.split("(?<=.)");
                    Collections.addAll(list, parts);
                    lists.add(list);
                }
            }
            
            for (int r = 1; r < lists.size() - 1; r++) {
                for (int c = 1; c < lists.get(r).size() - 1; c++) {
                    count += isXMAS(r, c, lists);
                }
            }
            System.out.println("X-MAS COUNT: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static int isXMAS(int row, int col, List<List<String>> list) {
        int count = 0;

        if (matchesMAS(row - 1, col - 1, list) &&
            matchesA(row, col, list) &&
            matchesMAS(row + 1, col + 1, list)) {
            count++;
        }
        if (matchesMAS(row - 1, col + 1, list) &&
            matchesA(row, col, list) &&
            matchesMAS(row + 1, col - 1, list)) {
            count++;
        }

        return count;
    }

    private static boolean matchesMAS(int row, int col, List<List<String>> list) {
        if (row < 0 || col < 0 || row >= list.size() || col + 2 >= list.get(row).size()) {
            return false;
        }
        String pattern = list.get(row).get(col) + list.get(row).get(col + 1) + list.get(row).get(col + 2);
        return pattern.equals("MAS") || pattern.equals("SAM");
    }

    private static boolean matchesA(int row, int col, List<List<String>> list) {
        if (row < 0 || col < 0 || row >= list.size() || col >= list.get(row).size()) {
            return false;
        }
        return list.get(row).get(col).equals("A");
    }
}
