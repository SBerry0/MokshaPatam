import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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

    /**
     * TODO: Complete this function, fewestMoves(), to return the minimum number of moves
     *  to reach the final square on a board with the given size, ladders, and snakes.
     */
    private static int[][] createSpecial(int[][] ladders, int[][] snakes) {
        int[] inputSquares = new int[ladders.length + snakes.length];
        int[] outputSquares = new int[ladders.length + snakes.length];
        int i = 0;
        for(int[] arr : ladders) {
            inputSquares[i] = arr[0];
            outputSquares[i++] = arr[1];
        }
        for(int[] arr : snakes) {
            inputSquares[i] = arr[0];
            outputSquares[i++] = arr[1];
        }
        int[][] specials = new int[2][outputSquares.length];
        specials[0] = inputSquares;
        specials[1] = outputSquares;
        return specials;
    }

    private static boolean isDecentMove(int[][] teleports, int[][] snakes, int position, int move) {
        Arrays.sort(snakes[0]);
        int newPos = move(position, move, teleports);
        if (newPos > 100 || Arrays.binarySearch(snakes[0], newPos) != -1) {
            return false;
        }
        return true;
    }

    public static int fewestMoves(int endCell, int[][] ladders, int[][] snakes) {
//        System.out.println(boardsize);
//        System.out.println(Arrays.deepToString(ladders));
//        System.out.println(Arrays.deepToString(snakes));
        int[][] teleports = createSpecial(ladders, snakes);
        System.out.println(Arrays.deepToString(teleports));
        int pos;
        Queue<Integer> possibilities = new LinkedList<>();
        possibilities.add(1);
        int[] steps = {0, 0, 0, 0, 0, 0};
        while (!possibilities.isEmpty() && possibilities.peek() != endCell) {
            pos = possibilities.remove();
            System.out.println(pos);
            System.out.println(Arrays.toString(steps));

            if (isDecentMove(teleports, snakes, pos, 6)) {
                possibilities.add(pos+6);
                steps[5] ++;
            } else {
                steps[5] = -1;
            }
            if (isDecentMove(teleports, snakes, pos, 5)) {
                possibilities.add(pos+5);
                steps[4] ++;
            } else {
                steps[4] = -1;
            }
            if (isDecentMove(teleports, snakes, pos, 4)) {
                possibilities.add(pos+4);
                steps[3] ++;
            } else {
                steps[3] = -1;
            }
            if (isDecentMove(teleports, snakes, pos, 3)) {
                possibilities.add(pos+3);
                steps[2] ++;
            } else {
                steps[2] = -1;
            }
            if (isDecentMove(teleports, snakes, pos, 2)) {
                possibilities.add(pos+2);
                steps[1] ++;
            } else {
                steps[1] = -1;
            }
            if (isDecentMove(teleports, snakes, pos, 1)) {
                possibilities.add(pos+1);
                steps[0] ++;
            } else {
                steps[0] = -1;
            }

//            boolean hasSolution = true;
//            for (int i : steps) {
//                if (i == -1) {
//                    hasSolution = false;
//                } else {
//                    hasSolution = true;
//                    break;
//                }
//            }
//            if (!hasSolution)
//                return -1;
        }
        int minSteps = Integer.MAX_VALUE;
        for (int i : steps) {
            if (i != -1 && i < minSteps) {
                minSteps = i;
            }
        }
        return minSteps;
    }

    private static int move(int position, int stepSize, int[][]specialMoves) {
        int newPos = position+stepSize;
        for (int i = 0; i < specialMoves[0].length; i++) {
            if (specialMoves[0][i] == newPos) {
                return specialMoves[1][i];
            }
        }
        return newPos;
    }
}
