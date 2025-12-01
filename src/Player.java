import java.util.ArrayList;

public class Player {
    private String name;
    private int money;

    private boolean isBot;

    private ArrayList<Carta> cartas = new ArrayList<>();

    //Constructores

    public Player(String name, int money, boolean isBot) {
        this.name = name;
        this.money = money;
        this.isBot = isBot;
    }

    //Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }
}