package HitZeroVersions;

import java.util.Scanner;
import java.util.Stack;

public class StackHitZero {

    public static void main(String[] args) {

        gameTest();

        boolean solvable = false;
        int[] test = { 2, 3, 1, 0 };
        solvable = stackHitZero(test, 0);
        if (solvable) {
            System.out.println("Solvable");
        } else {
            System.out.println("Not solvable\n");
        }
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

        // Expected results
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
            solvable = stackHitZero(game[i], positions[i]);
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

    // Plays the game and prints position of marker at each step
    // Returns true if game has a solution
    private static boolean stackHitZero(int[] game, int index) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        int[] visitedPos = new int[game.length]; // To keep track of positions we visit

        // Contains all the values to the left of current value
        for (int i = 0; i < index; i++) {
            left.push(game[i]);
        }

        // Contains all the values to the right of the current value
        for (int i = game.length - 1; i > index; i--) {
            right.push(game[i]);
        }

        int value = game[index]; // To store the current value where pointer is pointing
        int position = index; // To keep track of the index
        while (value != 0) {
            // Checks if we return to already visited position
            // Returns false, because the game is not solvable since we're turning in round
            if (visitedPos[position] == 1) {
                return false;
            }
            // Update visited position to mark that we visited it (=1)
            visitedPos[position] = 1;
            if (position + value < game.length) { // Move to the right if we can
                position = position + value;
                left.push(value);
                for (int i = 0; i < value; i++) {
                    left.push(right.pop());
                }
                value = left.pop();
                printGameBoard(game);
                printMarker(game, position);
                if (value == 0) {
                    return true;
                }
            } else if (position - value >= 0) { // Move to the left if we can
                position = position - value;
                right.push(value);
                for (int i = 0; i < value; i++) {
                    right.push(left.pop());
                }
                value = right.pop();
                printGameBoard(game);
                printMarker(game, position);
            } else {
                break; // We can't move to both side, since number of moves needed to perform is beyond
                       // the moves we can make to both right & left
            }
        }
        return false;
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
