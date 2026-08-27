import java.util.ArrayList;
import java.util.List;

class EdgeCaseNode {
    int value;
    EdgeCaseNode left;
    EdgeCaseNode right;

    EdgeCaseNode(int value) {
        this.value = value;
    }
}

class EdgeCaseBst {
    private EdgeCaseNode root;

    boolean add(int value) {
        if (root == null) {
            root = new EdgeCaseNode(value);
            return true;
        }

        EdgeCaseNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new EdgeCaseNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new EdgeCaseNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int target) {
        EdgeCaseNode current = root;
        while (current != null) {
            if (target == current.value) return true;
            current = target < current.value ? current.left : current.right;
        }
        return false;
    }

    List<Integer> searchPath(int target) {
        List<Integer> path = new ArrayList<>();
        EdgeCaseNode current = root;
        while (current != null) {
            path.add(current.value);
            if (target == current.value) break;
            current = target < current.value ? current.left : current.right;
        }
        return path;
    }

    List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(EdgeCaseNode node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    List<Integer> preorder() {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    private void preorder(EdgeCaseNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }

    List<Integer> postorder() {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(EdgeCaseNode node, List<Integer> result) {
        if (node == null) return;
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }

    int size() {
        return size(root);
    }

    private int size(EdgeCaseNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    int height() {
        return height(root);
    }

    private int height(EdgeCaseNode node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    int leafCount() {
        return leafCount(root);
    }

    private int leafCount(EdgeCaseNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return leafCount(node.left) + leafCount(node.right);
    }

    String deleteCase(int target) {
        EdgeCaseNode node = findNode(target);
        if (node == null) return "MISSING";
        if (node.left == null && node.right == null) return "LEAF";
        if (node.left == null || node.right == null) return "ONE_CHILD";
        return "TWO_CHILDREN";
    }

    private EdgeCaseNode findNode(int target) {
        EdgeCaseNode current = root;
        while (current != null) {
            if (target == current.value) return current;
            current = target < current.value ? current.left : current.right;
        }
        return null;
    }

    boolean remove(int target) {
        if (!contains(target)) return false;
        root = remove(root, target);
        return true;
    }

    private EdgeCaseNode remove(EdgeCaseNode node, int target) {
        if (target < node.value) {
            node.left = remove(node.left, target);
        } else if (target > node.value) {
            node.right = remove(node.right, target);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            EdgeCaseNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private EdgeCaseNode minimumNode(EdgeCaseNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    List<Integer> range(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low <= high) range(root, low, high, result);
        return result;
    }

    private void range(EdgeCaseNode node, int low, int high,
                       List<Integer> result) {
        if (node == null) return;
        if (low < node.value) range(node.left, low, high, result);
        if (low <= node.value && node.value <= high) result.add(node.value);
        if (node.value < high) range(node.right, low, high, result);
    }

    boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(EdgeCaseNode node, long low, long high) {
        if (node == null) return true;
        if (node.value <= low || node.value >= high) return false;
        return isValid(node.left, low, node.value)
                && isValid(node.right, node.value, high);
    }

    void forceDeepViolationForTest() {
        if (root != null && root.left != null && root.left.right != null) {
            root.left.right.value = root.value + 5;
        }
    }
}

public class BstEdgeCaseTestDemo {
    private static EdgeCaseBst standardTree() {
        EdgeCaseBst tree = new EdgeCaseBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        return tree;
    }

    public static void main(String[] args) {
        EdgeCaseBst tree = new EdgeCaseBst();
        System.out.println("emptyFind=" + tree.contains(10));
        System.out.println("emptyRemove=" + tree.remove(10));
        System.out.println("addRoot=" + tree.add(10));
        System.out.println("duplicate=" + tree.add(10));
        System.out.println("removeRoot=" + tree.remove(10));
        System.out.println("size=" + tree.size());
        System.out.println("valid=" + tree.isValid());
    }
}
