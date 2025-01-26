package deque;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    // 测试空队列的迭代器
    @Test
    public void testEmptyDequeIterator() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        Iterator<Integer> iterator = deque.iterator();
        assertFalse("Empty deque iterator should have no elements", iterator.hasNext());

        try {
            iterator.next();
            // 如果没有抛出异常，标记测试失败
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // 成功捕获预期异常，测试通过
        }
    }

    // 测试迭代顺序正确性（混合 addFirst/addLast）
    @Test
    public void testIteratorOrder() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addFirst("A");  // Deque: [A]
        deque.addLast("B");   // Deque: [A, B]
        deque.addFirst("C");  // Deque: [C, A, B]

        Iterator<String> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("First element should be C", "C", iterator.next());
        assertEquals("Second element should be A", "A", iterator.next());
        assertEquals("Third element should be B", "B", iterator.next());
        assertFalse("Iterator should have no more elements", iterator.hasNext());
    }

    // 测试迭代器遍历所有元素
    @Test
    public void testFullTraversal() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        // 添加 5 个元素
        for (int i = 0; i < 5; i++) {
            deque.addLast(i);
        }

        int expected = 0;
        for (int num : deque) {  // 使用 for-each 隐式调用迭代器
            System.err.println(num);
            assertEquals("Element should match sequential order", expected++, num);
        }
        assertEquals("Should iterate through all 5 elements", 5, expected);
    }

    // 测试不支持 remove 操作
    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemoveNotSupported() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("test");
        Iterator<String> iterator = deque.iterator();
        iterator.next();
        iterator.remove();  // 应抛出异常
    }

    // 测试多个独立迭代器
    @Test
    public void testMultipleIterators() {
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        deque.addLast('X');
        deque.addLast('Y');

        Iterator<Character> it1 = deque.iterator();
        Iterator<Character> it2 = deque.iterator();

        assertEquals("Both iterators should start at same element", it1.next(), it2.next());
        assertTrue("Both iterators should have next", it1.hasNext() && it2.hasNext());
    }

    // 测试相同引用的相等性
    @Test
    public void testReflexivity() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertTrue("Deque should equal itself", deque.equals(deque));
    }

    // 测试与 null 比较
    @Test
    public void testEqualsNull() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("test");
        assertFalse("Deque should not equal null", deque.equals(null));
    }

    // 测试与非 LinkedListDeque 对象比较
    @Test
    public void testDifferentClass() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(5);
        assertFalse("Deque should not equal a different class", deque.equals("5"));
    }

    // 测试大小不同的队列
    @Test
    public void testDifferentSize() {
        LinkedListDeque<Integer> deque1 = new LinkedListDeque<>();
        deque1.addLast(1);
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();
        deque2.addLast(1);
        deque2.addLast(2);
        assertFalse("Deques with different sizes should not be equal", deque1.equals(deque2));
    }

    // 测试元素顺序不同的队列
    @Test
    public void testDifferentOrder() {
        LinkedListDeque<Integer> deque1 = new LinkedListDeque<>();
        deque1.addLast(1);
        deque1.addLast(2);
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();
        deque2.addLast(2);
        deque2.addLast(1);
        assertFalse("Deques with different element order should not be equal", deque1.equals(deque2));
    }

    // 测试元素内容不同的队列
    @Test
    public void testDifferentElements() {
        LinkedListDeque<String> deque1 = new LinkedListDeque<>();
        deque1.addLast("A");
        LinkedListDeque<String> deque2 = new LinkedListDeque<>();
        deque2.addLast("B");
        assertFalse("Deques with different elements should not be equal", deque1.equals(deque2));
    }

    // 测试完全相同元素的队列
    @Test
    public void testEqualDeques() {
        LinkedListDeque<Integer> deque1 = new LinkedListDeque<>();
        deque1.addLast(10);
        deque1.addLast(20);
        LinkedListDeque<Integer> deque2 = new LinkedListDeque<>();
        deque2.addLast(10);
        deque2.addLast(20);
        assertTrue("Deques with same elements should be equal", deque1.equals(deque2));
    }

    // 测试包含 null 元素的队列
    @Test
    public void testNullElements() {
        LinkedListDeque<String> deque1 = new LinkedListDeque<>();
        deque1.addLast(null);
        LinkedListDeque<String> deque2 = new LinkedListDeque<>();
        deque2.addLast(null);
        assertTrue("Deques with null elements should be equal", deque1.equals(deque2));
    }

    // 测试混合 null 和非 null 元素
    @Test
    public void testMixedNullElements() {
        LinkedListDeque<String> deque1 = new LinkedListDeque<>();
        deque1.addLast("A");
        deque1.addLast(null);
        LinkedListDeque<String> deque2 = new LinkedListDeque<>();
        deque2.addLast("A");
        deque2.addLast(null);
        assertTrue("Deques with mixed null and non-null elements should be equal", deque1.equals(deque2));
    }
}
