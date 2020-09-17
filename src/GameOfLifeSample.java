import java.util.Random;
public class GameOfLifeSample {

        private static final int WIDTH = 8;
        private static final int HEIGHT = 4;
        private static final String[][] gamefield = new String[WIDTH][HEIGHT];
        // positions to check
        private static final int[][] NEIGHBOURS = {
                {0, -1},    // NORTH
                {-1, -1},   // NORTHWEST
                {+1, -1},   // NORTHEAST
                {0, +1},    // SOUTH
                {-1, +1},   // SOUTHWEST
                {+1, +1},   // SOUTHEAST
                {+1, 0},    // EAST
                {-1, 0}     // WEST
                //   {+1, +1}       myself
        };

        private static void initializeField() {
            for (int i = 0; i < gamefield.length; i++) {
                for (int j = 0; j < gamefield[i].length; j++) {
                    // coin flip
                    int alive = new Random().nextInt(2);
                    if (alive == 0) {
                        gamefield[i][j] = ".";
                    } else {
                        gamefield[i][j] = "*";
                    }
                }
            }
        }

        private static void printField() {
            for (String[] array : gamefield) {
                System.out.print("|");
                for (String s : array) {
                    System.out.print(s + " ");
                }
                System.out.print("|");
                System.out.println();
            }
        }

        private static boolean isAlive(int x, int y) {
            // check for memory validation (index out of bounds)
            if (x < WIDTH && y < HEIGHT && x > -1 && y > -1) {
                return gamefield[x][y].equals("*");
            }
            return false;
        }

        private static int countNeighbors(int x, int y) {
            int count = 0;
            for (int[] offset : NEIGHBOURS) {
                if (isAlive(x + offset[0], y + offset[1])) {
                    count++;
                }
            }
            return count;
        }

        private static void play(int rounds) {
            int round = 0;
            while (round <= rounds) {
                for (int i = 0; i < gamefield.length; i++) {
                    for (int j = 0; j < gamefield[i].length; j++) {
                        int neighbors = countNeighbors(i, j);
                        // first rule
                        if (neighbors == 3) gamefield[i][j] = "*";
                        // second rule
                        if (neighbors < 2) gamefield[i][j] = ".";
                        // third rule
                        if (neighbors == 2) gamefield[i][j] = "*";
                        // fourth rule
                        if (neighbors > 3) gamefield[i][j] = ".";
                    }
                }
                round++;
            }
        }

        public static void main(String[] args) {
            initializeField();
            printField();
            play(3);
            System.out.println("==========");
            printField();
        }
    }

