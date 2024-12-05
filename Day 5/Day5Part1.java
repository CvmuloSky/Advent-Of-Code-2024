import java.io.*;
import java.util.*;

public class Day5Part1 {
    static HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        String fileName = "in.txt";
        int midSum = 0;
        LinkedList<Integer> list = new LinkedList<>();
        String[] pageOrder = new String[0];
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null && line.indexOf("|") != -1) {
                    String[] lines = line.split("\\|");
                    LinkedList<Integer> temp = new LinkedList<>();
                    if (!map.containsKey(Integer.parseInt(lines[0]))) {
                        temp.add(Integer.parseInt(lines[1]));
                        map.put(Integer.valueOf(lines[0]), temp);
                    } else {
                        map.get(Integer.parseInt(lines[0])).add(Integer.parseInt(lines[1]));
                    }
                }
                while ((line = br.readLine()) != null && line.indexOf(",") != -1) {
                    pageOrder = line.split("\\,");
                    // System.out.println(pageOrder.length);
                    if (isValid(pageOrder)) {
                        System.out.println(Integer.parseInt(pageOrder[pageOrder.length / 2]));
                        midSum += Integer.parseInt(pageOrder[pageOrder.length / 2]);
                    }
                }
            }
            System.out.println(map);
            System.out.println("Mid sum: " + midSum);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static boolean isValid(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int curr = Integer.parseInt(arr[i]);
            for (int j = 0; j < i; j++) {
                int prev = Integer.parseInt(arr[j]);
                LinkedList<Integer> afters = map.get(curr);
                if (afters != null && afters.contains(prev)) {
                    return false;
                }
            }
        }
        return true;
    }
}