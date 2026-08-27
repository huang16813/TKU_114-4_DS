class PropertyNode {
    int value;
    PropertyNode left;
    PropertyNode right;

    PropertyNode(int value) {
        this.value = value;
    }
}

class PropertyBst {
    private PropertyNode root;

    boolean add(int value) {
        if (root == null) {
            root = new PropertyNode(value);
            return true;
        }
        PropertyNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new PropertyNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new PropertyNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        PropertyNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    Integer minimum() {
        if (root == null) return null;
        PropertyNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        PropertyNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    int size() {
        return size(root);
    }

    private int size(PropertyNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(PropertyNode node) {
        return node == null
                ? -1
                : 1 + Math.max(height(node.left), height(node.right));
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(PropertyNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}

public class BstPropertyDemo {
    public static void main(String[] args) {
        PropertyBst tree = new PropertyBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        tree.inorder();
        System.out.println("30=" + tree.contains(30));
    }
}
