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
        int newPos = move(position, move, teleports);
        if (possibilities.search(newPos) != -1) {
            return false;
        }
        if (!inArr(snakes, newPos)) {
            return false;
        }
        if (newPos > boardSize) {
            return false;
        }
        return true;
    }

    private static boolean isDecentMove(int[][] teleports, int[][] snakes, Queue<int[]> possibilities, int position, int move, int boardSize) {
        int newPos = move(position, move, teleports);

        // From: https://www.geeksforgeeks.org/how-to-iterate-over-a-queue-in-java/
        Iterator<int[]> possibilitiesIterator = possibilities.iterator();
        // Iterating Queue
        while (possibilitiesIterator.hasNext()) {
            int[] values = possibilitiesIterator.next();
            if (values[0] == newPos) {
                return false;
            }
        }
        if (!inArr(snakes, newPos)) {
            return false;
        }
        if (newPos > boardSize) {
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
        System.out.println(endCell + " cells");
        System.out.println("Snakes: " + Arrays.deepToString(snakes));
        System.out.println("Ladders: " + Arrays.deepToString(ladders));
        int[][] teleports = createSpecial(ladders, snakes);
        int pos;
//        Queue<Integer> possibilities = new LinkedList<>();
        Stack<Integer> possibilities = new Stack<>();
        possibilities.add(1);
//        int[] steps = {0, 0, 0, 0, 0, 0};
        int steps = -1;
        while (!possibilities.isEmpty() && possibilities.peek() != endCell) {
            possibilities = sortStack(possibilities);
            pos = possibilities.pop();
            steps ++;
            for (int i = 1; i < 7; i++) {
                if (isDecentMove(teleports, snakes, possibilities, pos, i, endCell)) {
                    possibilities.push(move(pos, i, teleports));
                }
            }
//            if (isDecentMove(teleports, snakes, possibilities, pos, 1, endCell)) {
//                possibilities.push(move(pos, 1, teleports));
//            } if (isDecentMove(teleports, snakes, possibilities, pos, 2, endCell)) {
//                possibilities.push(move(pos, 2, teleports));
//            } if (isDecentMove(teleports, snakes, possibilities, pos, 3, endCell)) {
//                possibilities.push(move(pos, 3, teleports));
//            } if (isDecentMove(teleports, snakes, possibilities, pos, 4, endCell)) {
//                possibilities.push(move(pos, 4, teleports));
//            } if (isDecentMove(teleports, snakes, possibilities, pos, 5, endCell)) {
//                possibilities.push(move(pos, 5, teleports));
//            } if (isDecentMove(teleports, snakes, possibilities, pos, 6, endCell)) {
//                possibilities.push(move(pos,  6, teleports));
//            }
            // Pushing squares with snakes into the highest priority
            for (int[] arr : ladders) {
                if (arr[0] >= pos && arr[0] - 6 <= pos) {
                    // Removing potential duplicate squares
                    int index = possibilities.search(move(arr[0], 0, teleports));
                    if (index >= 0) {
//                        System.out.println("removing index " + index);
                        possibilities.remove(possibilities.size() - index);
                    }
                    possibilities.push(move(arr[0], 0, teleports));
                }
            }
        }
        return steps + 1;
    }

    public static int BFS(int endCell, int[][] ladders, int[][] snakes) {
        System.out.println(endCell + " cells");
        System.out.println("Snakes: " + Arrays.deepToString(snakes));
        System.out.println("Ladders: " + Arrays.deepToString(ladders));
        int[][] teleports = createSpecial(ladders, snakes);
        int pos;
        // Holding {square number : number of steps to get to that square}
        Queue<int[]> possibilities = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        possibilities.add(new int[] {1, 0});
        while (possibilities.peek()[0] != endCell) {
//            Iterator<int[]> possibilitiesIterator = possibilities.iterator();
            // Iterating Queue
//            while (possibilitiesIterator.hasNext()) {
//                int[] values = possibilitiesIterator.next();
//                System.out.print(Arrays.toString(values));
//                System.out.print(", ");
//            }
            int[] item = possibilities.remove();
            pos = item[0];
            int steps = item[1];
            visited.add(pos);
            for (int i = 6; i > 0; i--) {
                if (isDecentMove(teleports, snakes, possibilities, pos, i, endCell)) {
                    possibilities.add(new int[] {move(pos, i, teleports), steps+1});
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
        return possibilities.peek()[1];
    }

    public static Stack<Integer> sortStack(Stack<Integer> input){
        // Sorting a stack from lowest to highest, so the highest will be popped.
        Stack<Integer> tempStack = new Stack<Integer>();
        while(!input.isEmpty()){
            int temp = input.pop();
            while(!tempStack.isEmpty() && tempStack.peek() > temp){
                input.push(tempStack.pop());
            }
            tempStack.push(temp);
        }
        return tempStack;
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
