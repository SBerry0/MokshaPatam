import java.util.*;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Sohum Berry
 *
 */

public class MokshaPatam {
    private static int[] createSpecial(int[][] ladders, int[][] snakes, int end) {
        // Create a map for the special moves
        int[] outs = new int[end+1];

        for(int[] arr : ladders) {
            outs[arr[0]] = arr[1];
        }
        for(int[] arr : snakes) {
            outs[arr[0]] = arr[1];
        }

        return outs;
    }

    // Checking if a move should be made
    private static boolean isDecentMove(int[] teleports, Queue<int[]> possibilities, int move, int boardSize) {
        // From: https://www.geeksforgeeks.org/how-to-iterate-over-a-queue-in-java/
        Iterator<int[]> possibilitiesIterator = possibilities.iterator();
        // If it is already in the queue don't add it
        while (possibilitiesIterator.hasNext()) {
            int[] values = possibilitiesIterator.next();
            if (values[0] == move) {
                return false;
            }
        }
        // If the move exceeds the size of the board, don't add it
        if (move > boardSize) {
            return false;
        }
        // There is a snake's head on the square, don't add it
        // (could backfire if a solution requires going down a snake to complete it)
        if (teleports[move] != 0 && teleports[move] < move) {
            return false;
        }
        // Otherwise add it to the queue
        return true;
    }

    public static int BFS(int endCell, int[][] ladders, int[][] snakes) {
        System.out.println(endCell + " cells");
        System.out.println("Snakes: " + Arrays.deepToString(snakes));
        System.out.println("Ladders: " + Arrays.deepToString(ladders));
        // Create the map
        int[] teleportMap = createSpecial(ladders, snakes, endCell);
        int pos;
        // {square number : number of steps to get to that square}
        Queue<int[]> possibilities = new LinkedList<>();
        // Create a visited set to check if all possibilities have been checked, then return -1 if no solution is found
        Set<Integer> visited = new HashSet<>();
        // Add starting square
        possibilities.add(new int[] {1, 0});
        while (possibilities.peek()[0] != endCell) {
            int[] item = possibilities.remove();
            // Store the position and number of steps of each item
            pos = item[0];
            int steps = item[1];
            visited.add(pos);
            // Add next decent moves to the queue in reverse order
            for (int i = 6; i > 0; i--) {
                int newMove = move(pos, i, teleportMap);
                if (isDecentMove(teleportMap, possibilities, newMove, endCell)) {
                    // Add the new move and increment the steps
                    possibilities.add(new int[] {newMove, steps+1});
                }
            }
            // Subtract the number of snakes because their heads will never be visited.
            // Subtract two for the cells 0 and 100 which will never be visited
            if (visited.size() >= endCell - snakes.length - 2) {
                System.out.println("No Solution");
                System.out.println();
                return -1;
            }
        }
        System.out.println(possibilities.peek()[1] + " steps");
        System.out.println();
        // Return the steps of the last square
        return possibilities.peek()[1];
    }

    private static int move(int position, int stepSize, int[] specialMoves) {
        int newPos = position+stepSize;
        // Check if the move will teleport by using the map
        if (newPos <= 100 && specialMoves[newPos] != 0) {
            // Return the special move
            return specialMoves[newPos];
        }
        // Otherwise return the position + the step size.
        return newPos;
    }
}
