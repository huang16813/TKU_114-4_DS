final class WalletTransaction {
    private final int sequence;
    private final String type;
    private final int amount;
    private final int balanceAfter;

    WalletTransaction(int sequence, String type, int amount, int balanceAfter) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    int getSequence() {
        return sequence;
    }

    String getType() {
        return type;
    }

    int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return sequence + " " + type + " " + amount + " balance=" + balanceAfter;
    }
}

class DigitalWallet {
    private final String walletId;
    private final String owner;
    private int balance;
    private final WalletTransaction[] transactions;
    private int transactionCount;

    DigitalWallet(String walletId, String owner, int historyCapacity) {
        this.walletId = (walletId == null || walletId.isBlank()) ? "UNKNOWN" : walletId;
        this.owner = (owner == null || owner.isBlank()) ? "Unknown" : owner;
        this.balance = 0;
        this.transactions = new WalletTransaction[Math.max(1, historyCapacity)];
        this.transactionCount = 0;
    }

    boolean deposit(int amount) {
        if (amount <= 0 || transactionCount >= transactions.length) {
            return false;
        }
        balance += amount;
        record("DEPOSIT", amount);
        return true;
    }

    boolean pay(int amount) {
        if (amount <= 0 || amount > balance
                || transactionCount >= transactions.length) {
            return false;
        }
        balance -= amount;
        record("PAY", amount);
        return true;
    }

    boolean refund(int amount) {
        if (amount <= 0 || transactionCount >= transactions.length) {
            return false;
        }
        balance += amount;
        record("REFUND", amount);
        return true;
    }

    boolean transferTo(DigitalWallet target, int amount) {
        if (target == null || target == this || amount <= 0 || amount > balance) {
            return false;
        }
        if (transactionCount >= transactions.length
                || target.transactionCount >= target.transactions.length) {
            return false;
        }
        balance -= amount;
        record("TRANSFER_OUT", amount);
        target.balance += amount;
        target.record("TRANSFER_IN", amount);
        return true;
    }

    private void record(String type, int amount) {
        transactions[transactionCount] = new WalletTransaction(
                transactionCount + 1, type, amount, balance);
        transactionCount++;
    }

    WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }
        return null;
    }

    int totalByType(String type) {
        int total = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equals(type)) {
                total += transactions[i].getAmount();
            }
        }
        return total;
    }

    void printStatement() {
        System.out.println(walletId + " owner=" + owner + " balance=" + balance);
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactions[i]);
        }
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        DigitalWallet walletA = new DigitalWallet("W001", "Amy", 5);
        DigitalWallet walletB = new DigitalWallet("W002", "Ben", 5);

        walletA.deposit(1000);
        walletA.pay(250);
        walletA.refund(50);
        walletA.transferTo(walletB, 300);

        System.out.println("交易陣列未滿前存入 100：" + walletA.deposit(100));
        System.out.println("交易陣列已滿後再存入 100：" + walletA.deposit(100));

        System.out.println("查詢序號 2 的交易：" + walletA.findTransaction(2));
        System.out.println("查詢不存在的序號 99：" + walletA.findTransaction(99));

        System.out.println("A 的 DEPOSIT 總額：" + walletA.totalByType("DEPOSIT"));
        System.out.println("A 的 TRANSFER_OUT 總額：" + walletA.totalByType("TRANSFER_OUT"));

        walletA.printStatement();
        walletB.printStatement();
    }
}
