class ShapeCompareNode {
    int value;
    ShapeCompareNode left;
    ShapeCompareNode right;

    ShapeCompareNode(int value) {
        this.value = value;
    }
}

class ShapeCompareBst {
    private ShapeCompareNode root;
    private int comparisons;

    boolean add(int value) {
        if (root == null) {
            root = new ShapeCompareNode(value);
            return true;
        }
        ShapeCompareNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ShapeCompareNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ShapeCompareNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        ShapeCompareNode current = root;
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

    private int height(ShapeCompareNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    int resetAndGetComparisons() {
        int result = comparisons;
        comparisons = 0;
        return result;
    }
}

public class TreeShapeComparison {
    static void runExperiment(String label, int[] insertOrder, int[] allKeys, int missingKey) {
        ShapeCompareBst tree = new ShapeCompareBst();
        for (int value : insertOrder) {
            tree.add(value);
        }
        for (int value : allKeys) {
            tree.contains(value);
        }
        int allKeysComparisons = tree.resetAndGetComparisons();

        tree.contains(missingKey);
        int missingComparisons = tree.resetAndGetComparisons();

        System.out.println(label + " height=" + tree.height()
                + " allKeysComparisons=" + allKeysComparisons
                + " missingKeyComparisons=" + missingComparisons);
    }

    public static void main(String[] args) {
        int[] ascendingOrder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        int[] descendingOrder = {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] balancedOrder = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};

        int missingKey = 100;

        runExperiment("ascending-order", ascendingOrder, ascendingOrder, missingKey);
        runExperiment("descending-order", descendingOrder, ascendingOrder, missingKey);
        runExperiment("near-balanced-order", balancedOrder, ascendingOrder, missingKey);
    }
}
