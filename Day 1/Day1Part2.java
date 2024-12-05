import java.io.*;
import java.util.*;
public class Day1Part2 {
    public static void main(String[] args) {
        String filePath = "in.txt";
        try {
            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();
            readInputFile(filePath, leftList, rightList);
            int similarityScore = calculateSimilarityScore(leftList, rightList);
            System.out.println("Similarity Score: " + similarityScore);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
    private static void readInputFile(String filePath, List<Integer> leftList, List<Integer> rightList) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    leftList.add(Integer.parseInt(parts[0]));
                    rightList.add(Integer.parseInt(parts[1]));
                }
            }
        }
    }
    private static int calculateSimilarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : rightList) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        int totalScore = 0;
        for (int num : leftList) {
            int countInRightList = frequencyMap.getOrDefault(num, 0);
            totalScore += num * countInRightList;
        }
        return totalScore;
    }
}
