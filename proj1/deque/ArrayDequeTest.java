package deque;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public void testInitialState() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        assertTrue("New deque should be empty", deque.isEmpty());
        assertEquals("Initial size should be 0", 0, deque.size());
    }

    // 测试 addFirst 和 removeFirst
    @Test
    public void addFirstRemoveFirstTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 添加并移除单个元素
        deque.addFirst(10);
        assertEquals("Size should be 1", 1, deque.size());
        int first = deque.removeFirst();
        assertEquals("Removed element should be 10", 10, first);
        assertTrue("Deque should be empty after removal", deque.isEmpty());

        // 测试循环添加和扩容
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
        }
        assertEquals("Size should be 10", 10, deque.size());
    }

    // 测试 addLast 和 removeLast
    @Test
    public void addLastRemoveLastTest() {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addLast("A");
        deque.addLast("B");
        assertEquals("Size should be 2", 2, deque.size());

        String last = deque.removeLast();
        assertEquals("Removed element should be B", "B", last);
        assertEquals("Size should be 1 after removal", 1, deque.size());
    }

    // 测试混合操作（交替 addFirst/addLast）
    @Test
    public void mixedAddRemoveTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);     // [1]
        deque.addLast(2);      // [1,2]
        deque.addFirst(0);     // [0,1,2]
        deque.addLast(3);      // [0,1,2,3]

        assertEquals("Size should be 4", 4, deque.size());
        assertEquals("removeFirst should return 0", 0, (int) deque.removeFirst());
        assertEquals("removeLast should return 3", 3, (int) deque.removeLast());
        assertEquals("New size should be 2", 2, deque.size());
    }

    // 测试扩容和缩容逻辑
    @Test
    public void resizeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 填充到初始容量 (8)
        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }
        assertEquals("Capacity should remain 8", 8, deque.size());

        // 触发扩容
