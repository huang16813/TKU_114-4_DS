import java.util.ArrayList;
import java.util.List;

class CollectNode {
    String value;
    CollectNode left;
    CollectNode right;

    CollectNode(String value) {
        this.value = value;
    }
}

public class TraversalResultCollector {
    static List<String> preorder(CollectNode node) {
        List<String> result = new ArrayList<>();
        collectPreorder(node, result);
        return result;
    }

    private static void collectPreorder(CollectNode node, List<String> result) {
        if (node == null) {
            return;
        }
        result.add(node.value);
        collectPreorder(node.left, result);
        collectPreorder(node.right, result);
    }

    static List<String> inorder(CollectNode node) {
        List<String> result = new ArrayList<>();
        collectInorder(node, result);
        return result;
    }

    private static void collectInorder(CollectNode node, List<String> result) {
        if (node == null) {
            return;
        }
        collectInorder(node.left, result);
        result.add(node.value);
        collectInorder(node.right, result);
    }

    static List<String> postorder(CollectNode node) {
        List<String> result = new ArrayList<>();
        collectPostorder(node, result);
        return result;
    }

    private static void collectPostorder(CollectNode node, List<String> result) {
        if (node == null) {
            return;
        }
        collectPostorder(node.left, result);
        collectPostorder(node.right, result);
        result.add(node.value);
    }

    static void report(String label, CollectNode root) {
        System.out.println(label + " preorder=" + preorder(root));
        System.out.println(label + " inorder=" + inorder(root));
        System.out.println(label + " postorder=" + postorder(root));
    }

    public static void main(String[] args) {
        report("empty", null);

        CollectNode single = new CollectNode("A");
        report("single-node", single);

        CollectNode leftSkewed = new CollectNode("A");
        leftSkewed.left = new CollectNode("B");
        leftSkewed.left.left = new CollectNode("C");
        leftSkewed.left.left.left = new CollectNode("D");
        report("left-skewed", leftSkewed);

        CollectNode complete = new CollectNode("A");
        complete.left = new CollectNode("B");
        complete.right = new CollectNode("C");
        complete.left.left = new CollectNode("D");
        complete.left.right = new CollectNode("E");
        complete.right.left = new CollectNode("F");
        complete.right.right = new CollectNode("G");
        report("complete", complete);
    }
}
