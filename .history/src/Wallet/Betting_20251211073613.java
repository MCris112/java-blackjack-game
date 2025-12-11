package Wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Utilities.MC;
import Utilities.Table;

public class Betting {

    //Atributos
    private ArrayList<Chips> betChips;
    //private WalletModel wallet;

    static Scanner sc = new Scanner(System.in);

    //Constructor
    public Betting() {
        this.betChips = new ArrayList<>(); //Incializado
    }

    //Get and Setters

    public List<Chips> getBetChips() {
        return betChips;
    }

    public void setBetChips(ArrayList<Chips> betChips) {
        this.betChips = betChips;
    }

//    public WalletModel getWallet() {
//        return wallet;
//    }
//
//    public void setWallet(WalletModel wallet) {
//        this.wallet = wallet;
//    }

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
                    .addRow("Blanco", "1")
                    .addRow("Rojo", "5")
                    .addRow("Azul", "10")
                    .addRow("Verde", "25")
                    .addRow("Negro",  "100")
                    .addRow("Morado", "500")
                    .addRow("Naranja", "1000")
                    .print();

            System.out.println("Seleccione una opcion: ");
            option = sc.nextLine().trim();

            switch (option.toLowerCase()) {
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

                    // Obtenemos el chip que queremos quitar
                    Chips chips = this.getChipPorTipo(tipoRemove);

                    // Quitamos la cantidad de chips por ese tipo para remover
                    chips.remove(cantRemove);

                    // Si hay menos chips o igual a 0, se supone que ya no hay chips de este tipo
                    // Entonces quitamos de la lista de los chips apostados
                    if ( chips.getAmount() <= 0)
                    {
                        this.betChips.remove( chips );
                    }

                    MC.printLine();
                    System.out.println("Total dinero apostado: " + this.calcTotalBet());
                    MC.printLine();
                    this.eyeBet();

                    break;

                case "ok":
                    if ( this.calcTotalBet() == 0 )
                    {
                        MC.title.outlineY("Necesitas dinero para apostar");
                    }else{
                        end = true;
                    }
                    break;

                default:
                    // Verifica que el color sea válido
                    if( Chips.parseType(option) == null )
                    {
                        MC.title.outlineY("El color seleccionado no es valido");
                    }else{
                        this.agregarChipPorTipo( Chips.parseType(option) );
                    }

                    this.eyeBet();

                    break;
            }

        } while (!end);
        System.out.println("Es tu apuesta final: " + totalBet);
    }

    public void agregarChipPorTipo( TypeChips tipo )
    {
        // Obetenemos la lista de chips por el tipo
        Chips chips = this.getChipPorTipo(tipo);

        //Añadimos cuantos chips se quiere agregar al chip existente en la lista
        chips.add(1);

        MC.title.outlineY("Añadiste el chip ");
    }

    public Chips getChipPorTipo( TypeChips tipo )
    {
        for (Chips chip : this.betChips) {
            if (chip.getType().equals(tipo)) {
                return chip;
            }
        }

        // Si no se encuentra un chip existente, entonces añadimos uno a la lista
        Chips chips = new Chips( tipo, 0 );
        this.betChips.add(chips);

        return chips;
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
