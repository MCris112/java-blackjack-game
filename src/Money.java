public class Money {

    private double balance;
    private MoneyType type;

    //Constructor
    public Money(double balance, MoneyType type) {
        this.balance = balance;
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public MoneyType getType() {
        return type;
    }

    public void setType(MoneyType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Money{" +
                "balance=" + balance +
                '}';
    }
}
