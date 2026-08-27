class CountNode {
    int key;
    int count;
    CountNode left;
    CountNode right;

    CountNode(int key) {
        this.key = key;
        this.count = 1;
    }
}

class CountBst {
    private CountNode root;

    void add(int key) {
        root = add(root, key);
    }

    private CountNode add(CountNode node, int key) {
        if (node == null) {
            return new CountNode(key);
        }
        if (key == node.key) {
            node.count++;
        } else if (key < node.key) {
            node.left = add(node.left, key);
        } else {
            node.right = add(node.right, key);
        }
        return node;
    }

    void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(CountNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + "(" + node.count + ") ");
        inorder(node.right);
    }
}

public class BstDuplicateCounter {
    public static void main(String[] args) {
        CountBst tree = new CountBst();
        int[] values = {50, 30, 70, 30, 20, 50, 50};
        for (int value : values) {
            tree.add(value);
        }
        tree.inorder();
    }
}
