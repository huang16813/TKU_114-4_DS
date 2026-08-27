class SuiteNode {
    int value;
    SuiteNode left;
    SuiteNode right;

    SuiteNode(int value) {
        this.value = value;
    }
}

class SuiteBst {
    private SuiteNode root;

    boolean add(int value) {
        if (root == null) {
            root = new SuiteNode(value);
            return true;
        }
        SuiteNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new SuiteNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new SuiteNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        SuiteNode current = root;
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

    private SuiteNode remove(SuiteNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            SuiteNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private SuiteNode minimumNode(SuiteNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(SuiteNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }
}

public class BstDeleteTestSuite {
    public static void main(String[] args) {
        SuiteBst empty = new SuiteBst();
        System.out.println("remove from empty tree=" + empty.remove(10));

        SuiteBst missing = new SuiteBst();
        for (int value : new int[]{50, 30, 70}) missing.add(value);
        System.out.println("remove missing value=" + missing.remove(99));

        SuiteBst singleRoot = new SuiteBst();
        singleRoot.add(50);
        System.out.println("remove single root=" + singleRoot.remove(50));
        System.out.println("single root now empty=" + singleRoot.isEmpty());

        SuiteBst oneChildRoot = new SuiteBst();
        oneChildRoot.add(50);
        oneChildRoot.add(30);
        System.out.println("remove root with one child=" + oneChildRoot.remove(50));
        oneChildRoot.inorder();

        SuiteBst twoChildrenRoot = new SuiteBst();
        for (int value : new int[]{50, 30, 70}) twoChildrenRoot.add(value);
        System.out.println("remove root with two children=" + twoChildrenRoot.remove(50));
        twoChildrenRoot.inorder();

        SuiteBst downToEmpty = new SuiteBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) downToEmpty.add(value);
        for (int value : new int[]{20, 40, 30, 60, 80, 70, 50}) {
            System.out.println("remove " + value + "=" + downToEmpty.remove(value));
            downToEmpty.inorder();
        }
        System.out.println("final empty=" + downToEmpty.isEmpty());
    }
}
