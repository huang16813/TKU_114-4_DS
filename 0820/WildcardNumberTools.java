import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {
    static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (Number value : values) {
            total += value.doubleValue();
        }
        return total / values.size();
    }

    static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }
        double max = Double.NEGATIVE_INFINITY;
        for (Number value : values) {
            double current = value.doubleValue();
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    static void addRange(List<? super Integer> target, int start, int end) {
        if (target == null || start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>(List.of(10, 20, 30));
        List<Double> doubles = new ArrayList<>(List.of(1.5, 2.5, 3.5));
        List<Integer> empty = new ArrayList<>();

        System.out.println("average(int)=" + average(integers));
        System.out.println("average(double)=" + average(doubles));
        System.out.println("maximum(int)=" + maximum(integers));
        System.out.println("average(empty)=" + average(empty));
        System.out.println("maximum(empty)=" + maximum(empty));

        addRange(integers, 40, 42);
        System.out.println("after addRange=" + integers);
        addRange(integers, 5, 1);
        System.out.println("after invalid range=" + integers);
    }
}
