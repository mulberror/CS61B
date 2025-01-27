package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    // 整数比较器（明确泛型类型）
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    // 字符串长度比较器（明确泛型类型）
    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    // 测试空队列
    @Test
    public void testEmptyDeque() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<Integer>(new IntComparator());
        assertNull("Max of empty deque should be null", deque.max());
        assertNull("Max with custom comparator should be null", deque.max(new IntComparator()));
    }

    // 测试默认比较器（整数）
    @Test
    public void testDefaultComparator() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<Integer>(new IntComparator());
        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(5);
        deque.addLast(2);
        assertEquals("Max should be 5", 5, (int) deque.max());
    }

    // 测试自定义比较器（字符串长度）
    @Test
    public void testCustomComparator() {
        MaxArrayDeque<String> deque = new MaxArrayDeque<String>(Comparator.naturalOrder());
        deque.addLast("apple");
        deque.addLast("banana");
        deque.addLast("cherry");

        StringLengthComparator lengthComparator = new StringLengthComparator();
        assertEquals("Longest string should be 'banana'", "banana", deque.max(lengthComparator));
    }

    // 测试逆序比较器
    @Test
    public void testReverseComparator() {
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<Integer>(reverseComparator);
        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(5);
        assertEquals("Max with reverse comparator should be 1", 1, (int) deque.max());
    }
}