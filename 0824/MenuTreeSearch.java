class MenuNode {
    String label;
    MenuNode left;
    MenuNode right;

    MenuNode(String label) {
        this.label = label;
    }
}

public class MenuTreeSearch {
    static boolean contains(MenuNode node, String target) {
        if (node == null || target == null) {
            return false;
        }
        return node.label.equals(target)
                || contains(node.left, target)
                || contains(node.right, target);
    }

    static int findDepth(MenuNode node, String target) {
        return findDepth(node, target, 0);
    }

    private static int findDepth(MenuNode node, String target, int depth) {
        if (node == null) {
            return -1;
        }
        if (node.label.equals(target)) {
            return depth;
        }
        int leftResult = findDepth(node.left, target, depth + 1);
        if (leftResult != -1) {
            return leftResult;
        }
        return findDepth(node.right, target, depth + 1);
    }

    static int countLeaves(MenuNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaves(node.left) + countLeaves(node.right);
    }

    static void preorderDisplay(MenuNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.label + " ");
        preorderDisplay(node.left);
        preorderDisplay(node.right);
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("Main");
        root.left = new MenuNode("Food");
        root.right = new MenuNode("Drink");
        root.left.left = new MenuNode("Noodle");
        root.left.right = new MenuNode("Rice");
        root.right.left = new MenuNode("Tea");

        preorderDisplay(root);
        System.out.println();

        System.out.println("contains Rice=" + contains(root, "Rice"));
        System.out.println("contains Coffee=" + contains(root, "Coffee"));

        System.out.println("findDepth Tea=" + findDepth(root, "Tea"));
        System.out.println("findDepth Main=" + findDepth(root, "Main"));
        System.out.println("findDepth Coffee=" + findDepth(root, "Coffee"));

        System.out.println("countLeaves=" + countLeaves(root));
    }
}
