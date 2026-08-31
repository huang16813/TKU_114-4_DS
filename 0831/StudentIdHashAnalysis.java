import java.util.ArrayList;
import java.util.List;

public class StudentIdHashAnalysis {
    static void analyze(int[] studentIds, int bucketCount) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        for (int id : studentIds) {
            int index = Math.floorMod(Integer.hashCode(id), bucketCount);
            buckets.get(index).add(id);
        }

        int totalCollisions = 0;
        int longestChain = 0;
        int nonEmptyBuckets = 0;

        for (List<Integer> bucket : buckets) {
            int count = bucket.size();
            if (count > 0) {
                nonEmptyBuckets++;
            }
            totalCollisions += Math.max(0, count - 1);
            longestChain = Math.max(longestChain, count);
        }

        double averageChain = nonEmptyBuckets == 0 ? 0.0 : (double) studentIds.length / nonEmptyBuckets;

        System.out.println("bucketCount=" + bucketCount);
        for (int i = 0; i < buckets.size(); i++) {
            System.out.println("  bucket " + i + " count=" + buckets.get(i).size()
                    + " -> " + buckets.get(i));
        }
        System.out.println("total collisions=" + totalCollisions);
        System.out.println("longest chain=" + longestChain);
        System.out.println("average chain length (non-empty buckets)="
                + String.format("%.2f", averageChain));
    }

    public static void main(String[] args) {
        int[] studentIds = {10012, 10007, 10022, 10017, 10003, 10008, 10015, 10025};

        analyze(studentIds, 5);
        System.out.println();
        analyze(studentIds, 11);
    }
}
