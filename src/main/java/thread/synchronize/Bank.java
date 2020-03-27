package thread.synchronize;

/**
 * @author 李昭
 */
public class Bank {
    public static void main(String[] args) {
        Account account = new Account("邓思萱", 100);
        Draw self = new Draw(account, 1, "alone");
        Draw d = new Draw(account, 1, "deng");
        Draw d2 = new Draw(account, 1, "deng2");
        self.start();
        d.start();
        d2.start();
    }
}

class Account {
    private String name;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

class Draw extends Thread {

    private Account account;
    private double drawing;
    private double total;
    private String name;

    public Draw(Account account, double drawing, String name) {
        super(name);            //直接设定线程名
        this.account = account;
        this.drawing = drawing;
    }

    @Override
    public void run() {
        double balance = account.getBalance();
        if (balance <= 0) {
            return;
        }
        synchronized (account) {
            if (balance < drawing) {
                System.out.println("no sufficients funds!!");
                return;
            }

            balance -= drawing;
            account.setBalance(balance);
        }
        total += drawing;
        System.out.println(Thread.currentThread().getName() + " Drawing:" + total);
        System.out.println(Thread.currentThread().getName() + " balance:" + account.getBalance());
    }
}