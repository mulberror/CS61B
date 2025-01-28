package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    private static final int MAX_NUMBER = 10000;
    @Test
    public void test() {
        ArrayDequeSolution<Integer> correctList = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> studentList = new StudentArrayDeque<>();
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
                    correctList.addFirst(number);
                    studentList.addFirst(number);
                    size++;
                    break;
                case 1:
                    correctList.addLast(number);
                    studentList.addLast(number);
                    size++;
                    break;
                case 2:
                    if (correctList.size() != studentList.size()) {
                        flag = false;
                    }
                    break;
                case 3:
                    int position = StdRandom.uniform(0, studentList.size());
                    Integer getNumber0 = correctList.get(position);
                    Integer getNumber1 = studentList.get(position);
                    if (!getNumber0.equals(getNumber1)) {
                        flag = false;
                    }
                    break;
                case 4:
                    Integer removeFirstNumber0 = correctList.removeFirst();
                    Integer removeFirstNumber1 = studentList.removeFirst();
                    if (!removeFirstNumber0.equals(removeFirstNumber1)) {
                        flag = false;
                    }
                    size--;
                    break;
                case 5:
                    Integer removeLastNumber0 = correctList.removeLast();
                    Integer removeLastNumber1 = studentList.removeLast();
                    if (!removeLastNumber0.equals(removeLastNumber1)) {
                        flag = false;
                    }
                    size--;
                    break;
            }
        }
    }
}
