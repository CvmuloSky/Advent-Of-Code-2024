import java.io.*;
import java.util.regex.*;

public class Day3Part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("Day 3/in.txt"));
        StringBuilder in = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            in.append(line);
        }
        reader.close();
        String sigma = in.toString();
        Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Pattern controlPattern = Pattern.compile("do\\(\\)|don't\\(\\)");
        Matcher mulMatcher = mulPattern.matcher(sigma);
        Matcher controlMatcher = controlPattern.matcher(sigma);
        boolean isEnabled = true;
        int sum = 0;
        int currentIndex = 0;

        while (mulMatcher.find()) {
            while (controlMatcher.find(currentIndex) && controlMatcher.start() < mulMatcher.start()) {
                String controlInstruction = controlMatcher.group();
                isEnabled = controlInstruction.equals("do()");
                currentIndex = controlMatcher.end();
            }

            if (isEnabled) {
                int num1 = Integer.parseInt(mulMatcher.group(1));
                int num2 = Integer.parseInt(mulMatcher.group(2));
                sum += num1 * num2;
            }
        }
        System.out.println("Sum of all valid mul instructions: " + sum);
    }
}
