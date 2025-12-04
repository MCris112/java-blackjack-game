import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String name;
    private double money;
    private Wallet wallet;

    //Por defecto si se crea vacio será un bot
    private boolean isBot = true;

    private ArrayList<Carta> cartas = new ArrayList<>();

    //Constructores

    public Player() {//Modificado para que Bot tenga un numero ramson
        Random random = new Random();
        this.money = 200 + random.nextInt(301); //200 a 300
        this.wallet = new Wallet(); //Crea un nuevo Wallet vacio
        this.wallet.startWallet(this.money); //Llena el wallet con chips x money
    }

    public Player(String name, double money, boolean isBot, Wallet wallet) {
        this.name = name;
        this.money = money;
        this.isBot = isBot;
        this.wallet = wallet;
    }

    //Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setIsBot(boolean bot) {
        isBot = bot;
    }

    public boolean hasEnoughMoney( int bet )
    {
        return this.money >= bet;
    }
    
    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    //Metodos

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", isBot=" + isBot +
                ", cartas=" + cartas +
                '}';
    }

}