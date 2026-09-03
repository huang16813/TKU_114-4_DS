import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Q01_PriorityRecord {
    public record Job(String id, int priority, long sequence) {}

    public static List<String> processOrder(List<Job> jobs) {
        List<String> result = new ArrayList<>();
        if (jobs == null || jobs.isEmpty()) {
            return result;
        }

        Comparator<Job> order = Comparator.comparingInt(Job::priority)
                .thenComparingLong(Job::sequence)
                .thenComparing(Job::id);

        PriorityQueue<Job> queue = new PriorityQueue<>(order);
        for (Job job : jobs) {
            if (job != null) {
                queue.offer(job);
            }
        }

        while (!queue.isEmpty()) {
            result.add(queue.poll().id());
        }
        return result;
    }

    public static void main(String[] args) {
        List<Job> jobs = List.of(
                new Job("J1", 3, 1),
                new Job("J2", 1, 4),
                new Job("J3", 1, 2),
                new Job("J4", 2, 3));

        System.out.println("order=" + processOrder(jobs));
        System.out.println("empty=" + processOrder(List.of()));
        System.out.println("null=" + processOrder(null));

        List<Job> original = new ArrayList<>(jobs);
        processOrder(original);
        System.out.println("input unchanged=" + original.equals(jobs));
    }
}
