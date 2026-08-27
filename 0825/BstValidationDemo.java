class ValidationNode {
    int value;
    ValidationNode left;
    ValidationNode right;

    ValidationNode(int value) {
        this.value = value;
    }
}

class ValidationBst {
    private ValidationNode root;

    boolean add(int value) {
        if (root == null) {
            root = new ValidationNode(value);
            return true;
        }
        ValidationNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new ValidationNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new ValidationNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        ValidationNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    Integer minimum() {
        if (root == null) return null;
        ValidationNode current = root;
        while (current.left != null) current = current.left;
        return current.value;
    }

    Integer maximum() {
        if (root == null) return null;
        ValidationNode current = root;
        while (current.right != null) current = current.right;
        return current.value;
    }

    int size() {
        return size(root);
    }

    private int size(ValidationNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(ValidationNode node) {
        return node == null
                ? -1
                : 1 + Math.max(height(node.left), height(node.right));
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(ValidationNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(ValidationNode node, long minimum, long maximum) {
        if (node == null) return true;
        if (node.value <= minimum || node.value >= maximum) return false;
        return isValid(node.left, minimum, node.value)
                && isValid(node.right, node.value, maximum);
    }
}

public class BstValidationDemo {
    public static void main(String[] args) {
        ValidationBst tree = new ValidationBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        System.out.println("valid=" + tree.isValid());
    }
}
