class CaseNode {
    int value;
    CaseNode left;
    CaseNode right;

    CaseNode(int value) {
        this.value = value;
    }
}

class CaseBst {
    private CaseNode root;

    boolean add(int value) {
        if (root == null) {
            root = new CaseNode(value);
            return true;
        }
        CaseNode current = root;
        while (true) {
            if (value == current.value) return false;
            if (value < current.value) {
                if (current.left == null) {
                    current.left = new CaseNode(value);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new CaseNode(value);
                    return true;
                }
                current = current.right;
            }
        }
    }

    boolean contains(int value) {
        CaseNode current = root;
        while (current != null) {
            if (value == current.value) return true;
            current = value < current.value ? current.left : current.right;
        }
        return false;
    }

    int size() {
        return size(root);
    }

    private int size(CaseNode node) {
        return node == null ? 0 : 1 + size(node.left) + size(node.right);
    }

    boolean remove(int value) {
        if (!contains(value)) return false;
        root = remove(root, value);
        return true;
    }

    private CaseNode remove(CaseNode node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = remove(node.left, value);
        } else if (value > node.value) {
            node.right = remove(node.right, value);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            CaseNode successor = minimumNode(node.right);
            node.value = successor.value;
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    private CaseNode minimumNode(CaseNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(CaseNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(CaseNode node, long minimum, long maximum) {
        if (node == null) return true;
        if (node.value <= minimum || node.value >= maximum) return false;
        return isValid(node.left, minimum, node.value)
                && isValid(node.right, node.value, maximum);
    }
}

public class BstDeleteCases {
    static void report(String label, CaseBst tree) {
        System.out.println(label + " inorder:");
        tree.inorder();
        System.out.println(label + " size=" + tree.size() + " valid=" + tree.isValid());
    }

    public static void main(String[] args) {
        CaseBst tree = new CaseBst();
        for (int value : new int[]{50, 30, 70, 20, 40, 60, 80}) {
            tree.add(value);
        }
        report("initial", tree);

        System.out.println("remove leaf 20=" + tree.remove(20));
        report("after leaf delete", tree);

        System.out.println("remove single-child 30=" + tree.remove(30));
        report("after single-child delete", tree);

        System.out.println("remove two-children 70=" + tree.remove(70));
        report("after two-children delete", tree);
    }
}
