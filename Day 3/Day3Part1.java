import java.io.*;
import java.util.regex.*;

public class Day3Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Day 3/in.txt"));
        StringBuilder in = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            in.append(line);
        }
        reader.close();

        String corruptedMemory = in.toString();
        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(corruptedMemory);

        int sum = 0;
        while (matcher.find()) {
            int num1 = Integer.parseInt(matcher.group(1));
            int num2 = Integer.parseInt(matcher.group(2));
            sum += num1 * num2;
        }

        System.out.println("Sum of all valid mul instructions: " + sum);
    }
}
