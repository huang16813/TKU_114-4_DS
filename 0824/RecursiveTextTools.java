public class RecursiveTextTools {
    static String reverse(String text) {
        if (text == null || text.length() <= 1) {
            return text;
        }
        return reverse(text.substring(1)) + text.charAt(0);
    }

    static boolean isPalindrome(String text) {
        String normalized = normalize(text);
        return isPalindromeChecked(normalized, 0, normalized.length() - 1);
    }

    private static String normalize(String text) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    private static boolean isPalindromeChecked(String text, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }
        return isPalindromeChecked(text, left + 1, right - 1);
    }

    static int countCharacter(String text, char target) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        int current = text.charAt(0) == target ? 1 : 0;
        return current + countCharacter(text.substring(1), target);
    }

    public static void main(String[] args) {
        String[] samples = {"", "A", "Level", "A man a plan a canal Panama", "Hello"};

        for (String sample : samples) {
            System.out.println("\"" + sample + "\" reverse=\"" + reverse(sample)
                    + "\" isPalindrome=" + isPalindrome(sample)
                    + " countCharacter(l)=" + countCharacter(sample.toLowerCase(), 'l'));
        }
    }
}
