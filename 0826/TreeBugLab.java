import java.util.ArrayList;
import java.util.List;

public class TreeBugLab {

    static class BugNode {
        int value;
        BugNode left;
        BugNode right;

        BugNode(int value) {
            this.value = value;
        }
    }

    // Bug 1: search direction reversed
    static boolean buggyContains(BugNode node, int target) {
        while (node != null) {
            if (target == node.value) return true;
            node = target < node.value ? node.right : node.left;
        }
        return false;
    }

    static boolean fixedContains(BugNode node, int target) {
        while (node != null) {
            if (target == node.value) return true;
            node = target < node.value ? node.left : node.right;
        }
        return false;
    }

    // Bug 2: inorder order wrong (root printed before children)
    static void buggyInorder(BugNode node, List<Integer> result) {
        if (node == null) return;
        result.add(node.value);
        buggyInorder(node.left, result);
        buggyInorder(node.right, result);
    }

    static void fixedInorder(BugNode node, List<Integer> result) {
        if (node == null) return;
        fixedInorder(node.left, result);
        result.add(node.value);
        fixedInorder(node.right, result);
    }

    // Bug 3: delete loses child subtree in one-child case
    static BugNode buggyRemove(BugNode node, int target) {
        if (node == null) return null;
        if (target < node.value) {
            node.left = buggyRemove(node.left, target);
        } else if (target > node.value) {
            node.right = buggyRemove(node.right, target);
        } else {
            if (node.left == null || node.right == null) return null;
            BugNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = buggyRemove(node.right, successor.value);
        }
        return node;
    }

    static BugNode fixedRemove(BugNode node, int target) {
        if (node == null) return null;
        if (target < node.value) {
            node.left = fixedRemove(node.left, target);
        } else if (target > node.value) {
            node.right = fixedRemove(node.right, target);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BugNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.value = successor.value;
            node.right = fixedRemove(node.right, successor.value);
        }
        return node;
    }

    // Bug 4: validation only checks direct parent-child
    static boolean buggyIsValid(BugNode node) {
        if (node == null) return true;
        if (node.left != null && node.left.value >= node.value) return false;
        if (node.right != null && node.right.value <= node.value) return false;
        return buggyIsValid(node.left) && buggyIsValid(node.right);
    }

    static boolean fixedIsValid(BugNode node) {
        return fixedIsValid(node, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean fixedIsValid(BugNode node, long low, long high) {
        if (node == null) return true;
        if (node.value <= low || node.value >= high) return false;
        return fixedIsValid(node.left, low, node.value) && fixedIsValid(node.right, node.value, high);
    }

    public static void main(String[] args) {
        BugNode searchTree = new BugNode(50);
        searchTree.left = new BugNode(30);
        searchTree.right = new BugNode(70);
        System.out.println("bug1 buggyContains(30)=" + buggyContains(searchTree, 30)
                + " fixedContains(30)=" + fixedContains(searchTree, 30));

        BugNode traversalTree = new BugNode(50);
        traversalTree.left = new BugNode(30);
        traversalTree.right = new BugNode(70);
        List<Integer> buggyResult = new ArrayList<>();
        buggyInorder(traversalTree, buggyResult);
        List<Integer> fixedResult = new ArrayList<>();
        fixedInorder(traversalTree, fixedResult);
        System.out.println("bug2 buggyInorder=" + buggyResult + " fixedInorder=" + fixedResult);

        BugNode deleteTreeBuggy = new BugNode(50);
        deleteTreeBuggy.left = new BugNode(30);
        deleteTreeBuggy.left.left = new BugNode(20);
        deleteTreeBuggy = buggyRemove(deleteTreeBuggy, 30);
        List<Integer> afterBuggyRemove = new ArrayList<>();
        fixedInorder(deleteTreeBuggy, afterBuggyRemove);
        System.out.println("bug3 buggyRemove result (should still contain 20)=" + afterBuggyRemove);

        BugNode deleteTreeFixed = new BugNode(50);
        deleteTreeFixed.left = new BugNode(30);
        deleteTreeFixed.left.left = new BugNode(20);
        deleteTreeFixed = fixedRemove(deleteTreeFixed, 30);
        List<Integer> afterFixedRemove = new ArrayList<>();
        fixedInorder(deleteTreeFixed, afterFixedRemove);
        System.out.println("fixedRemove result=" + afterFixedRemove);

        BugNode invalidDeep = new BugNode(50);
        invalidDeep.left = new BugNode(30);
        invalidDeep.left.right = new BugNode(60);
        System.out.println("bug4 buggyIsValid(invalid deep tree)=" + buggyIsValid(invalidDeep)
                + " fixedIsValid=" + fixedIsValid(invalidDeep));
    }
}
