import java.io.*;
import java.util.*;
public class Day1Part1 {
    public static void main(String[] args) {
        String filePath = "in.txt";
        try {
            List<Integer> leftList = new ArrayList<>();
            List<Integer> rightList = new ArrayList<>();
            readInputFile(filePath, leftList, rightList);

            int[] leftArray = new int[leftList.size()];
            int[] rightArray = new int[rightList.size()];
            for (int i = 0; i < leftList.size(); i++) {
                leftArray[i] = leftList.get(i);
            }
            for (int i = 0; i < rightList.size(); i++) {
                rightArray[i] = rightList.get(i);
            }

            int totalDistance = calculateTotalDistance(leftArray, rightArray);
            System.out.println("Total Distance: " + totalDistance);
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
    private static int calculateTotalDistance(int[] leftList, int[] rightList) {
        Arrays.sort(leftList);
        Arrays.sort(rightList);
        int totalDistance = 0;
        for (int i = 0; i < leftList.length; i++) {
            totalDistance += Math.abs(leftList[i] - rightList[i]);
        }
        return totalDistance;
    }
}
