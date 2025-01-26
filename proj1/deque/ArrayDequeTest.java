package deque;

import org.junit.Test;
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
        deque.addLast(8);
        assertEquals("Capacity should expand to 16", 16, deque.capacity());

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
}
