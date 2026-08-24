import java.util.List;
import java.util.ArrayList;

class TestNode {
    String value;
    TestNode left;
    TestNode right;

    TestNode(String value) {
        this.value = value;
    }
}

public class TraversalTestReport {
    static List<String> preorder(TestNode node) {
        List<String> result = new ArrayList<>();
        collectPreorder(node, result);
        return result;
    }

    private static void collectPreorder(TestNode node, List<String> result) {
        if (node == null) {
            return;
        }
        result.add(node.value);
        collectPreorder(node.left, result);
        collectPreorder(node.right, result);
    }

    static List<String> inorder(TestNode node) {
        List<String> result = new ArrayList<>();
        collectInorder(node, result);
        return result;
    }

    private static void collectInorder(TestNode node, List<String> result) {
        if (node == null) {
            return;
        }
        collectInorder(node.left, result);
        result.add(node.value);
        collectInorder(node.right, result);
    }

    static List<String> postorder(TestNode node) {
        List<String> result = new ArrayList<>();
        collectPostorder(node, result);
        return result;
    }

    private static void collectPostorder(TestNode node, List<String> result) {
        if (node == null) {
            return;
        }
        collectPostorder(node.left, result);
        collectPostorder(node.right, result);
        result.add(node.value);
    }

    static void verify(String label, TestNode root,
            List<String> expectedPreorder, List<String> expectedInorder, List<String> expectedPostorder) {
        List<String> actualPreorder = preorder(root);
        List<String> actualInorder = inorder(root);
        List<String> actualPostorder = postorder(root);

        System.out.println(label + " preorder expected=" + expectedPreorder + " actual=" + actualPreorder
                + " match=" + expectedPreorder.equals(actualPreorder));
        System.out.println(label + " inorder expected=" + expectedInorder + " actual=" + actualInorder
                + " match=" + expectedInorder.equals(actualInorder));
        System.out.println(label + " postorder expected=" + expectedPostorder + " actual=" + actualPostorder
                + " match=" + expectedPostorder.equals(actualPostorder));
    }

    public static void main(String[] args) {
        verify("empty", null,
                List.of(), List.of(), List.of());

        TestNode single = new TestNode("A");
        verify("single-node", single,
                List.of("A"), List.of("A"), List.of("A"));

        TestNode onlyLeft = new TestNode("A");
        onlyLeft.left = new TestNode("B");
        onlyLeft.left.left = new TestNode("C");
        verify("only-left", onlyLeft,
                List.of("A", "B", "C"), List.of("C", "B", "A"), List.of("C", "B", "A"));

        TestNode onlyRight = new TestNode("A");
        onlyRight.right = new TestNode("B");
        onlyRight.right.right = new TestNode("C");
        verify("only-right", onlyRight,
                List.of("A", "B", "C"), List.of("A", "B", "C"), List.of("C", "B", "A"));

        TestNode complete = new TestNode("A");
        complete.left = new TestNode("B");
        complete.right = new TestNode("C");
        complete.left.left = new TestNode("D");
        complete.left.right = new TestNode("E");
        complete.right.left = new TestNode("F");
        complete.right.right = new TestNode("G");
        verify("complete", complete,
                List.of("A", "B", "D", "E", "C", "F", "G"),
                List.of("D", "B", "E", "A", "F", "C", "G"),
                List.of("D", "E", "B", "F", "G", "C", "A"));

        TestNode irregular = new TestNode("A");
        irregular.left = new TestNode("B");
        irregular.right = new TestNode("C");
        irregular.left.right = new TestNode("D");
        irregular.right.left = new TestNode("E");
        irregular.right.left.left = new TestNode("F");
        verify("irregular", irregular,
                List.of("A", "B", "D", "C", "E", "F"),
                List.of("B", "D", "A", "F", "E", "C"),
                List.of("D", "B", "F", "E", "C", "A"));
    }
}
