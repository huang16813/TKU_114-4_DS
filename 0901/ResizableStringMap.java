import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap<V> {
    private record Entry<V>(String key, V value) {}

    private List<List<Entry<V>>> buckets;
    private int size;

    public ResizableStringMap(int initialBucketCount) {
        if (initialBucketCount <= 0) {
            throw new IllegalArgumentException("initialBucketCount");
        }
        buckets = createBuckets(initialBucketCount);
    }

    private List<List<Entry<V>>> createBuckets(int count) {
        List<List<Entry<V>>> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(new ArrayList<>());
        }
        return result;
    }

    private int index(String key, int bucketCount) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }
        return Math.floorMod(key.hashCode(), bucketCount);
    }

    public void put(String key, V value) {
        List<Entry<V>> chain = buckets.get(index(key, buckets.size()));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key().equals(key)) {
                chain.set(i, new Entry<>(key, value));
                return;
            }
        }
        chain.add(new Entry<>(key, value));
        size++;
        resizeIfNeeded();
    }

    public V get(String key) {
        for (Entry<V> entry : buckets.get(index(key, buckets.size()))) {
            if (entry.key().equals(key)) {
                return entry.value();
            }
        }
        return null;
    }

    public boolean remove(String key) {
        List<Entry<V>> chain = buckets.get(index(key, buckets.size()));
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).key().equals(key)) {
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

    public int bucketCount() {
        return buckets.size();
    }

    public double loadFactor() {
        return (double) size / buckets.size();
    }

    private void resizeIfNeeded() {
        if (loadFactor() <= 0.75) {
            return;
        }
        List<List<Entry<V>>> newBuckets = createBuckets(buckets.size() * 2);
        for (List<Entry<V>> chain : buckets) {
            for (Entry<V> entry : chain) {
                newBuckets.get(index(entry.key(), newBuckets.size())).add(entry);
            }
        }
        buckets = newBuckets;
        System.out.println("resize -> bucketCount=" + buckets.size());
    }

    public static void main(String[] args) {
        ResizableStringMap<Integer> map = new ResizableStringMap<>(4);
        String[] keys = {"a", "b", "c", "d", "e", "f"};
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], i + 1);
            System.out.printf("put %s size=%d bucketCount=%d load=%.2f%n",
                    keys[i], map.size(), map.bucketCount(), map.loadFactor());
        }

        for (String key : keys) {
            System.out.println("get " + key + "=" + map.get(key));
        }
        System.out.println("remove c=" + map.remove("c"));
        System.out.println("size after remove=" + map.size());
    }
}
