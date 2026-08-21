import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {
    private final Deque<String> history = new ArrayDeque<>();

    void visit(String url) {
        history.push(url);
    }

    String back() {
        if (history.isEmpty()) {
            return "EMPTY";
        }
        history.pop();
        return current();
    }

    String current() {
        return history.isEmpty() ? "EMPTY" : history.peek();
    }

    public static void main(String[] args) {
        BrowserBackStack browser = new BrowserBackStack();

        System.out.println("初始 current：" + browser.current());

        browser.visit("home.html");
        browser.visit("news.html");
        browser.visit("article.html");
        System.out.println("目前頁面：" + browser.current());

        System.out.println("上一頁：" + browser.back());
        System.out.println("上一頁：" + browser.back());
        System.out.println("上一頁：" + browser.back());
        System.out.println("空堆疊再上一頁：" + browser.back());
    }
}
