import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;

    DynamicArray(int initialCapacity) {
        data = new Object[Math.max(1, initialCapacity)];
    }

    void add(T value) {
        ensureCapacity();
        data[size] = value;
        size++;
    }

    void add(int index, T value) {
        checkIndexForInsert(index);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    T set(int index, T value) {
        checkIndex(index);
        T old = (T) data[index];
        data[index] = value;
        return old;
    }

    @SuppressWarnings("unchecked")
    T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;
        return removed;
    }

    int size() {
        return size;
    }

    int capacity() {
        return data.length;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
    }

    private void checkIndexForInsert(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index=" + index);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(data, size));
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        DynamicArray<String> names = new DynamicArray<>(2);
        names.add("A");
        names.add("B");
        names.add("C");
        System.out.println("names：" + names + "，capacity=" + names.capacity());

        names.add(1, "X");
        System.out.println("index 1 插入後：" + names);

        System.out.println("removed=" + names.remove(2));
        System.out.println("刪除後：" + names);

        DynamicArray<Integer> numbers = new DynamicArray<>(2);
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        System.out.println("numbers：" + numbers + "，capacity=" + numbers.capacity());

        try {
            numbers.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("index -1 例外：" + e.getMessage());
        }

        try {
            numbers.get(numbers.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("index=size 例外：" + e.getMessage());
        }

        DynamicArray<Integer> empty = new DynamicArray<>(2);
        try {
            empty.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("空結構刪除例外：" + e.getMessage());
        }
    }
}
