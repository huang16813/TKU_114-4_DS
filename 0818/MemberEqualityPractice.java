import java.util.Objects;

class LibraryMember {
    private final String memberId;
    private String name;
    private String email;

    LibraryMember(String memberId, String name, String email) {
        this.memberId = (memberId == null || memberId.isBlank()) ? "Unknown" : memberId;
        this.name = (name == null || name.isBlank()) ? "Unknown" : name;
        this.email = (email == null || email.isBlank()) ? "Unknown" : email;
    }

    @Override
    public String toString() {
        return "LibraryMember{memberId='" + memberId + "', name='" + name
                + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryMember)) {
            return false;
        }
        LibraryMember member = (LibraryMember) other;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {
    public static void main(String[] args) {
        LibraryMember a = new LibraryMember("M001", "Amy", "amy@example.com");
        LibraryMember b = new LibraryMember("M001", "Amy Chen", "amy.chen@example.com");

        System.out.println(a);
        System.out.println(b);
        System.out.println("a == b：" + (a == b));
        System.out.println("a.equals(b)：" + a.equals(b));
        System.out.println("a.equals(null)：" + a.equals(null));
    }
}
