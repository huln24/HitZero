package HitZeroVersions;

import java.util.Scanner;

public class RecursiveHitZero {

    public static void main(String[] args) {
        gameTest();

        boolean solvable = false;
        int[] test = { 1, 2, 3, 2, 0 };
        solvable = recursiveHitZero(test, 0);
        if (solvable) {
            System.out.println("Solvable");
        } else {
            System.out.println("Not solvable\n");
        }
    }

    // Takes array of integers of the game and starting index of the game
    private static boolean recursiveHitZero(int[] A, int i) {
        // Empty array of same size as game array, content at index i to be updated when
        // we visit that position
        int[] B = new int[A.length];
        // Returns the result of the recursive function
        return recursiveHitZero_(A, i, B);
    }

    // The recursive function to play the game
    // Checks if we return to already visited position, so we know whether game is
    // solvable or not
    private static boolean recursiveHitZero_(int[] A, int i, int[] B) {
        int size = A.length;
        printGameBoard(A);
        printMarker(A, i);
        // If we already visited the position i (B[i] = 1),
        // we know that game is unsolvable because we're returning to where we were
        // before
        if (B[i] == 1) {
            return false;
        }
        B[i] = 1; // Mark that we are visiting this position
        if (A[i] == 0) { // We reach position with value 0, hence end of the game
            return true;
        } else if (A[i] + i <= size - 1) { // If we can move to the right, we move to the right by A[i]
            return recursiveHitZero_(A, A[i] + i, B);
        } else if (i - A[i] >= 0) { // else if we can move to the left, move to the left by A[i]
            return recursiveHitZero_(A, i - A[i], B);
        }
        return false; // We're stuck at current position, because we can't move to the both directions
                      // by A[i]. Hence, game unsolvable
    }

    // Tests each game cases with given starting position
    private static void gameTest() {
        // Test Cases
        int[][] game = {
                { 4, 8, 5, 2, 3, 5, 1, 6, 4, 0 },
                { 5, 8, 2, 3, 1, 5, 0 },
                { 5, 8, 2, 3, 1, 5, 0 },
                { 4, 9, 2, 1, 5, 8, 3, 5, 6, 7, 0 },
                { 1, 0 },
                { 3, 0 },
                { 24, 11, 8, 18, 8, 20, 22, 10, 13, 16, 7, 10, 3, 7, 16, 24, 8, 2, 16, 5, 6, 19, 17, 11, 0 },
                { 24, 11, 8, 18, 8, 20, 22, 10, 13, 16, 7, 10, 3, 7, 16, 24, 8, 2, 16, 5, 6, 19, 17, 11, 0 },
                { 3, 1, 2, 9, 8, 7, 2, 6, 0 },
                { 7, 9, 5, 7, 6, 3, 7, 4, 6, 0 },
                { 7, 8, 6, 2, 9, 7, 3, 5, 0 },
                { 2, 2, 0 },
                { 2, 2, 0 },
                { 4, 6, 3, 2, 2, 9, 10, 1, 6, 0 },
                { 4, 5, 3, 3, 0 },
                { 4, 5, 3, 3, 0 },
                { 1, 2, 3, 4, 5, 3, 0 },
                { 3, 2, 1, 2, 0 },
                { 2, 4, 2, 1, 2, 0 },
                { 1, 4, 3, 3, 3, 6, 2, 7, 5, 0 }
        };

        // Starting positions
        int[] positions = { 0, 5, 3, 2, 0, 0, 24, 2, 4, 5, 0, 0, 1, 8, 1, 3, 4, 3, 1, 8 };

        // Expected results, good thing
        boolean[] expected = { true, false, true, false, true, false, false, true, false, false, true, true,
                false, false, false, true, false, false, true, false };

        // Initialization of Scanner object
        Scanner keyIn = new Scanner(System.in);
        boolean solvable = false; // Stores value returned by recursive function, whether game is solvable or not

        // Start playing the game
        for (int i = 0; i < game.length; i++) {
            System.out.println("--------------------------------------");
            System.out.println(" Game " + (i + 1) + " is starting...");
            System.out.println("--------------------------------------");
            System.out.println("Here is the game board: \n");
            printGameBoard(game[i]);
            System.out.println("\nThe starting position is " + positions[i]);
            System.out.println("\nGame Start!");
            printGameBoard(game[i]);
            printMarker(game[i], positions[i]);
            solvable = recursiveHitZero(game[i], positions[i]);
            if (solvable) {
                System.out.println("Game is solvable!\n");
            } else
                System.out.println("Game is not solvable!\n");

            System.out.println("----------------------------------------------------");
            if (solvable == expected[i]) {
                System.out.println("All tests passed!\n");
            } else {
                System.out.println("Tests failed!\n");
            }
        }

        keyIn.close();
    }

    // Prints position marker
    private static void printMarker(int[] game, int index) {
        for (int i = 0; i < index; i++) {
            System.out.print("    ");

        }
        System.out.println("^");
    }

    // Prints game board
    private static void printGameBoard(int[] game) {
        for (int j = 0; j < game.length; j++) {
            if (j < game.length - 1)
                System.out.print(game[j] + " | ");
            else {
                System.out.println(game[j]);
            }
        }
    }

}
