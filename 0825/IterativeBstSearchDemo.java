class IterSearchNode {
    int value;
    IterSearchNode left;
    IterSearchNode right;

    IterSearchNode(int value) {
        this.value = value;
    }
}

class IterSearchBst {
    private IterSearchNode root;

    boolean add(int value) {
        if (root == null) {
            root = new IterSearchNode(value);
            return true;
        }
        IterSearchNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new IterSearchNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new IterSearchNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        IterSearchNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    Integer minimum() {
        if (root == null) return null;
        IterSearchNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        IterSearchNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    int size() {
        return size(root);
    }

    private int size(IterSearchNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(IterSearchNode node) {
        return node == null
                ? -1
                : 1 + Math.max(height(node.left), height(node.right));
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(IterSearchNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}

public class IterativeBstSearchDemo {
    public static void main(String[] args) {
        IterSearchBst tree = new IterSearchBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println("20=" + tree.contains(20));
        System.out.println("90=" + tree.contains(90));
    }
}
