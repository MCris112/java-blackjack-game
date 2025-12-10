package Entity;

import Contenido.Carta;
import Contenido.Mazo;
import Wallet.Betting;
import Wallet.WalletModel;

import java.util.ArrayList;

public class Player {
    private String name;
    private double money = 10000.00;
    private WalletModel wallet = new WalletModel();
    private Betting betting =  new Betting();

    //Por defecto si se crea vacio será un bot
    private boolean isBot = true;

    private ArrayList<Carta> cartas = new ArrayList<>();

    //Constructores

    public Player() {//Modificado para que Bot tenga un numero ramson
        this.wallet.startWallet(this ); //Llena el wallet con chips x money
    }

    public Player(String name, double money, boolean isBot, WalletModel wallet) {
        this.name = name;
        this.money = money;
        this.isBot = isBot;
        this.wallet = wallet;

        this.wallet.startWallet( this );
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

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setIsBot(boolean bot) {
        isBot = bot;
    }

    
    public WalletModel getWallet() {
        return wallet;
    }

    public void setWallet(WalletModel wallet) {
        this.wallet = wallet;
    }

    public void giveCard( Carta card )
    {
        System.out.printf("%s recibiste una carta: \n", this.name);
        System.out.println(card);
        this.cartas.add( card );
    }

    public void resetBetting()
    {
        this.betting = new Betting();
    }

    public Betting getBetting() {
        return betting;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public int getCartasPuntosTotales()
    {
        int puntos = 0;

        for (Carta carta: this.cartas)
        {
            puntos +=carta.getValue();
        }

        return puntos;
    }



    public void generateBetBot() {
        //generar numero aletario
        int money = (int) (Math.random() * 100) + 1;
        this.money = money;
        this.wallet.startWallet( this );
    }

    //Metodos

    /**
     * Verificar si el jugador tiene Blackjack
     * @return en caso de que tenga verdadero
     */
    public boolean hasBlackjack()
    {
        return Mazo.checkBlackjack( this.cartas );
    }

    public void prestarMoney() {
        if (this.isBot()) {
            System.out.println("El crupier no puede prestar dinero a un bot.");
        }
        else {
            System.out.println("El crupier humano ha prestado dinero.");
        }
    }

    @Override
    public String toString() {
        return "Entity.Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", isBot=" + isBot +
                ", cartas=" + cartas +
                '}';
    }

}