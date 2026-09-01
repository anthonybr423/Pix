import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pix {
    public void transfer(Account sourceAccount, Account targetAccount, int amount) {
        if (amount > 0 && amount <= sourceAccount.getBalance()) {
            sourceAccount.withdraw(amount);
            targetAccount.deposit(amount);
        }
    }
    
    void print(String s) {
        System.out.println(s);
    }

    void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Account[] accounts = new Account[20];
        for (int i = 0; i < 20; i++) {
            accounts[i] = new Account((int) (Math.random() * 1000));
        }
        Pix pix = new Pix();
        for (int i = 0; i < 1000; i++) {
            Account account1 = accounts[(int) (Math.random() * 20)];
            Account account2 = accounts[(int) (Math.random() * 20)];
            int amount = (int) (Math.random() * 800);
            long startTime = System.nanoTime();
            executor.submit(() -> pix.transfer(account1, account2, amount));
            long endTime = System.nanoTime();
            print("Transfer" + " from account " + account1 + " to account " + account2 + " amount: " + amount + " took " + (endTime - startTime) + " ns");
        }
        executor.shutdown();
    }
}
