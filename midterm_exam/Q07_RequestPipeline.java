import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {
    public static boolean isBalanced(String text) {
        if (text == null) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty() || !matches(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static List<String> process(String[] commands) {
        List<String> result = new ArrayList<>();
        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue = new ArrayDeque<>();
        Deque<String> urgentQueue = new ArrayDeque<>();

        for (String command : commands) {
            if (command == null || command.isBlank()) {
                continue;
            }
            String[] parts = command.trim().split("\\s+", 2);
            String type = parts[0];

            if ("NORMAL".equals(type) && parts.length == 2) {
                normalQueue.offerLast(parts[1]);
            } else if ("URGENT".equals(type) && parts.length == 2) {
                urgentQueue.offerLast(parts[1]);
            } else if ("PROCESS".equals(type)) {
                String next = urgentQueue.isEmpty()
                        ? normalQueue.pollFirst()
                        : urgentQueue.pollFirst();
                result.add(next == null ? "EMPTY" : next);
            }
        }

        return result;
    }
}
