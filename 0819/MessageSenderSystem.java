interface MessageSender {
    boolean send(String receiver, String message);
}

class EmailSender implements MessageSender {
    @Override
    public boolean send(String receiver, String message) {
        if (!receiver.contains("@")) {
            return false;
        }
        System.out.println("EMAIL " + receiver + ": " + message);
        return true;
    }
}

class SmsSender implements MessageSender {
    @Override
    public boolean send(String receiver, String message) {
        System.out.println("SMS " + receiver + ": " + message);
        return true;
    }
}

class ConsoleSender implements MessageSender {
    @Override
    public boolean send(String receiver, String message) {
        System.out.println("CONSOLE " + receiver + ": " + message);
        return true;
    }
}

public class MessageSenderSystem {
    static boolean notify(MessageSender sender, String receiver, String message) {
        if (receiver == null || receiver.isBlank()
                || message == null || message.isBlank()) {
            System.out.println("接收者或訊息為空，無法送出");
            return false;
        }
        return sender.send(receiver, message);
    }

    public static void main(String[] args) {
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender console = new ConsoleSender();

        System.out.println("email=" + notify(email, "amy@example.com", "Hi"));
        System.out.println("sms=" + notify(sms, "0912345678", "Hi"));
        System.out.println("console=" + notify(console, "B113", "Hi"));
        System.out.println("blank=" + notify(email, "", "Hi"));
        System.out.println("nullMsg=" + notify(sms, "0912345678", null));
    }
}
