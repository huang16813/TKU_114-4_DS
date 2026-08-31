import java.util.LinkedHashSet;
import java.util.Set;

public class InterestSetComparison {
    static <T> Set<T> union(Set<T> first, Set<T> second) {
        Set<T> result = new LinkedHashSet<>(first);
        result.addAll(second);
        return result;
    }

    static <T> Set<T> intersection(Set<T> first, Set<T> second) {
        Set<T> result = new LinkedHashSet<>(first);
        result.retainAll(second);
        return result;
    }

    static <T> Set<T> firstOnly(Set<T> first, Set<T> second) {
        Set<T> result = new LinkedHashSet<>(first);
        result.removeAll(second);
        return result;
    }

    static <T> Set<T> secondOnly(Set<T> first, Set<T> second) {
        Set<T> result = new LinkedHashSet<>(second);
        result.removeAll(first);
        return result;
    }

    public static void main(String[] args) {
        Set<String> alice = new LinkedHashSet<>(Set.of("Reading", "Hiking", "Music"));
        Set<String> bob = new LinkedHashSet<>(Set.of("Music", "Gaming", "Hiking"));

        System.out.println("union=" + union(alice, bob));
        System.out.println("intersection=" + intersection(alice, bob));
        System.out.println("aliceOnly=" + firstOnly(alice, bob));
        System.out.println("bobOnly=" + secondOnly(alice, bob));

        System.out.println("alice unchanged=" + alice);
        System.out.println("bob unchanged=" + bob);
    }
}
