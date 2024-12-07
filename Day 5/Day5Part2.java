import java.io.*;
import java.util.*;

public class Day5Part2 {
    static HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
    public static void main(String[] args) {
        String fileName = "Day 5/in.txt";
        int midSum = 0;
        int fixedMidSum = 0;
        LinkedList<Integer> list = new LinkedList<>();
        String[] pageOrder = new String[0];
        
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                // Parse the rules
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
                
                // Parse the updates
                while ((line = br.readLine()) != null && line.indexOf(",") != -1) {
                    pageOrder = line.split(",");
                    if (isValid(pageOrder)) {
                        midSum += getMid(pageOrder);
                    } else {
                        String[] fixedOrder = fixOrder(pageOrder);
                        fixedMidSum += getMid(fixedOrder);
                    }
                }
            }
            System.out.println("Mid sum of valid updates: " + midSum);
            System.out.println("Mid sum of fixed updates: " + fixedMidSum);
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

    public static String[] fixOrder(String[] arr) {
        List<Integer> correctedList = new ArrayList<>(); 
        for (String s : arr) {
            int curr = Integer.parseInt(s);
            int insertPosition = correctedList.size();
            for (int j = 0; j < correctedList.size(); j++) {
                int prev = correctedList.get(j);
                LinkedList<Integer> afters = map.get(prev);
                    if (afters != null && afters.contains(curr)) {
                    insertPosition = j;
                    break;
                }
            }
                correctedList.add(insertPosition, curr);
        }
        String[] corrected = new String[correctedList.size()];
        for (int i = 0; i < correctedList.size(); i++) {
            corrected[i] = String.valueOf(correctedList.get(i));
        }
        return corrected;
    }
    

    public static int getMid(String[] arr) {
        return Integer.parseInt(arr[arr.length / 2]);
    }
}
