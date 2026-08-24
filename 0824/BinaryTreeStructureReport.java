class StructNode {
    String value;
    StructNode left;
    StructNode right;

    StructNode(String value) {
        this.value = value;
    }
}

public class BinaryTreeStructureReport {
    static int size(StructNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    static int leafCount(StructNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return leafCount(node.left) + leafCount(node.right);
    }

    static int height(StructNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    static void collectLeaves(StructNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.append(node.value).append(" ");
            return;
        }
        collectLeaves(node.left, result);
        collectLeaves(node.right, result);
    }

    static void printReport(String label, StructNode root) {
        System.out.println(label + " root=" + (root == null ? "EMPTY" : root.value));
        StringBuilder leaves = new StringBuilder();
        collectLeaves(root, leaves);
        System.out.println(label + " leaves=" + leaves.toString().trim());
        System.out.println(label + " size=" + size(root));
        System.out.println(label + " leafCount=" + leafCount(root));
        System.out.println(label + " height=" + height(root));
    }

    public static void main(String[] args) {
        StructNode root = new StructNode("A");
        root.left = new StructNode("B");
        root.right = new StructNode("C");
        root.left.left = new StructNode("D");
        root.left.right = new StructNode("E");
        root.right.left = new StructNode("F");
        root.right.right = new StructNode("G");

        printReport("7-node tree", root);
        System.out.println();

        printReport("empty tree", null);
        System.out.println();

        printReport("single-node tree", new StructNode("Solo"));
    }
}
