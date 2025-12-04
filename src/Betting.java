import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Utilities.Table;

public class Betting {

    //Atributos
    private List<Chips> betChips;     
    private Wallet wallet;
    private Scanner sc;

    //Constructor
    public Betting(List<Chips> betChips) {
        this.betChips = new ArrayList<>(); //Incializado
        this.wallet = new Wallet();
        this.sc = new Scanner(System.in);
    }

    //Get and Setters

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public List<Chips> getBetChips() {
        return betChips;
    }

    public void setBetChips(List<Chips> betChips) {
        this.betChips = betChips;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    //Metodos//

    //ActionBetMenu
    public void actionBetMenu() {
        Scanner sc = new Scanner(System.in);
        String option;
        int totalBet = 0;
        boolean end = false;

        do {
            betMenu();

            option = sc.nextLine().trim();

            switch (option) {
                case "Retirarse":
                    System.out.println("Te has retirado.");
                    return;

                case "Quitar":
                    
                    break;

                case "Ya":
                    end = true;
                    break;

                default:
                if (valido(option)) {   
                    totalBet = wallet.plusChip(option, totalBet);
                } else {
                    System.out.println("Opción inválida.");
                }
            }

        } while (!end);
        System.out.println("Es tu apuesta final: " + totalBet);
    }
    
    //betMenu
    private void betMenu() {
        Table.instance()
            .addRow("Escribe el color de la ficha, 'quitar' para retirar, 'ya' para terminar, o 'retirarse':")
            .addRow("------------------------------------")
            .addRow("Blanco: 1")
            .addRow("Rojo: 5")
            .addRow("Azul: 10")
            .addRow("Verde: 25")
            .addRow("Negro: 100")
            .addRow("Morado: 500")
            .addRow("Naranja: 1000")
            .addRow("------------------------------------")
            .print();
    }

    //Boolean para Valido
    private boolean valido(String option) {
        option = option.trim().toLowerCase();

        return 
            option.equals("blanco")         ||
            option.equals("rojo")           ||
            option.equals("azul")           ||
            option.equals("verde")          ||
            option.equals("negro")          ||
            option.equals("morado")         ||
            option.equals("naranja")        ||       
            option.equals("anaranjado");      
    }
}
