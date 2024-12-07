import java.io.*;
import java.util.*;

public class Day6Part1 {
    static LinkedList<LinkedList<String>> map = new LinkedList();

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
                    LinkedList<String> temp = new LinkedList<String>();
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
        int totalDistinctMoves = 0;
        while (r < map.size() || c < map.get(0).size()) {
            String current = map.get(r).get(c);
            if (getOrientation(r, c).equals("up") && !map.get(r + 1).get(c).equals("#")) {
                r++;
                System.out.println(getOrientation(r, c));
                if (!current.equals("X")) {
                    totalDistinctMoves++;
                }
                map.get(r).set(c, "X");
            } else if (getOrientation(r, c).equals("down") && !map.get(r - 1).get(c).equals("#")) {
                r--;
                System.out.println(getOrientation(r, c));
                if (!current.equals("X")) {
                    totalDistinctMoves++;
                }
                map.get(r).set(c, "X");

            } else if (getOrientation(r, c).equals("right") && !map.get(r).get(c + 1).equals("#")) {
                c++;
                System.out.println(getOrientation(r, c));
                if (!current.equals("X")) {
                    totalDistinctMoves++;
                }
                map.get(r).set(c, "X");

            } else if (getOrientation(r, c).equals("left") && !map.get(r).get(c - 1).equals("#")) {
                c--;
                System.out.println(getOrientation(r, c));
                if (!current.equals("X")) {
                    totalDistinctMoves++;
                }
                map.get(r).set(c, "X");
            } else {
                turn(r, c);
            }
        }
        return totalDistinctMoves;
    }

    public static String getOrientation(int r, int c) {
        String orientation = "";
        String current = map.get(r).get(c);
        if (current.equals("^")) {
            orientation = "up";
        } else if (current.equals(">")) {
            orientation = "right";
        } else if (current.equals("<")) {
            orientation = "left";
        } else {
            orientation = "down";
        }
        return orientation;
    }

    public static String turn(int r, int c) {
        String current = map.get(r).get(c);
        String orientation = "";
        if (current.equals("^")) {
            orientation = ">";
        } else if (current.equals(">")) {
            orientation = "V";
        } else if (current.equals("<")) {
            orientation = "<";
        } else {
            orientation = "^";
        }
        return orientation;
    }

}