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

    private static boolean isDecentMove(int[][] teleports, int[][] snakes, Stack<Integer> possibilities, int position, int move) {
//        Arrays.sort(snakes[0]);
        int newPos = move(position, move, teleports);
        for (int i : possibilities) {
            if (i == newPos) {
                System.out.println(i + " ==? " + newPos);
                return false;
            }
        }
        for (int[] arr : snakes) {
            if (arr[1] == newPos) {

            }
        }
        if (newPos > 100) {
            System.out.println(newPos + " is over 100");
            return false;
        }
        return true;
    }

    public static int fewestMoves(int endCell, int[][] ladders, int[][] snakes) {
//        System.out.println(boardsize);
//        System.out.println(Arrays.deepToString(ladders));
//        System.out.println(Arrays.deepToString(snakes));
        int[][] teleports = createSpecial(ladders, snakes);
        System.out.println(Arrays.deepToString(snakes));
        System.out.println(Arrays.deepToString(ladders));
        int pos;
//        Queue<Integer> possibilities = new LinkedList<>();
        Stack<Integer> possibilities = new Stack<>();
        possibilities.add(1);
        int[] steps = {0, 0, 0, 0, 0, 0};
        while (!possibilities.isEmpty() && possibilities.peek() != endCell) {
            System.out.println(possibilities);
            pos = possibilities.pop();
            System.out.println(pos);
//            System.out.println(Arrays.toString(steps));

            if (isDecentMove(teleports, snakes, possibilities, pos, 1)) {
                possibilities.push(pos+1);
                steps[0] ++;
            } else {
                System.out.println((pos + 1) + " is not decent");

                steps[0] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 2)) {
                possibilities.push(pos+2);
                steps[1] ++;
            } else {
                System.out.println((pos + 2) + " is not decent");

                steps[1] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 3)) {
                possibilities.push(pos+3);
                steps[2] ++;
            } else {
                System.out.println((pos + 3) + " is not decent");

                steps[2] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 4)) {
                possibilities.push(pos+4);
                steps[3] ++;
            } else {
                System.out.println((pos + 4) + " is not decent");

                steps[3] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 5)) {
                possibilities.push(pos+5);
                steps[4] ++;
            } else {
                System.out.println((pos + 5) + " is not decent");
                steps[4] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 6)) {
                possibilities.push(pos+6);
                steps[5] ++;
            } else {
                System.out.println((pos + 6) + " is not decent");
                steps[5] = -1;
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
