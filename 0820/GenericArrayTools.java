import java.util.Arrays;

public class GenericArrayTools {
    static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (T item : data) {
            if (item == null ? target == null : item.equals(target)) {
                count++;
            }
        }
        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null) {
            return;
        }
        if (first < 0 || first >= data.length
                || second < 0 || second >= data.length) {
            return;
        }
        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {
        String[] names = {"Amy", "Ben", "Amy", "Cara"};

        System.out.println("countMatches(Amy)=" + countMatches(names, "Amy"));
        System.out.println("last=" + last(names));

        swap(names, 0, 3);
        System.out.println("after swap=" + Arrays.toString(names));

        System.out.println("last(empty)=" + last(new String[0]));
        System.out.println("last(null)=" + last(null));
        swap(names, 0, 10);
        System.out.println("swap out of range unchanged=" + Arrays.toString(names));
    }
}
