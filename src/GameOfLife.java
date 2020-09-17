import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
    public static void main(String[] args) {

        System.out.println("Welcome to my Game of Life");
        System.out.println("Please insert the height of the game field");
        Scanner readIn = new Scanner(System.in);
        //x Achse
        int vertical = readIn.nextInt();
        System.out.println("Please insert the width of the game field");
        //y Achse
        int horizontal = readIn.nextInt();
        //Create the matrix
        int [][] matrix = new int[vertical][horizontal];

        //fill the matrix with coin flip
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int coin = new Random().nextInt(2);
                matrix[i][j] = coin;
            }
        }

        boolean play = true;
        //print matrix
        printTheMatrix(matrix);

        while (play == true) {

            //make a newMatrix with the GameOfLife Conditions
            int[][] newMatrix = new int[matrix.length][matrix[0].length];

            //tell me how many neighbours I have
            //and implement a new matrix
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (i == 0 && j == 0) {//bestimmung der Nachbarn im ersten Feld. Ecke rechts oben
                        int m11 = matrix[0][1] + matrix[1][1] + matrix[1][0];
                        if (m11 == 3 || m11 == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    if ((i == 0) && j > 0 && j < matrix[i].length - 1) {//erste Zeile ohne Ecken
                        int m1undhoeher = matrix[0][j - 1] + matrix[0][j + 1] //vor und danach in der ersten Zelle
                                + matrix[1][j - 1] + matrix[1][j] + matrix[1][j + 1]; //Nachbarn in der zweiten Zelle
                        if (m1undhoeher == 3 || m1undhoeher == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    if (i == 0 && j == matrix[i].length - 1) {//Ecke rechts oben
                        int m13 = matrix[0][matrix[i].length - 2] + matrix[1][matrix[i].length - 2] + matrix[1][matrix[i].length - 1];
                        if (m13 == 3 || m13 == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    if (i == matrix.length - 1 && j == 0) {//Ecke links unten 71
                        int m71 = matrix[matrix.length - 1][1] + matrix[matrix.length - 2][1] + matrix[matrix.length - 2][0];
                        if (m71 == 3 || m71 == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;
                    }

                    if (i == matrix.length - 1 && j == matrix[i].length - 1) {//Ecke rechts unten
                        int m73 = matrix[matrix.length - 1][matrix[i].length - 2] + matrix[matrix.length - 2][matrix[i].length - 2] + matrix[matrix.length - 2][matrix[i].length - 1];
                        if (m73 == 3 || m73 == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    if (i == matrix.length - 1 && j > 0 && j < matrix[i].length - 1) {//unterste Zeile ohne Ecken 72
                        int lastrowmiddle = matrix[matrix.length - 1][j - 1] + matrix[matrix.length - 1][j + 1] //vor und danach in der untersten Zelle
                                + matrix[matrix.length - 2][j - 1] + matrix[matrix.length - 2][j] + matrix[matrix.length - 2][j + 1]; //Nachbarn in der zweiten von unten Zelle
                        if (lastrowmiddle == 3 || lastrowmiddle == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    if (i >= 1 && i < matrix.length - 1 && j == 0) {//erste Spalte ohne Ecken 21+31+41+51+61
                        int firstcolumn = matrix[i - 1][0] + matrix[i + 1][0] //Nachbarn oberhalb und unterhalb
                                + matrix[i - 1][1] + matrix[i][1] + matrix[i + 1][1]; //Nachbarn der nächsten Spalte
                        if (firstcolumn == 3 || firstcolumn == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    if (i >= 1 && i < matrix.length - 1 && j == matrix[i].length - 1) {//letzte Spalte ohne Ecken 23+33+43+53+63
                        int lastcolumn = matrix[i - 1][matrix[i].length - 1] + matrix[i + 1][matrix[i].length - 1] //Nachbarn oberhalb und unterhalb
                                + matrix[i - 1][matrix[i].length - 2] + matrix[i][matrix[i].length - 2] + matrix[i + 1][matrix[i].length - 2]; //Nachbarn der vorderen Spalte
                        if (lastcolumn == 3 || lastcolumn == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }
                    //and all the others numbers without corners or anything
                    if (i >= 1 && i < matrix.length - 1 && j > 0 && j < matrix[i].length - 1) {
                        int middle = matrix[i - 1][j] + matrix[i + 1][j]
                                + matrix[i - 1][j + 1] + matrix[i - 1][j] + matrix[i - 1][j - 1]
                                + matrix[i + 1][j + 1] + matrix[i + 1][j] + matrix[i + 1][j - 1];
                        if (middle == 3 || middle == 2) {
                            newMatrix[i][j] = 1;
                        } else newMatrix[i][j] = 0;

                    }

                }

            }


            printTheMatrix(newMatrix);
            matrix = newMatrix;
            System.out.println("Do you want to play again?");
            System.out.println("Press 'y' for yess, any other key to quiet the game");
            String playAgain = readIn.next();

            //ask the user, if he wants to play again
            if (playAgain.equals("y")){
                play = true;
            }else play = false;

    }

}

    // new methods here to follow
    //methode um die Zweidimensionale Matrix zu drucken
    private static void printTheMatrix(int[][] printMatrix) {
        for (int i = 0; i < printMatrix.length; i++) {
            for (int j = 0; j < printMatrix[i].length; j++) {
                System.out.print(printMatrix[i][j] + " ");
                if ((j + 1) % printMatrix[i].length == 0) {
                    System.out.println();
                }
            }
        }
        System.out.println();
    }
}

