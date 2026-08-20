import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList(
                "Amy", "Ben", null, "Amy", "  ", "Cara", "Ben", ""));

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (name == null || name.isBlank()) {
                iterator.remove();
            }
        }

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String name : names) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println("清理後：" + names);
        System.out.println("重複姓名：" + duplicates);
    }
}
