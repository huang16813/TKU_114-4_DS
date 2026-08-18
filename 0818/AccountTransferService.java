class Account {
    private String id;
    private int balance;

    Account(String id, int balance) {
        this.id = (id == null || id.isBlank()) ? "UNKNOWN" : id;
        this.balance = Math.max(0, balance);
    }

    boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return id + " balance=" + balance;
    }
}

class TransferService {
    boolean transfer(Account source, Account target, int amount) {
        if (source == null || target == null || source == target) {
            return false;
        }
        if (amount <= 0 || amount > source.getBalance()) {
            return false;
        }
        source.withdraw(amount);
        target.deposit(amount);
        return true;
    }
}

public class AccountTransferService {
    public static void main(String[] args) {
        TransferService service = new TransferService();
        Account a = new Account("A", 1000);
        Account b = new Account("B", 200);

        System.out.println("成功轉帳 300：" + service.transfer(a, b, 300));
        System.out.println(a);
        System.out.println(b);

        System.out.println("轉帳 5000（餘額不足）：" + service.transfer(a, b, 5000));
        System.out.println(a);
        System.out.println(b);

        System.out.println("同帳戶轉帳：" + service.transfer(a, a, 100));
        System.out.println(a);

        System.out.println("目標為 null：" + service.transfer(a, null, 100));
        System.out.println(a);
    }
}