//        deque.addLast(8);
//        assertEquals("Capacity should expand to 16", 16, deque.capacity());

        // 移除元素触发缩容
        for (int i = 0; i < 5; i++) {
            deque.removeFirst();
        }
        assertTrue("Capacity should shrink to 8", deque.size() * 2 <= 8);
    }

    @Test
    public void orderConsistencyTest() {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        deque.addFirst('A'); // [A]
        deque.addLast('B');  // [A,B]
        deque.addFirst('C'); // [C,A,B]

        assertEquals("First element should be C", 'C', (char) deque.removeFirst());
        assertEquals("Last element should be B", 'B', (char) deque.removeLast());
        assertEquals("Remaining element should be A", 'A', (char) deque.removeFirst());
    }

    @Test
    public void printDequeTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.printDeque(); // 预期输出: 1 2 3
    }

    @Test
    public void testEmptyIterator() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Iterator<Integer> iterator = deque.iterator();
        assertFalse("Empty deque iterator should have no elements", iterator.hasNext());

        try {
            iterator.next();
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
            // 测试通过
        }
    }

    // 测试单元素队列的迭代
    @Test
    public void testSingleElement() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("A");
        Iterator<String> iterator = deque.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertFalse(iterator.hasNext());
    }

    // 测试多元素顺序遍历
    @Test
    public void testElementOrder() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> iterator = deque.iterator();
        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }

    // 测试环形遍历（head 在数组末尾，tail 在开头）
    @Test
    public void testCircularTraversal() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        // 填充队列：head=0, tail=3（容量=4）
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        // 移除两个元素，head=2, tail=3
        deque.removeFirst();
        deque.removeFirst();

        // 添加两个元素，触发环形逻辑：head=2, tail=1（容量=4）
        deque.addLast(4);
        deque.addLast(5);

        Iterator<Integer> iterator = deque.iterator();
        assertEquals(3, (int) iterator.next());
        assertEquals(4, (int) iterator.next());
        assertEquals(5, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }

    // 测试混合操作后的迭代
    @Test
    public void testMixedOperations() {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        deque.addFirst('B');  // [B]
        deque.addLast('C');   // [B, C]
        deque.addFirst('A');  // [A, B, C]
        deque.removeLast();   // [A, B]
        deque.addLast('D');   // [A, B, D]

        Iterator<Character> iterator = deque.iterator();
        assertEquals('A', (char) iterator.next());
        assertEquals('B', (char) iterator.next());
        assertEquals('D', (char) iterator.next());
        assertFalse(iterator.hasNext());
    }

    // 测试不支持 remove() 操作
    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveNotSupported() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(10);
        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        iterator.remove(); // 应抛出异常
    }

    // 测试多个独立迭代器
    @Test
    public void testMultipleIterators() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(10);
        deque.addLast(20);

        Iterator<Integer> it1 = deque.iterator();
        Iterator<Integer> it2 = deque.iterator();

        assertEquals(10, (int) it1.next());
        assertEquals(10, (int) it2.next());
        assertEquals(20, (int) it1.next());
        assertEquals(20, (int) it2.next());
    }

    // 测试自反性：对象等于自身
    @Test
    public void testReflexivity() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        assertTrue("Deque should equal itself", deque.equals(deque));
    }

    // 测试对称性：A等于B则B等于A
    @Test
    public void testSymmetry() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        deque1.addLast("A");
        ArrayDeque<String> deque2 = new ArrayDeque<>();
        deque2.addLast("A");

        assertTrue("deque1 should equal deque2", deque1.equals(deque2));
        assertTrue("deque2 should equal deque1", deque2.equals(deque1));
    }

    // 测试传递性：A等于B且B等于C，则A等于C
    @Test
    public void testTransitivity() {
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addLast(10);
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque2.addLast(10);
        ArrayDeque<Integer> deque3 = new ArrayDeque<>();
        deque3.addLast(10);

        assertTrue(deque1.equals(deque2));
        assertTrue(deque2.equals(deque3));
        assertTrue(deque1.equals(deque3));
    }

    // 测试与非ArrayDeque对象比较
    @Test
    public void testDifferentClass() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(5);
        String notADeque = "5";
        assertFalse("Deque should not equal a String", deque.equals(notADeque));
    }

    // 测试与null比较
    @Test
    public void testEqualsNull() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.addLast("test");
        assertFalse("Deque should not equal null", deque.equals(null));
    }

    // 测试大小不同的队列
    @Test
    public void testDifferentSize() {
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addLast(1);
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque2.addLast(1);
        deque2.addLast(2);
        assertFalse("Deques with different sizes should not be equal", deque1.equals(deque2));
    }

    // 测试元素顺序不同的队列
    @Test
    public void testDifferentOrder() {
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addLast(1);
        deque1.addLast(2);
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque2.addLast(2);
        deque2.addLast(1);
        assertFalse("Deques with different order should not be equal", deque1.equals(deque2));
    }

    // 测试元素内容不同的队列
    @Test
    public void testDifferentElements() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        deque1.addLast("A");
        ArrayDeque<String> deque2 = new ArrayDeque<>();
        deque2.addLast("B");
        assertFalse("Deques with different elements should not be equal", deque1.equals(deque2));
    }

    // 测试包含null元素的队列
    @Test
    public void testNullElements() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        deque1.addLast(null);
        ArrayDeque<String> deque2 = new ArrayDeque<>();
        deque2.addLast(null);
        assertTrue("Deques with null elements should be equal", deque1.equals(deque2));
    }

    // 测试混合null和非null元素
    @Test
    public void testMixedNullElements() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        deque1.addLast("A");
        deque1.addLast(null);
        ArrayDeque<String> deque2 = new ArrayDeque<>();
        deque2.addLast("A");
        deque2.addLast(null);
        assertTrue("Deques with mixed elements should be equal", deque1.equals(deque2));
    }

    // 测试对象内容相同但引用不同（如不同对象实例）
    @Test
    public void testSameContentDifferentInstances() {
        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addLast(1);
        deque1.addLast(2);
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        deque2.addLast(1);
        deque2.addLast(2);
        assertTrue("Deques with same content should be equal", deque1.equals(deque2));
    }

    // 测试equals方法的一致性（多次调用结果相同）
    @Test
    public void testConsistency() {
        ArrayDeque<String> deque1 = new ArrayDeque<>();
        deque1.addLast("X");
        ArrayDeque<String> deque2 = new ArrayDeque<>();
        deque2.addLast("X");

        boolean firstResult = deque1.equals(deque2);
        boolean secondResult = deque1.equals(deque2);
        assertEquals("Results should be consistent", firstResult, secondResult);
    }

    @Test
    public void testArrayDequeLinkedListDequeSame() {
        Deque<String> deque1 = new ArrayDeque<>();
        deque1.addLast("X");
        Deque<String> deque2 = new LinkedListDeque<>();
        deque2.addLast("X");

        assertTrue("Deques with same content should be equal", deque1.equals(deque2));
    }
}
