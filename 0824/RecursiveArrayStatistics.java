public class RecursiveArrayStatistics {
    static int maximum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array must not be null or empty");
        }
        return maximum(values, 0);
    }

    private static int maximum(int[] values, int index) {
        if (index == values.length - 1) {
            return values[index];
        }
        int restMax = maximum(values, index + 1);
        return Math.max(values[index], restMax);
    }

    static int minimum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array must not be null or empty");
        }
        return minimum(values, 0);
    }

    private static int minimum(int[] values, int index) {
        if (index == values.length - 1) {
            return values[index];
        }
        int restMin = minimum(values, index + 1);
        return Math.min(values[index], restMin);
    }

    static int countAbove(int[] values, int threshold) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("array must not be null or empty");
        }
        return countAbove(values, threshold, 0);
    }

    private static int countAbove(int[] values, int threshold, int index) {
        if (index >= values.length) {
            return 0;
        }
        int current = values[index] > threshold ? 1 : 0;
        return current + countAbove(values, threshold, index + 1);
    }

    public static void main(String[] args) {
        int[] values = {4, 9, 2, 15, 6};
        System.out.println("maximum=" + maximum(values));
        System.out.println("minimum=" + minimum(values));
        System.out.println("countAbove(5)=" + countAbove(values, 5));

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println("null exception: " + e.getMessage());
        }

        try {
            minimum(new int[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("empty exception: " + e.getMessage());
        }
    }
}
