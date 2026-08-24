class StatsNode {
    int value;
    StatsNode left;
    StatsNode right;

    StatsNode(int value) {
        this.value = value;
    }
}

public class BinaryTreeStatistics {
    static int size(StatsNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    static int sum(StatsNode node) {
        return node == null ? 0 : node.value + sum(node.left) + sum(node.right);
    }

    static int maximum(StatsNode node) {
        if (node == null) {
            throw new IllegalStateException("empty tree has no maximum");
        }
        return maximumRecursive(node);
    }

    private static int maximumRecursive(StatsNode node) {
        int result = node.value;
        if (node.left != null) {
            result = Math.max(result, maximumRecursive(node.left));
        }
        if (node.right != null) {
            result = Math.max(result, maximumRecursive(node.right));
        }
        return result;
    }

    static int leafCount(StatsNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return leafCount(node.left) + leafCount(node.right);
    }

    static int height(StatsNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    static boolean contains(StatsNode node, int target) {
        if (node == null) {
            return false;
        }
        return node.value == target || contains(node.left, target) || contains(node.right, target);
    }

    public static void main(String[] args) {
        StatsNode root = new StatsNode(10);
        root.left = new StatsNode(5);
        root.right = new StatsNode(20);
        root.left.left = new StatsNode(3);
        root.left.right = new StatsNode(7);
        root.right.right = new StatsNode(25);

        System.out.println("size=" + size(root));
        System.out.println("sum=" + sum(root));
        System.out.println("maximum=" + maximum(root));
        System.out.println("leafCount=" + leafCount(root));
        System.out.println("height=" + height(root));
        System.out.println("contains 7=" + contains(root, 7));
        System.out.println("contains 99=" + contains(root, 99));

        try {
            maximum(null);
        } catch (IllegalStateException e) {
            System.out.println("empty tree maximum exception: " + e.getMessage());
        }
    }
}
