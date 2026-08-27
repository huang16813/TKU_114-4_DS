class ShapeNode {
    int value;
    ShapeNode left;
    ShapeNode right;

    ShapeNode(int value) {
        this.value = value;
    }
}

class ShapeBst {
    private ShapeNode root;
    private int comparisons;

    boolean add(int value) {
        if (root == null) {
            root = new ShapeNode(value);
            return true;
        }
        ShapeNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ShapeNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ShapeNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        ShapeNode current = root;
        while (current != null) {
            comparisons++;
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    int size() {
        return size(root);
    }

    private int size(ShapeNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(ShapeNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    int totalComparisons() {
        return comparisons;
    }
}

public class SkewedBstReport {
    public static void main(String[] args) {
        int[] sortedOrder = {10, 20, 30, 40, 50, 60, 70};
        int[] balancedOrder = {40, 20, 60, 10, 30, 50, 70};

        ShapeBst sortedTree = new ShapeBst();
        for (int value : sortedOrder) {
            sortedTree.add(value);
        }

        ShapeBst balancedTree = new ShapeBst();
        for (int value : balancedOrder) {
            balancedTree.add(value);
        }

        for (int value : sortedOrder) {
            sortedTree.contains(value);
        }
        for (int value : balancedOrder) {
            balancedTree.contains(value);
        }

        System.out.println("sorted-order tree size=" + sortedTree.size()
                + " height=" + sortedTree.height()
                + " comparisons=" + sortedTree.totalComparisons());
        System.out.println("balanced-order tree size=" + balancedTree.size()
                + " height=" + balancedTree.height()
                + " comparisons=" + balancedTree.totalComparisons());
    }
}
