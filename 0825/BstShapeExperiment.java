class ExperimentNode {
    int value;
    ExperimentNode left;
    ExperimentNode right;

    ExperimentNode(int value) {
        this.value = value;
    }
}

class ExperimentBst {
    private ExperimentNode root;
    private int comparisons;

    boolean add(int value) {
        if (root == null) {
            root = new ExperimentNode(value);
            return true;
        }
        ExperimentNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ExperimentNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ExperimentNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        ExperimentNode current = root;
        while (current != null) {
            comparisons++;
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    int height() {
        return height(root);
    }

    private int height(ExperimentNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    int totalComparisons() {
        return comparisons;
    }
}

public class BstShapeExperiment {
    static void runExperiment(String label, int[] insertOrder, int[] searchValues) {
        ExperimentBst tree = new ExperimentBst();
        for (int value : insertOrder) {
            tree.add(value);
        }
        for (int value : searchValues) {
            tree.contains(value);
        }
        System.out.println(label + " height=" + tree.height() + " comparisons=" + tree.totalComparisons());
    }

    public static void main(String[] args) {
        int[] values = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        int[] sortedOrder = values.clone();
        java.util.Arrays.sort(sortedOrder);

        int[] balancedOrder = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        int[] shuffledOrder = {8, 1, 15, 4, 12, 2, 9, 6, 14, 3, 10, 7, 13, 5, 11};

        runExperiment("sorted-order", sortedOrder, values);
        runExperiment("balanced-order", balancedOrder, values);
        runExperiment("shuffled-order", shuffledOrder, values);
    }
}
