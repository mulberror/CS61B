package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> testList = new BuggyAList<>();

        correctList.addLast(4);
        correctList.addLast(5);
        correctList.addLast(6);
        testList.addLast(4);
        testList.addLast(5);
        testList.addLast(6);

        assertEquals(correctList.removeLast(), testList.removeLast());
        assertEquals(correctList.removeLast(), testList.removeLast());
        assertEquals(correctList.removeLast(), testList.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> testList = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (L.size() == 0) {
                operationNumber = 0;
            }
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                testList.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // getLast
                Integer getNumber0 = L.getLast();
                Integer getNumber1 = testList.getLast();
                System.out.println("getLast()");
                assertEquals(getNumber0, getNumber1);
            } else {
                // removeLast
                Integer getNumber0 = L.removeLast();
                Integer getNumber1 = testList.removeLast();
                System.out.println("removeLast()");
                assertEquals(getNumber0, getNumber1);
            }
        }
    }
}
