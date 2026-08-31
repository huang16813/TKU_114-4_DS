import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetworkGraph {
    private final Map<String, Set<String>> friends = new LinkedHashMap<>();

    public boolean addUser(String user) {
        if (user == null || user.isBlank()) {
            return false;
        }
        return friends.putIfAbsent(user, new LinkedHashSet<>()) == null;
    }

    public boolean addFriend(String first, String second) {
        if (!friends.containsKey(first) || !friends.containsKey(second) || first.equals(second)) {
            return false;
        }
        boolean changed = friends.get(first).add(second);
        friends.get(second).add(first);
        return changed;
    }

    public boolean removeFriend(String first, String second) {
        if (!friends.containsKey(first) || !friends.containsKey(second)) {
            return false;
        }
        boolean changed = friends.get(first).remove(second);
        friends.get(second).remove(first);
        return changed;
    }

    public List<String> friendsOf(String user) {
        Set<String> result = friends.get(user);
        return result == null ? List.of() : new ArrayList<>(result);
    }

    public List<String> isolatedUsers() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : friends.entrySet()) {
            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SocialNetworkGraph network = new SocialNetworkGraph();
        for (String user : List.of("Amy", "Ben", "Cara", "Dan")) {
            network.addUser(user);
        }

        network.addFriend("Amy", "Ben");
        network.addFriend("Amy", "Cara");

        System.out.println("Amy friends=" + network.friendsOf("Amy"));
        System.out.println("isolated=" + network.isolatedUsers());

        network.removeFriend("Amy", "Cara");
        System.out.println("Amy friends after unfriend=" + network.friendsOf("Amy"));
        System.out.println("isolated after unfriend=" + network.isolatedUsers());
    }
}
