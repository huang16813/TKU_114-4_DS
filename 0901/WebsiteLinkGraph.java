import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebsiteLinkGraph {
    private final Map<String, Set<String>> outgoingLinks = new LinkedHashMap<>();

    public void addPage(String page) {
        if (page == null || page.isBlank()) {
            throw new IllegalArgumentException("page");
        }
        outgoingLinks.putIfAbsent(page, new LinkedHashSet<>());
    }

    public boolean addLink(String from, String to) {
        if (!outgoingLinks.containsKey(from) || !outgoingLinks.containsKey(to)) {
            return false;
        }
        return outgoingLinks.get(from).add(to);
    }

    public List<String> outgoingFrom(String page) {
        Set<String> links = outgoingLinks.get(page);
        return links == null ? List.of() : new ArrayList<>(links);
    }

    public int incomingCount(String page) {
        if (!outgoingLinks.containsKey(page)) {
            return 0;
        }
        int count = 0;
        for (Set<String> links : outgoingLinks.values()) {
            if (links.contains(page)) {
                count++;
            }
        }
        return count;
    }

    public List<String> pagesWithNoIncoming() {
        List<String> result = new ArrayList<>();
        for (String page : outgoingLinks.keySet()) {
            if (incomingCount(page) == 0) {
                result.add(page);
            }
        }
        return result;
    }

    public List<String> pagesWithNoOutgoing() {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : outgoingLinks.entrySet()) {
            if (entry.getValue().isEmpty()) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        WebsiteLinkGraph web = new WebsiteLinkGraph();
        for (String page : List.of("Home", "About", "Blog", "Contact")) {
            web.addPage(page);
        }

        web.addLink("Home", "About");
        web.addLink("Home", "Blog");
        web.addLink("About", "Contact");
        web.addLink("Blog", "Contact");

        System.out.println("Home outgoing=" + web.outgoingFrom("Home"));
        System.out.println("Contact incoming=" + web.incomingCount("Contact"));
        System.out.println("noIncoming=" + web.pagesWithNoIncoming());
        System.out.println("noOutgoing=" + web.pagesWithNoOutgoing());
    }
}
