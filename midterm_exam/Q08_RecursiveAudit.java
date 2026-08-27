public class Q08_RecursiveAudit {
    public static int sumValid(int[] data, int index) {
        if (data == null) {
            return 0;
        }
        int current = index < 0 ? 0 : index;
        if (current >= data.length) {
            return 0;
        }
        int value = (data[current] >= 0 && data[current] <= 100) ? data[current] : 0;
        return value + sumValid(data, current + 1);
    }

    public static int countOccurrences(int[] data, int index, int target) {
        if (data == null) {
            return 0;
        }
        int current = index < 0 ? 0 : index;
        if (current >= data.length) {
            return 0;
        }
        int match = data[current] == target ? 1 : 0;
        return match + countOccurrences(data, current + 1, target);
    }

    public static boolean isPalindrome(String text, int left, int right) {
        if (text == null) {
            return false;
        }
        if (left >= right) {
            return true;
        }
        char leftChar = Character.toLowerCase(text.charAt(left));
        char rightChar = Character.toLowerCase(text.charAt(right));
        if (leftChar != rightChar) {
            return false;
        }
        return isPalindrome(text, left + 1, right - 1);
    }
}
