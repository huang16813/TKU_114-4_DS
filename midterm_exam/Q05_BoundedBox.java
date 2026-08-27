import java.util.ArrayList;
import java.util.List;

public class Q05_BoundedBox<T extends Comparable<T>> {
    private final int capacity;
    private final List<T> data = new ArrayList<>();

    public Q05_BoundedBox(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be at least 1");
        }
        this.capacity = capacity;
    }

    public boolean add(T value) {
        if (value == null || data.size() >= capacity) {
            return false;
        }
        data.add(value);
        return true;
    }

    public int size() {
        return data.size();
    }

    public boolean isFull() {
        return data.size() >= capacity;
    }

    public T minimum() {
        if (data.isEmpty()) {
            return null;
        }
        T min = data.get(0);
        for (T value : data) {
            if (value.compareTo(min) < 0) {
                min = value;
            }
        }
        return min;
    }

    public T maximum() {
        if (data.isEmpty()) {
            return null;
        }
        T max = data.get(0);
        for (T value : data) {
            if (value.compareTo(max) > 0) {
                max = value;
            }
        }
        return max;
    }

    public int countGreaterThan(T threshold) {
        if (threshold == null) {
            return 0;
        }
        int count = 0;
        for (T value : data) {
            if (value.compareTo(threshold) > 0) {
                count++;
            }
        }
        return count;
    }

    public List<T> snapshot() {
        return new ArrayList<>(data);
    }
}
