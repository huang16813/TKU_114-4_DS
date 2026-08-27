import java.util.List;
import java.util.ArrayList;

class TestNode {
    int value;
    TestNode left;
    TestNode right;

    TestNode(int value) {
        this.value = value;
    }
}

class TestBst {
    private TestNode root;

    boolean add(int value) {
        if (root == null) {
            root = new TestNode(value);
            return true;
        }
        TestNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new TestNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TestNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        TestNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    boolean isEmpty() {
        return root == null;
    }

    boolean remove(int value) {
        if (!contains(value)) return false;
        root = remove(root, value);
        return true;
    }

    private TestNode remove(TestNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TestNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private TestNode minimumNode(TestNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    int size() {
        return size(root);
    }

    private int size(TestNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TestNode node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    List<Integer> range(int low, int high) {
        List<Integer> result = new ArrayList<>();
        if (low <= high) range(root, low, high, result);
        return result;
    }

    private void range(TestNode node, int low, int high, List<Integer> result) {
        if (node == null) return;
        if (low < node.value) range(node.left, low, high, result);
        if (low <= node.value && node.value <= high) result.add(node.value);
        if (node.value < high) range(node.right, low, high, result);
    }

    boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TestNode node, long low, long high) {
        if (node == null) return true;
        if (node.value <= low || node.value >= high) return false;
        return isValid(node.left, low, node.value) && isValid(node.right, node.value, high);
    }
}

public class CompleteBstTestSuite {
    private static int passCount = 0;
    private static int failCount = 0;

    static void check(String description, boolean condition) {
        if (condition) {
            passCount++;
            System.out.println("PASS - " + description);
        } else {
            failCount++;
            System.out.println("FAIL - " + description);
        }
    }

    public static void main(String[] args) {
        TestBst tree = new TestBst();

        check("new tree isEmpty", tree.isEmpty());
        check("empty tree contains returns false", !tree.contains(10));
        check("empty tree remove returns false", !tree.remove(10));
        check("empty tree size is 0", tree.size() == 0);
        check("empty tree isValid", tree.isValid());

        check("add root 50", tree.add(50));
        check("tree not empty after add", !tree.isEmpty());
        check("add duplicate 50 fails", !tree.add(50));

        check("add 30", tree.add(30));
        check("add 70", tree.add(70));
        check("add 20", tree.add(20));
        check("add 40", tree.add(40));
        check("add 60", tree.add(60));
        check("add 80", tree.add(80));

        check("size is 7", tree.size() == 7);
        check("inorder is sorted", tree.inorder().equals(List.of(20, 30, 40, 50, 60, 70, 80)));
        check("isValid after inserts", tree.isValid());
        check("contains 40", tree.contains(40));
        check("missing 999 not contained", !tree.contains(999));

        check("remove leaf 20", tree.remove(20));
        check("20 no longer contained", !tree.contains(20));

        check("remove one-child 30", tree.remove(30));
        check("30 no longer contained", !tree.contains(30));

        check("remove two-children 50", tree.remove(50));
        check("50 no longer contained", !tree.contains(50));

        check("remove missing 999 fails", !tree.remove(999));

        check("isValid after deletions", tree.isValid());
        check("range(40,80) correct", tree.range(40, 80).equals(List.of(40, 60, 70, 80)));
        check("range with low>high is empty", tree.range(80, 40).isEmpty());

        System.out.println("total pass=" + passCount + " fail=" + failCount);
    }
}
