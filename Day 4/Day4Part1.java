import java.io.*;
import java.util.*;

public class Day4Part1 {
    public static void main(String[] args) {
        String fileName = "Day 4/inreal.txt";
        int count = 0;
        try {
            List<List<String>> lists = new LinkedList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    List<String> list = new ArrayList<>();
                    String[] parts = line.split("");
                    Collections.addAll(list, parts);
                    lists.add(list);
                }
            }
            for (int r = 0; r < lists.size(); r++) {
                for (int c = 0; c < lists.get(r).size(); c++) {
                    count += isXMAS(r, c, lists);
                }
            }
            System.out.println("XMAS COUNT: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static int isXMAS(int row, int col, List<List<String>> list) {
        int count = 0;
        if (list.get(row).get(col).equals("X")) {
            if (up(row, col, list)) {
                count++;
            }
            if (down(row, col, list)) {
                count++;
            }
            if (left(row, col, list)) {
                count++;
            }
            if (right(row, col, list)) {
                count++;
            }
            if (upLeft(row, col, list)) {
                count++;
            }
            if (upRight(row, col, list)) {
                count++;
            }
            if (downLeft(row, col, list)) {
                count++;
            }
            if (downRight(row, col, list)) {
                count++;
            }
        }
        return count;
    }

    private static boolean up(int row, int col, List<List<String>> list) {
        if (row < 3) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int r = 0; r <= 3; r++) {
            temp.append(list.get(row - r).get(col));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean down(int row, int col, List<List<String>> list) {
        if (row + 3 >= list.size()) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int r = 0; r <= 3; r++) {
            temp.append(list.get(row + r).get(col));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean left(int row, int col, List<List<String>> list) {
        if (col < 3) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int c = 0; c <= 3; c++) {
            temp.append(list.get(row).get(col - c));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean right(int row, int col, List<List<String>> list) {
        if (col + 3 >= list.get(row).size()) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int c = 0; c <= 3; c++) {
            temp.append(list.get(row).get(col + c));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean upLeft(int row, int col, List<List<String>> list) {
        if (row < 3 || col < 3) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            temp.append(list.get(row - i).get(col - i));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean upRight(int row, int col, List<List<String>> list) {
        if (row < 3 || col + 3 >= list.get(row).size()) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            temp.append(list.get(row - i).get(col + i));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean downLeft(int row, int col, List<List<String>> list) {
        if (row + 3 >= list.size() || col < 3) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            temp.append(list.get(row + i).get(col - i));
        }
        return temp.toString().equals("XMAS");
    }

    private static boolean downRight(int row, int col, List<List<String>> list) {
        if (row + 3 >= list.size() || col + 3 >= list.get(row).size()) {
            return false;
        }
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            temp.append(list.get(row + i).get(col + i));
        }
        return temp.toString().equals("XMAS");
    }
}
