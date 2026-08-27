import java.util.ArrayList;
import java.util.List;

public class Q04_NotificationRouter {
    public interface Channel {
        String name();
        boolean supports(String destination);
        String send(String destination, String message);
    }

    public static class EmailChannel implements Channel {
        @Override
        public String name() {
            return "EMAIL";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            int at = destination.indexOf('@');
            if (at <= 0 || at == destination.length() - 1) {
                return false;
            }
            return destination.indexOf('@', at + 1) == -1;
        }

        @Override
        public String send(String destination, String message) {
            return "EMAIL|" + destination + "|" + message;
        }
    }

    public static class SmsChannel implements Channel {
        @Override
        public String name() {
            return "SMS";
        }

        @Override
        public boolean supports(String destination) {
            if (destination == null) {
                return false;
            }
            String digitsOnly = destination.replace("-", "");
            if (digitsOnly.length() != 10) {
                return false;
            }
            for (char c : digitsOnly.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String send(String destination, String message) {
            return "SMS|" + destination + "|" + message;
        }
    }

    public static List<String> route(List<Channel> channels, String destination, String message) {
        List<String> result = new ArrayList<>();
        if (channels == null || destination == null || message == null) {
            return result;
        }
        for (Channel channel : channels) {
            if (channel != null && channel.supports(destination)) {
                result.add(channel.send(destination, message));
            }
        }
        return result;
    }
}
