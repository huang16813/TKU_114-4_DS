import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private final Deque<String> undoStack = new ArrayDeque<>();
    private final Deque<String> redoStack = new ArrayDeque<>();

    void type(String action) {
        undoStack.push(action);
        redoStack.clear();
    }

    String undo() {
        if (undoStack.isEmpty()) {
            return "EMPTY";
        }
        String action = undoStack.pop();
        redoStack.push(action);
        return action;
    }

    String redo() {
        if (redoStack.isEmpty()) {
            return "EMPTY";
        }
        String action = redoStack.pop();
        undoStack.push(action);
        return action;
    }

    void printState() {
        System.out.println("undo=" + undoStack + " redo=" + redoStack);
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        editor.type("輸入 Hello");
        editor.printState();
        editor.type("輸入 World");
        editor.printState();
        editor.type("刪除一行");
        editor.printState();

        System.out.println("undo：" + editor.undo());
        editor.printState();
        System.out.println("undo：" + editor.undo());
        editor.printState();

        System.out.println("redo：" + editor.redo());
        editor.printState();

        editor.type("輸入 New");
        editor.printState();

        System.out.println("redo（新增後應為空）：" + editor.redo());
        editor.printState();

        System.out.println("undo：" + editor.undo());
        System.out.println("undo：" + editor.undo());
        System.out.println("undo（應為空）：" + editor.undo());
        editor.printState();
    }
}
