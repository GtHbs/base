package exception;

/**
 * @author 李昭
 */
public class TestException extends Exception {
    private double amount;
    public TestException(double amount)
    {
        this.amount = amount;
    }
    public double getAmount()
    {
        return amount;
    }
}

class checkAmount
{
    private double balance;
    private int number;

    public checkAmount(int number) {
        this.number = number;
    }

    public void deposit(double amount)
    {
        balance += amount;
    }

    public void withDraw(double amount) throws TestException {
        if (amount <= balance)
            balance -= amount;
        else
        {
            double needs = amount - balance;
            throw new TestException(needs);
        }
    }

    public double getBalance()
    {
        return balance;
    }

    public int getNumber()
    {
        return number;
    }
}

class BankDemo
{
    public static void main(String[] args) {
        checkAmount amount = new checkAmount(101);
        System.out.println("Depositing $500....");
        amount.deposit(500);
        try {
            System.out.println("WithDraw $100");
            amount.withDraw(100);
            System.out.println("Depositing $600");
            amount.withDraw(600);
        } catch (TestException e) {
            System.out.println("Sorry,but you are short $"+e.getAmount());
            e.printStackTrace();
        }
    }
}



