class InvariantNode {
    int value;
    InvariantNode left;
    InvariantNode right;

    InvariantNode(int value) {
        this.value = value;
    }
}

public class BstInvariantChecker {
    static boolean isValid(InvariantNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValid(InvariantNode node, long minimum, long maximum) {
        if (node == null) return true;
        if (node.value <= minimum || node.value >= maximum) return false;
        return isValid(node.left, minimum, node.value)
                && isValid(node.right, node.value, maximum);
    }

    public static void main(String[] args) {
        InvariantNode validRoot = new InvariantNode(50);
        validRoot.left = new InvariantNode(30);
        validRoot.right = new InvariantNode(70);
        validRoot.left.left = new InvariantNode(20);
        validRoot.left.right = new InvariantNode(40);
        System.out.println("valid tree=" + isValid(validRoot));

        InvariantNode invalidRoot = new InvariantNode(50);
        invalidRoot.left = new InvariantNode(30);
        invalidRoot.right = new InvariantNode(70);
        invalidRoot.left.right = new InvariantNode(60);
        invalidRoot.left.right.right = new InvariantNode(65);
        System.out.println("invalid deep tree=" + isValid(invalidRoot));
    }
}
