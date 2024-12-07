import java.io.*;
import java.util.*;

public class Day6Part1 {
    static LinkedList<LinkedList<String>> map = new LinkedList<>();

    public static void main(String[] args) {
        int sigma = 0;
        int col = 0;
        int row = 0;
        String fileName = "in.txt";
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] lineChars = line.split("");
                    LinkedList<String> temp = new LinkedList<>();
                    for (int i = 0; i < lineChars.length; i++) {
                        temp.add(lineChars[i]);
                        if (lineChars[i].equals("^")) {
                            col = i;
                            row = sigma;
                        }
                    }
                    map.add(temp);
                    sigma++;
                }
            }
            System.out.println("Total Moves: " + totalMoves(row, col));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static int totalMoves(int r, int c) {
        Set<String> visitedPositions = new HashSet<>();
        Set<String> positionDirectionVisited = new HashSet<>();
        String direction = "^";
        int totalDistinctMoves = 0;

        while (true) {
            String currentPosition = r + "," + c;
            String currentPosDir = currentPosition + "," + direction;

            if (positionDirectionVisited.contains(currentPosDir)) {
                break;
            }
            positionDirectionVisited.add(currentPosDir);

            if (!visitedPositions.contains(currentPosition)) {
                visitedPositions.add(currentPosition);
                totalDistinctMoves++;
            }

            int[] nextPos = getNextPosition(r, c, direction);
            int nextR = nextPos[0];
            int nextC = nextPos[1];

            if (canMove(nextR, nextC)) {
                r = nextR;
                c = nextC;
            } else {
                direction = turnRight(direction);
            }

            if (isTrapped(r, c)) {
                break;
            }
        }
        return totalDistinctMoves;
    }

    public static int[] getNextPosition(int r, int c, String direction) {
        switch (direction) {
            case "^":
                return new int[] { r - 1, c };
            case ">":
                return new int[] { r, c + 1 };
            case "v":
                return new int[] { r + 1, c };
            case "<":
                return new int[] { r, c - 1 };
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    public static String turnRight(String direction) {
        switch (direction) {
            case "^":
                return ">";
            case ">":
                return "v";
            case "v":
                return "<";
            case "<":
                return "^";
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    public static boolean canMove(int r, int c) {
        return isWithinBounds(r, c) && !map.get(r).get(c).equals("#");
    }

    public static boolean isWithinBounds(int r, int c) {
        return r >= 0 && r < map.size() && c >= 0 && c < map.get(0).size();
    }

    public static boolean isTrapped(int r, int c) {
        String[] directions = { "^", ">", "v", "<" };
        for (String direction : directions) {
            int[] nextPos = getNextPosition(r, c, direction);
            int nextR = nextPos[0];
            int nextC = nextPos[1];
            if (canMove(nextR, nextC)) {
                return false;
            }
        }
        return true;
    }
}
