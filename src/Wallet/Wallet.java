package Wallet;

import Utilities.ChipTransp;
import java.util.ArrayList;
import java.util.Arrays;

public class Wallet {
    
    //Atributos
    private ArrayList<Chips> chips;
    private ChipTransp chipTransp;

    //ConstructorS
    public Wallet(){
        this.chips = new ArrayList<>();
        this.chipTransp = new ChipTransp(this.chips);
    }
    
    // public Wallet(ArrayList<Chips> chips) 

    //Get and Setters

    public ArrayList<Chips> getChips() {
        return chips;
    }

    public void setChips(ArrayList<Chips> chips) {
        this.chips = chips;
    }

    //Metodos

    //Inicializar wallet con chips
    public void startWallet(double money) {
        /* Limpiar previo a iniciar */
        chips.clear();
        TypeChips[] types = TypeChips.values(); 
        Arrays.sort(types, (a, b) -> Double.compare(Chips.valueOfType(b), Chips.valueOfType(a)));

        /* Repartición de fichas */
        for (TypeChips type : types) {
            double valueCoin = Chips.valueOfType(type);
            int quantity = (int)(money / valueCoin);

            if (quantity > 0) {
                /* Entrada x tipoFicha, inicilizar de no haber */
                chips.add(new Chips(quantity, type));
                money -= quantity * valueCoin;
            }
        }

        //Sobra...
        if (money > 0) {
            System.out.println("Sobra: " + money);
        }
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
    public void plusChip(String color, int amount) {
        TypeChips tipo = parseType(color);
        if (!realColor(tipo)){return;}
            chipTransp.plusChip(tipo, amount);
        }

    //Aqui, se agrega la imposibilidad de -0
    public boolean minusChip(String color, int amount) {
        TypeChips tipo = parseType(color);
        if (!realColor(tipo)){return false;}

        if (amount <= 0) {
        System.out.println("La cantidad debe ser mayor que cero.");
        return false;
        }

        int disponibles = chipTransp.getPlayMount(tipo);
        if (disponibles < amount) {
            System.out.println("No tienes suficientes fichas de " + tipo);
            return false; 
        }
            
        chipTransp.minusChip(tipo, amount);
        return true;
    }


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