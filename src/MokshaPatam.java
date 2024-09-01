import java.io.LineNumberReader;
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

    private static boolean isDecentMove(int[][] teleports, int[][] snakes, Stack<Integer> possibilities, int position, int move, int boardSize) {
//        Arrays.sort(snakes[0]);
        int newPos = move(position, move, teleports);
        if (possibilities.search(newPos) != -1) {
            return false;
        }

        if (!inArr(snakes, newPos)) {
            System.out.println(newPos + " is a snake!");
            return false;
        }
        if (newPos > boardSize) {
            System.out.println(newPos + " is over 100");
            return false;
        }
        return true;
    }
    private static boolean inArr(int[][] arr, int target) {
        for (int[] array : arr) {
            if (array[0] == target) {
                return false;
            }
        }
        return true;
    }

    public static int fewestMoves(int endCell, int[][] ladders, int[][] snakes) {
        System.out.println(endCell);
//        System.out.println(Arrays.deepToString(ladders));
//        System.out.println(Arrays.deepToString(snakes));
        int[][] teleports = createSpecial(ladders, snakes);
        System.out.println(Arrays.deepToString(snakes));
        System.out.println(Arrays.deepToString(ladders));
        int pos;
//        Queue<Integer> possibilities = new LinkedList<>();
        Stack<Integer> possibilities = new Stack<>();
        possibilities.add(1);
//        int[] steps = {0, 0, 0, 0, 0, 0};
        int steps = -1;
        while (!possibilities.isEmpty() && possibilities.peek() != endCell) {
            System.out.println(possibilities);
            possibilities = sortStack(possibilities);
            pos = possibilities.pop();
            steps ++;
//            System.out.println(steps);
            System.out.println(pos);
//            System.out.println(Arrays.toString(steps));

            if (isDecentMove(teleports, snakes, possibilities, pos, 1, endCell)) {
                possibilities.push(move(pos, 1, teleports));
//                steps ++;
            } else {
                System.out.println((pos + 1) + " is not decent");

//                steps[0] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 2, endCell)) {
                possibilities.push(move(pos, 2, teleports));
            } else {
                System.out.println((pos + 2) + " is not decent");

//                steps[1] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 3, endCell)) {
                possibilities.push(move(pos, 3, teleports));
//                steps ++;
            } else {
                System.out.println((pos + 3) + " is not decent");

//                steps[2] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 4, endCell)) {
                possibilities.push(move(pos, 4, teleports));
//                steps ++;
            } else {
                System.out.println((pos + 4) + " is not decent");

//                steps[3] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 5, endCell)) {
                possibilities.push(move(pos, 5, teleports));
//                steps ++;
            } else {
                System.out.println((pos + 5) + " is not decent");
//                steps[4] = -1;
            }
            if (isDecentMove(teleports, snakes, possibilities, pos, 6, endCell)) {
                possibilities.push(move(pos,  6, teleports));
//                steps ++;
            } else {
                System.out.println((pos + 6) + " is not decent");
//                steps[5] = -1;
            }
            for (int[] arr : ladders) {
                if (arr[0] >= pos && arr[0] - 6 <= pos) {
                    System.out.println("pushing " + arr[0]);
                    System.out.println(possibilities);
                    int index = possibilities.search(move(arr[0], 0, teleports));
                    if (index >= 0) {
//                        System.out.println("removing index " + index);
                        System.out.println(possibilities.remove(possibilities.size() - index));
                    }
                    possibilities.push(move(arr[0], 0, teleports));
                    System.out.println(possibilities);
//                    steps++;
                }
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
        System.out.println(possibilities);
        return steps + 1;
//        int minSteps = Integer.MAX_VALUE;
//        for (int i : steps) {
//            if (i != -1 && i < minSteps) {
//                minSteps = i;
//            }
//        }
//        return minSteps;
    }

    public static Stack<Integer> sortStack(Stack<Integer> input){
//        System.out.println(input);
        Stack<Integer> tempStack = new Stack<Integer>();
        while(!input.isEmpty()){
            int temp = input.pop();
//            System.out.println("temp: " + temp);
            while(!tempStack.isEmpty() && tempStack.peek() > temp){
                input.push(tempStack.pop());
//                System.out.println("input: " + input);
//                System.out.println("tempStack: " + tempStack);
            }
            tempStack.push(temp);
        }
        return tempStack;
    }

    private static int move(int position, int stepSize, int[][]specialMoves) {
        int newPos = position+stepSize;
//        System.out.println(Arrays.deepToString(specialMoves));
        for (int i = 0; i < specialMoves[0].length; i++) {
            if (specialMoves[0][i] == newPos) {
//                System.out.println("special move: " + specialMoves[0][i]);
//                System.out.println("returning: " + specialMoves[1][i]);
                return specialMoves[1][i];
            }
        }
        return newPos;
    }
}
