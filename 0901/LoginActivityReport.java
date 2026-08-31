import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LoginActivityReport {
    record LoginRecord(String account, String ip) {}

    static Map<String, Integer> countLogins(List<LoginRecord> records) {
        Map<String, Integer> counts = new HashMap<>();
        if (records == null) {
            return counts;
        }
        for (LoginRecord record : records) {
            if (record == null || record.account() == null) {
                continue;
            }
            counts.merge(record.account(), 1, Integer::sum);
        }
        return counts;
    }

    static Map<String, Set<String>> distinctIps(List<LoginRecord> records) {
        Map<String, Set<String>> ipsByAccount = new HashMap<>();
        if (records == null) {
            return ipsByAccount;
        }
        for (LoginRecord record : records) {
            if (record == null || record.account() == null || record.ip() == null) {
                continue;
            }
            ipsByAccount.computeIfAbsent(record.account(), key -> new HashSet<>()).add(record.ip());
        }
        return ipsByAccount;
    }

    static List<String> abnormalAccounts(List<LoginRecord> records, int ipThreshold) {
        Map<String, Set<String>> ipsByAccount = distinctIps(records);
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : new TreeMap<>(ipsByAccount).entrySet()) {
            if (entry.getValue().size() >= ipThreshold) {
                result.add(entry.getKey() + " ips=" + entry.getValue().size());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<LoginRecord> records = List.of(
                new LoginRecord("alice", "1.1.1.1"),
                new LoginRecord("alice", "1.1.1.1"),
                new LoginRecord("alice", "2.2.2.2"),
                new LoginRecord("bob", "3.3.3.3"),
                new LoginRecord("alice", "4.4.4.4"),
                new LoginRecord("bob", "3.3.3.3")
        );

        System.out.println("counts=" + countLogins(records));
        System.out.println("alice distinct ips=" + distinctIps(records).get("alice").size());
        System.out.println("abnormal(>=3 ips)=" + abnormalAccounts(records, 3));
    }
}
