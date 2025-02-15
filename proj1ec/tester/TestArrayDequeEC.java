package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeEC {
    private static final int MAX_NUMBER = 10000;
    @Test
    public void test() {
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        String message = "";
        boolean flag = true;
        int size = 0;

        while (flag) {
            int option = StdRandom.uniform(0, 6);
            if (size == 0) {
                option = StdRandom.uniform(0, 3);
            }
            Integer number = StdRandom.uniform(MAX_NUMBER);
            switch (option) {
                case 0:
                    solutionDeque.addFirst(number);
                    studentDeque.addFirst(number);
                    size++;
                    message += "addFirst(" + number + ")\n";
                    break;
                case 1:
                    solutionDeque.addLast(number);
                    studentDeque.addLast(number);
                    size++;
                    message += "addLast(" + number + ")\n";
                    break;
                case 2:
                    if (solutionDeque.size() != studentDeque.size()) {
                        flag = false;
                    }
                    break;
                case 3:
                    int position = StdRandom.uniform(0, studentDeque.size());
                    Integer getNumber0 = solutionDeque.get(position);
                    Integer getNumber1 = studentDeque.get(position);
                    if (!getNumber0.equals(getNumber1)) {
                        flag = false;
                    }
                    break;
                case 4:
                    Integer removeFirstNumber0 = solutionDeque.removeFirst();
                    Integer removeFirstNumber1 = studentDeque.removeFirst();
                    message += "removeFirst()\n";
                    if (!removeFirstNumber0.equals(removeFirstNumber1)) {
                        assertEquals(message, removeFirstNumber0, removeFirstNumber1);
                        flag = false;
                    }
                    size--;
                    break;
                case 5:
                    Integer removeLastNumber0 = solutionDeque.removeLast();
                    Integer removeLastNumber1 = studentDeque.removeLast();
                    message += "removeLast()\n";
                    if (!removeLastNumber0.equals(removeLastNumber1)) {
                        assertEquals(message, removeLastNumber0, removeLastNumber1);
                        flag = false;
                    }
                    size--;
                    break;
            }
        }
    }
}
