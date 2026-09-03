import java.util.ArrayList;
import java.util.List;

public class Q04_ChainedHashTable {
    private record Entry(int key, String value) {}

    private final List<List<Entry>> buckets;
    private int size;

    public Q04_ChainedHashTable(int bucketCount) {
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

    public void put(int key, String value) {
        List<Entry> chain = buckets.get(index(key));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key() == key) {
                chain.set(i, new Entry(key, value));
                return;
            }
        }
        chain.add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        for (Entry entry : buckets.get(index(key))) {
            if (entry.key() == key) {
                return entry.value();
            }
        }
        return null;
    }

    public boolean remove(int key) {
        List<Entry> chain = buckets.get(index(key));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key() == key) {
                chain.remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public int longestChain() {
        int longest = 0;
        for (List<Entry> chain : buckets) {
            longest = Math.max(longest, chain.size());
        }
        return longest;
    }

    public static void main(String[] args) {
        Q04_ChainedHashTable table = new Q04_ChainedHashTable(4);
        table.put(1, "A");
        table.put(5, "B");
        table.put(9, "C");
        table.put(-3, "D");
        table.put(5, "B2");

        System.out.println("size=" + table.size());
        System.out.println("get5=" + table.get(5));
        System.out.println("get9=" + table.get(9));
        System.out.println("longestChain=" + table.longestChain());

        System.out.println("remove1=" + table.remove(1));
        System.out.println("remove missing=" + table.remove(100));
        System.out.println("size after remove=" + table.size());

        try {
            new Q04_ChainedHashTable(0);
        } catch (IllegalArgumentException e) {
            System.out.println("bucketCount<=0 exception: " + e.getMessage());
        }
    }
}
