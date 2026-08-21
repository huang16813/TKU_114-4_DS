import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {
    static void runOperations(String label, List<Integer> data) {
        data.add(10);
        data.add(20);
        data.add(30);
        System.out.println(label + " 尾端新增：" + data);

        data.add(1, 15);
        System.out.println(label + " index 1 插入：" + data);

        data.remove(2);
        System.out.println(label + " index 2 刪除：" + data);

        int total = 0;
        for (int value : data) {
            total += value;
        }
        System.out.println(label + " 總和：" + total);
    }

    public static void main(String[] args) {
        runOperations("ArrayList", new ArrayList<>());
        runOperations("LinkedList", new LinkedList<>());

        System.out.println("說明：兩者操作結果完全一致，"
                + "但 ArrayList 以陣列儲存，指定位置插入／刪除需搬移後續元素；"
                + "LinkedList 以節點串接，指定位置存取需從端點逐步走訪，"
                + "兩者在內部效能特性上並不相同。");
    }
}
