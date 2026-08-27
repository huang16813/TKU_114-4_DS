class ExprNode {
    String value;
    ExprNode left;
    ExprNode right;

    ExprNode(String value) {
        this.value = value;
    }

    boolean isOperator() {
        return left != null && right != null;
    }
}

public class TraversalSelector {
    static String prefix(ExprNode node) {
        if (node == null) return "";
        if (!node.isOperator()) return node.value;
        return node.value + " " + prefix(node.left) + " " + prefix(node.right);
    }

    static String infix(ExprNode node) {
        if (node == null) return "";
        if (!node.isOperator()) return node.value;
        return "(" + infix(node.left) + " " + node.value + " " + infix(node.right) + ")";
    }

    static String postfix(ExprNode node) {
        if (node == null) return "";
        if (!node.isOperator()) return node.value;
        return postfix(node.left) + " " + postfix(node.right) + " " + node.value;
    }

    public static void main(String[] args) {
        ExprNode root = new ExprNode("+");
        root.left = new ExprNode("*");
        root.left.left = new ExprNode("3");
        root.left.right = new ExprNode("4");
        root.right = new ExprNode("5");

        System.out.println("prefix=" + prefix(root));
        System.out.println("infix=" + infix(root));
        System.out.println("postfix=" + postfix(root));
    }
}
