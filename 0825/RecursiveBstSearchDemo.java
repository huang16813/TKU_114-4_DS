class RecSearchNode {
    int value;
    RecSearchNode left;
    RecSearchNode right;

    RecSearchNode(int value) {
        this.value = value;
    }
}

class RecSearchBst {
    private RecSearchNode root;

    boolean add(int value) {
        if (root == null) {
            root = new RecSearchNode(value);
            return true;
        }
        RecSearchNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new RecSearchNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new RecSearchNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(RecSearchNode node, int value) {
        if (node == null) return false;
        if (value == node.value) return true;
        return value < node.value ? contains(node.left, value) : contains(node.right, value);
    }

    Integer minimum() {
        if (root == null) return null;
        RecSearchNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        RecSearchNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    int size() {
        return size(root);
    }

    private int size(RecSearchNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(RecSearchNode node) {
        return node == null
                ? -1
                : 1 + Math.max(height(node.left), height(node.right));
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(RecSearchNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}

public class RecursiveBstSearchDemo {
    public static void main(String[] args) {
        RecSearchBst tree = new RecSearchBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println("60=" + tree.contains(60));
        System.out.println("65=" + tree.contains(65));
    }
}
