package Wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Utilities.ChipTransp;
import Utilities.Table;

public class Betting {

    //Atributos
    private ArrayList<Chips> betChips; 
    //private Wallet wallet;
    private Scanner sc;
    private ChipTransp chipTransp;


    //Constructor
    public Betting() {
        this.betChips = new ArrayList<>(); //Incializado
        this.chipTransp = new ChipTransp(betChips);
        //this.wallet = new Wallet();
        this.sc = new Scanner(System.in);
    }

    //Get and Setters

    public List<Chips> getBetChips() {
        return betChips;
    }

    public void setBetChips(ArrayList<Chips> betChips) {
        this.betChips = betChips;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    //Metodos

    //-----------------------------------
    // FUNCIONES DE MENU DE APUESTAS
    //-----------------------------------


    //ActionBetMenu
    /* Acciona el menu de apuestas y derivados */
    public void actionBetMenu() {
        String option;
        int totalBet = 0;
        boolean end = false;

        do {

            Table.instance()
                    .addRow("Escribe el color de la ficha, 'quitar' para retirar, 'ok' para mantener")
                    .addRow("Color", "Precio")
                    .addRow("Blanco: 1")
                    .addRow("Rojo: 5")
                    .addRow("Azul: 10")
                    .addRow("Verde: 25")
                    .addRow("Negro: 100")
                    .addRow("Morado: 500")
                    .addRow("Naranja: 1000")
                    .print();

            System.out.println("Seleccione una opcion: ");
            option = sc.nextLine().trim();

            switch (option.toLowerCase()) {
//                case "retirarse":
//                    System.out.println("Te has retirado.");
//                    return;

                case "quitar":
                    System.out.print("Color a retirar: ");
                    String colorRemove = sc.nextLine().trim();
                    /* Es resaltable la conversion a int para el ingreso */
                    System.out.print("Cantidad a retirar: ");
                    int cantRemove = Integer.parseInt(sc.nextLine().trim());

                    /* Conversion reutilizada */
                    TypeChips tipoRemove = Chips.parseType(colorRemove);

                    /* Validacion Reciclada */
                    if (tipoRemove == null) {
                        System.out.println("Has incertado una ficha no vàlida");
                        continue; 
                    }

                    if (cantRemove <= 0) {
                        System.out.println("La cantidad debe ser mayor que cero.");
                        continue;
                    }


                    for (int i = 0; i < this.betChips.size(); i++) {
                        if ( this.betChips.get(i).getType() == tipoRemove ){
                            if ( this.betChips.get(i).remove( cantRemove ) )
                                this.betChips.remove(i);
                        }
                    }

                    System.out.println("Tienes " + this.calcTotalBet());
                    this.eyeBet();

                    break;

                case "ok":
                    end = true;
                    break;

                default:
                    /* Conversion reutilizada */
                    TypeChips tipo = wallet.parseType(option);
                    /* Validacion Reciclada */
                    if (!Wallet.realColor(tipo)) { //Recordar Static
                        continue;
                    }

                    //Zona_Transferencia
                    if (wallet.minusChip(option, 1)) {
                        chipTransp.plusChip(tipo, 1);

                        /* Impresion_Cromprobacion */
                        System.out.println("Has puesto una ficha de " + option + " en la mesa. Apuesta total: " + calcTotalBet());
                    }
            }

        } while (!end);
        System.out.println("Es tu apuesta final: " + totalBet);
    }

    /* Imprime Fichas Apostadas */
    public void eyeBet() {
        System.out.println("--- Fichas en la Apuesta ---");
        System.out.println();
        for (Chips c : betChips) {
            System.out.println(c);
        }
    }

    /* Calcular valor_TotalBet */
    public double calcTotalBet(){
        double total = 0;
            for (Chips c : betChips) {
                total += c.getTotalValue();
            }
        return total;
    }

}
