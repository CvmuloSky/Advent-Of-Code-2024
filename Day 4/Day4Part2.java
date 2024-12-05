import java.io.*;
import java.util.*;

public class Day4Part2 {
    public static void main(String[] args) {
        String fileName = "Day 4/inreal.txt";
        int count = 0;

        try {
            List<List<String>> list = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    List<String> row = Arrays.asList(line.split(""));
                    list.add(row);
                }
            }

            for (int r = 1; r < list.size() - 1; r++) {
                for (int c = 1; c < list.get(r).size() - 1; c++) {
                    if (list.get(r).get(c).equals("A")) {
                        count += hasX_MAS(r, c, list);
                    }
                }
            }
            System.out.println("X-MAS COUNT: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static int hasX_MAS(int row, int col, List<List<String>> list) {
        int count = 0;
        boolean TLBR = isMAS(list, row - 1, col - 1, row, col, row + 1, col + 1);
        boolean TRBL = isMAS(list, row - 1, col + 1, row, col, row + 1, col - 1);
        if(TLBR && TRBL) {
            count++;
        }

        return count;
    }

    private static boolean isMAS(List<List<String>> list, int r1, int c1, int r2, int c2, int r3, int c3) {
        if (inBounds(list, r1, c1) && inBounds(list, r2, c2) && inBounds(list, r3, c3)) {
            String pattern = list.get(r1).get(c1) + list.get(r2).get(c2) + list.get(r3).get(c3);
            return pattern.equals("MAS") || pattern.equals("SAM");
        }
        return false;
    }

    private static boolean inBounds(List<List<String>> list, int row, int col) {
        return row >= 0 && row < list.size() && col >= 0 && col < list.get(row).size();
    }
}
