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

    private static boolean isValidMove(int[][] teleports, int position, int move) {
        if (position + move > 100)
        return true;
    }

    public static int fewestMoves(int endCell, int[][] ladders, int[][] snakes) {
//        System.out.println(boardsize);
//        System.out.println(Arrays.deepToString(ladders));
//        System.out.println(Arrays.deepToString(snakes));
        int[][] teleports = createSpecial(ladders, snakes);
        int pos;

        Queue<Integer> possibilities = new LinkedList<>();
        possibilities.add(1);
        while (!(possibilities.peek() == endCell)) {
            pos = possibilities.remove();

        }
        return 0;
    }

    private static int move(int position, int stepSize, int[][]specialMoves) {
        int newPos = position+stepSize;


        return newPos;
    }
}
