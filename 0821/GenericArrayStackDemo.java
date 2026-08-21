class ArrayStack<T> {
    private Object[] data;
    private int size;

    ArrayStack(int capacity) {
        data = new Object[Math.max(1, capacity)];
    }

    boolean push(T value) {
        if (isFull()) {
            return false;
        }
        data[size] = value;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    T pop() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T value = (T) data[size];
        data[size] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    T peek() {
        return isEmpty() ? null : (T) data[size - 1];
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == data.length;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<String> textStack = new ArrayStack<>(2);
        System.out.println("push A：" + textStack.push("A"));
        System.out.println("push B：" + textStack.push("B"));
        System.out.println("push C：" + textStack.push("C"));
        System.out.println("peek：" + textStack.peek());
        System.out.println("pop：" + textStack.pop());
        System.out.println("size：" + textStack.size());

        ArrayStack<Integer> numberStack = new ArrayStack<>(3);
        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);
        System.out.println("isFull：" + numberStack.isFull());
        System.out.println("pop：" + numberStack.pop());
        System.out.println("isEmpty：" + numberStack.isEmpty());
    }
}
