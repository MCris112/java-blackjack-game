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

    //Switch Case para asignar valor
    public double getValueFromType(MoneyType type) {
        switch (type) {
            case Blanco: return 1;
            case Rosa: return 2.5;
            case Rojo: return 5;
            case Azul: return 10;
            case Verde: return 25;
            case Negro: return 100;
            case Morado: return 500;
            case Naranja: return 1000;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return "Money{" +
                "balance=" + balance +
                '}';
    }
}
