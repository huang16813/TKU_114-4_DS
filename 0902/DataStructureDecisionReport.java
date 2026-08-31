import java.util.List;

public class DataStructureDecisionReport {
    record Requirement(String scenario, String choice, String reason, String bigO) {}

    static List<Requirement> requirements() {
        return List.of(
                new Requirement("依索引隨機存取大量資料", "ArrayList",
                        "index 存取需要 O(1)，中間插入頻率低", "get O(1)"),
                new Requirement("先進先出的排隊任務", "ArrayDeque as Queue",
                        "只需要頭尾操作，不需要任意位置存取", "offer/poll O(1)"),
                new Requirement("復原/重做的操作歷史", "ArrayDeque as Stack",
                        "只在同一端加入與移除，符合 LIFO", "push/pop O(1)"),
                new Requirement("依成績區間查詢學生", "TreeMap / Balanced BST",
                        "需要維持排序並支援 range query", "平均 O(log n)"),
                new Requirement("每次找出最高優先權工作", "PriorityQueue / Heap",
                        "只需要極值，不需要完整排序", "peek O(1)，add/remove O(log n)"),
                new Requirement("以 id 快速查找會員資料", "HashMap",
                        "依 key 直接查找，不需要順序", "平均 O(1)"),
                new Requirement("檢查是否已存在重複標籤", "HashSet",
                        "只需要 membership test，不需要保存 value", "平均 O(1)"),
                new Requirement("保存並走訪人際關係網路", "Graph adjacency list",
                        "需要保存多對多的連結關係", "BFS/DFS O(V+E)"),
                new Requirement("保存插入順序且不允許重複", "LinkedHashSet",
                        "需要去重同時保留加入順序", "平均 O(1)"),
                new Requirement("依到達順序處理客服請求", "ArrayDeque as Queue",
                        "FIFO 語意，只在頭尾操作", "offer/poll O(1)"),
                new Requirement("查詢兩地點間的最短跳數路徑", "Graph + BFS",
                        "無權重最短路徑用 BFS 天然正確", "O(V+E)"),
                new Requirement("儲存密集且頂點數固定的地圖", "Adjacency matrix",
                        "頂點少、edge 密集時查詢單一 edge 較直接", "O(1) 查詢, O(V^2) 空間")
        );
    }

    public static void main(String[] args) {
        for (Requirement requirement : requirements()) {
            System.out.println(requirement.scenario() + " -> " + requirement.choice()
                    + " | reason: " + requirement.reason()
                    + " | Big-O: " + requirement.bigO());
        }
    }
}
