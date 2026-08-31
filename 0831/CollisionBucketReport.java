import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {
    private final List<List<Integer>> buckets;

    public CollisionBucketReport(int bucketCount) {
        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount");
        }
        buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    private int index(int key) {
        return Math.floorMod(Integer.hashCode(key), buckets.size());
    }

    public void insert(int key) {
        List<Integer> chain = buckets.get(index(key));
        if (!chain.contains(key)) {
            chain.add(key);
        }
    }

    public void report() {
        int totalCollisions = 0;
        int longestChain = 0;

        for (int i = 0; i < buckets.size(); i++) {
            int count = buckets.get(i).size();
            int collisions = Math.max(0, count - 1);
            totalCollisions += collisions;
            longestChain = Math.max(longestChain, count);
            System.out.println("bucket " + i + " keys=" + count
                    + " collisions=" + collisions + " -> " + buckets.get(i));
        }

        System.out.println("total collisions=" + totalCollisions);
        System.out.println("longest chain=" + longestChain);
    }

    public static void main(String[] args) {
        CollisionBucketReport table = new CollisionBucketReport(5);
        int[] keys = {12, 7, 22, -3, 17, 12, 2};
        for (int key : keys) {
            table.insert(key);
        }
        table.report();
    }
}
