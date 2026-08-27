import java.util.ArrayList;
import java.util.List;

class AuditNode {
    int value;
    AuditNode left;
    AuditNode right;

    AuditNode(int value) {
        this.value = value;
    }
}

class AuditBst {
    private AuditNode root;

    boolean add(int value) {
        if (root == null) {
            root = new AuditNode(value);
            return true;
        }
        AuditNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new AuditNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new AuditNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        AuditNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    boolean remove(int value) {
        if (!contains(value)) return false;
        root = remove(root, value);
        return true;
    }

    private AuditNode remove(AuditNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            AuditNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private AuditNode minimumNode(AuditNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(AuditNode node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    int size() {
        return size(root);
    }

    private int size(AuditNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(AuditNode node) {
        return node == null ? -1 : 1 + Math.max(height(node.left), height(node.right));
    }

    boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(AuditNode node, long low, long high) {
        if (node == null) return true;
        if (node.value <= low || node.value >= high) return false;
        return isValid(node.left, low, node.value) && isValid(node.right, node.value, high);
    }
}

public class BstOperationAudit {
    private static void audit(String operation, boolean result, AuditBst tree) {
        System.out.println(operation + " result=" + result
                + " inorder=" + tree.inorder()
                + " size=" + tree.size()
                + " height=" + tree.height()
                + " valid=" + tree.isValid());
    }

    public static void main(String[] args) {
        AuditBst tree = new AuditBst();

        audit("add 50", tree.add(50), tree);
        audit("add 30", tree.add(30), tree);
        audit("add 70", tree.add(70), tree);
        audit("add 20", tree.add(20), tree);
        audit("add 40", tree.add(40), tree);
        audit("add 60", tree.add(60), tree);
        audit("add 80", tree.add(80), tree);
        audit("add duplicate 40", tree.add(40), tree);

        audit("remove leaf 20", tree.remove(20), tree);
        audit("remove one-child 30", tree.remove(30), tree);
        audit("remove two-children 50", tree.remove(50), tree);
        audit("remove missing 999", tree.remove(999), tree);
    }
}
