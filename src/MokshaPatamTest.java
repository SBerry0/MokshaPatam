import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Moksha Patam
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * To use this test file, run either the entire thing or individual tests (one at a time).
 * There are five test cases, each of which will load data from [test number].txt, which is in the
 * test_files directory.
 */

public class MokshaPatamTest {

    private final MokshaPatam studentSolution = new MokshaPatam();
    private int nLadders;
    private int nSnakes;
    private int boardsize;
    private int[][] ladders;
    private int[][] snakes;

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testStack() {
        Stack<Integer> possibilities = new Stack<>();
        possibilities.push(6);
        possibilities.push(4);
        possibilities.push(5);
        possibilities.push(31);
        possibilities.push(3);
        MokshaPatam.sortStack(possibilities);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testBasic() {
        setTestData(0);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testTricky() {
        setTestData(4);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testLarger() {
        setTestData(6);
    }

    @Test
    @Timeout(value = 15, unit = TimeUnit.SECONDS)
    public void testMuchLarger() {
        setTestData(7);
    }

    private void setTestData(int testNumber) {
        // Open files
        try {
            BufferedReader testReader = new BufferedReader(new FileReader("test_files/" + testNumber + ".txt"));
            BufferedReader answerReader = new BufferedReader(new FileReader("test_files/" + testNumber + "_answers.txt"));

            // Get the number of tests in the file
            int numTests = Integer.parseInt(testReader.readLine());
            boardsize = Integer.parseInt(testReader.readLine());

            // Read in the data for each test, then run.
            for (int i = 0; i < numTests; i++)
            {
                int answer = Integer.parseInt(answerReader.readLine());
                loadTest(testReader);
                assertEquals(answer, studentSolution.fewestMoves(boardsize, ladders, snakes),
                        "Test " + testNumber + " failed: should return " + answer);
            }
        } catch (IOException e) {
            System.out.println("Error opening test file #" + testNumber);
            e.printStackTrace();
        }
    }

    private void loadTest(BufferedReader br) {
        String line;
        try {
            // Populate ladders
            line = br.readLine();
            nLadders = Integer.parseInt(line);
            ladders = new int[nLadders][2];

            for (int i = 0; i < nLadders; i++) {
                line = br.readLine();
                String[] parts = line.trim().split("\\s+");
                ladders[i][0] = Integer.parseInt(parts[0]);
                ladders[i][1] = Integer.parseInt(parts[1]);
            }

            // Populate snakes
            line = br.readLine();
            nSnakes = Integer.parseInt(line);
            snakes = new int[nSnakes][2];

            for (int i = 0; i < nSnakes; i++) {
                line = br.readLine();
                String[] parts = line.trim().split("\\s+");
                snakes[i][0] = Integer.parseInt(parts[0]);
                snakes[i][1] = Integer.parseInt(parts[1]);
            }

        } catch (IOException e) {
            System.out.println("Error opening test file");
            e.printStackTrace();
        }
    }
}
