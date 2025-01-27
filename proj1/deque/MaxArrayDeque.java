package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return getMaxHelper(comparator);
    }

    public T max(Comparator<T> c) {
        return getMaxHelper(c);
    }

    private T getMaxHelper(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxResult = get(0);
        for (T item : this) {
            if (c.compare(item, maxResult) > 0) {
                maxResult = item;
            }
        }
        return maxResult;
    }
}
