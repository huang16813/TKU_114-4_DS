class TripleNode {
    char value;
    TripleNode left;
    TripleNode right;

    TripleNode(char value) {
        this.value = value;
    }
}

public class ThreeTraversalPractice {
    static void preorder(TripleNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        result.append(node.value).append(" ");
        preorder(node.left, result);
        preorder(node.right, result);
    }

    static void inorder(TripleNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.append(node.value).append(" ");
        inorder(node.right, result);
    }

    static void postorder(TripleNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        postorder(node.left, result);
        postorder(node.right, result);
        result.append(node.value).append(" ");
    }

    public static void main(String[] args) {
        TripleNode root = new TripleNode('M');
        root.left = new TripleNode('F');
        root.left.left = new TripleNode('B');
        root.right = new TripleNode('T');
        root.right.left = new TripleNode('R');
        root.right.right = new TripleNode('Z');

        StringBuilder pre = new StringBuilder();
        preorder(root, pre);
        System.out.println("preorder: " + pre.toString().trim());

        StringBuilder in = new StringBuilder();
        inorder(root, in);
        System.out.println("inorder: " + in.toString().trim());

        StringBuilder post = new StringBuilder();
        postorder(root, post);
        System.out.println("postorder: " + post.toString().trim());
    }
}
