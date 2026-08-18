class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int transactionCount;

    DigitalWallet(String walletId, String owner) {
        this.walletId = (walletId == null || walletId.isBlank()) ? "UNKNOWN" : walletId;
        this.owner = (owner == null || owner.isBlank()) ? "Unknown" : owner;
        this.balance = 0;
        this.transactionCount = 0;
    }

    boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        transactionCount++;
        return true;
    }

    boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        transactionCount++;
        return true;
    }

    boolean refund(int amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        transactionCount++;
        return true;
    }

    int getBalance() {
        return balance;
    }

    int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return walletId + " owner=" + owner + " balance=" + balance
                + " transactions=" + transactionCount;
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("W101", "Ben");

        System.out.println("正常存入 1000：" + wallet.deposit(1000));
        System.out.println("正常提款 300：" + wallet.withdraw(300));
        System.out.println("提款 5000（餘額不足）：" + wallet.withdraw(5000));
        System.out.println("存入 -200（負數金額）：" + wallet.deposit(-200));
        System.out.println("退款 150：" + wallet.refund(150));

        System.out.println(wallet);
    }
}
