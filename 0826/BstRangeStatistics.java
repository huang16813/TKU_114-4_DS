import java.util.ArrayList;
import java.util.List;

class RangeStatsNode {
    int value;
    RangeStatsNode left;
    RangeStatsNode right;

    RangeStatsNode(int value) {
        this.value = value;
    }
}

class RangeStatsBst {
    private RangeStatsNode root;

    boolean add(int value) {
        if (root == null) {
            root = new RangeStatsNode(value);
            return true;
        }
        RangeStatsNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new RangeStatsNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new RangeStatsNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    List<Integer> valuesBetween(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low <= high) collect(root, low, high, result);
        return result;
    }

    private void collect(RangeStatsNode node, int low, int high, List<Integer> result) {
        if (node == null) return;
        if (low < node.value) collect(node.left, low, high, result);
        if (low <= node.value && node.value <= high) result.add(node.value);
        if (node.value < high) collect(node.right, low, high, result);
    }

    int countBetween(int low, int high) {
        if (low > high) return 0;
        return countBetween(root, low, high);
    }

    private int countBetween(RangeStatsNode node, int low, int high) {
        if (node == null) return 0;
        int count = 0;
        if (low < node.value) count += countBetween(node.left, low, high);
        if (low <= node.value && node.value <= high) count += 1;
        if (node.value < high) count += countBetween(node.right, low, high);
        return count;
    }

    int sumBetween(int low, int high) {
        if (low > high) return 0;
        return sumBetween(root, low, high);
    }

    private int sumBetween(RangeStatsNode node, int low, int high) {
        if (node == null) return 0;
        int total = 0;
        if (low < node.value) total += sumBetween(node.left, low, high);
        if (low <= node.value && node.value <= high) total += node.value;
        if (node.value < high) total += sumBetween(node.right, low, high);
        return total;
    }
}

public class BstRangeStatistics {
    public static void main(String[] args) {
        RangeStatsBst tree = new RangeStatsBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }

        System.out.println("valuesBetween(30,70)=" + tree.valuesBetween(30, 70));
        System.out.println("countBetween(30,70)=" + tree.countBetween(30, 70));
        System.out.println("sumBetween(30,70)=" + tree.sumBetween(30, 70));

        System.out.println("empty range valuesBetween(45,45)=" + tree.valuesBetween(45, 45));
        System.out.println("invalid range low>high valuesBetween(70,30)=" + tree.valuesBetween(70, 30));
        System.out.println("invalid range countBetween(70,30)=" + tree.countBetween(70, 30));
    }
}
