package Wallet;

import Entity.Player;
import Utilities.ChipTransp;
import java.util.ArrayList;
import java.util.Arrays;

public class WalletModel {
    
    //Atributos
    private ArrayList<Chips> chips = new ArrayList<>();
    private ChipTransp chipTransp;

    //ConstructorS
    public WalletModel(){
        this.chipTransp = new ChipTransp(this.chips);
    }


    public ArrayList<Chips> getChips() {
        return chips;
    }

    public void setChips(ArrayList<Chips> chips) {
        this.chips = chips;
    }

    /**
     * Inicializar wallet con chips
     * @param player pasar objeto jugador para pdoer quitar dinero y agregar fichas
     */
    public void startWallet(Player player) {
        /* Limpiar previo a iniciar */
        chips.clear();

        double money = player.getMoney();

        TypeChips[] types = TypeChips.values(); 
        Arrays.sort(types, (a, b) -> Double.compare(Chips.valueOfType(b), Chips.valueOfType(a)));

        /* Repartición de fichas */
        for (TypeChips type : types) {
            double valueCoin = Chips.valueOfType(type);
            int quantity = (int)(money / valueCoin);

            if (quantity > 0) {
                /* Entrada x tipoFicha, inicilizar de no haber */
                chips.add(new Chips(type, quantity));
                money -= quantity * valueCoin;
            }
        }

        //Sobra...
        if (money > 0) {
            System.out.println("Sobra: " + money);
        }

        player.setMoney(money);
    }

    //Restar lo apostado
    public void minusBet(int bet) { 
        for (Chips c : chips) {
            if (c.getAmount() >= bet) {
                c.setAmount(c.getAmount() - bet);
                return;
            }
        }
    }

    //Aplicacion de Semilla y la Utilidad que la brinda

    //Dado que la utilidad les ayuda, solo validadcalidad el color
//    public void plusChip(String color, int amount) {
//        TypeChips tipo = parseType(color);
//        if (!realColor(tipo)){return;}
//            chipTransp.plusChip(tipo, amount);
//        }

    //Aqui, se agrega la imposibilidad de -0
//    public boolean minusChip(String color, int amount) {
//        TypeChips tipo = parseType(color);
//        if (!realColor(tipo)){return false;}
//
//        if (amount <= 0) {
//        System.out.println("La cantidad debe ser mayor que cero.");
//        return false;
//        }
//
//        int disponibles = chipTransp.getPlayMount(tipo);
//        if (disponibles < amount) {
//            System.out.println("No tienes suficientes fichas de " + tipo);
//            return false;
//        }
//
//        chipTransp.minusChip(tipo, amount);
//        return true;
//    }


    // /* ContadorFichas-Existentes */
    // private int contarFichas(TypeChips tipo) {
    //     int contador = 0;
    //     for (Chips c : chips) {
    //         if (c.getType() == tipo) {
    //         contador++;
    //         }
    //     }
    //     return contador;
    // }

    //ImprimirColeccion
    public void eyeWallet() {
        System.out.println();
        System.out.println("--- Monedero Actual ---");
        System.out.println();
        for (Chips c : chips) {
            System.out.println(c);
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Wallet.Wallet [chips=" + chips + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

}