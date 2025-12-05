package Utilities;

import Wallet.Chips;
import Wallet.TypeChips;
import java.util.ArrayList;

public class ChipTransp {

    //Semilla
    private final ArrayList<Chips> chips;

    //Constructor
    public ChipTransp(ArrayList<Chips> chips) {
        this.chips = chips;
    }

    /* Funcion para gestionar la lleaga de fichas */
    public void plusChip(TypeChips tp, int playMount) {
        //Busqueda de fichas=tipo
        boolean found = false;
        for (Chips c : chips) {
            if (c.getType() == tp) {
                c.setAmount(c.getAmount() + playMount);
                found = true;
                break;
            }
        }
        if (!found) {
//            chips.add(new Chips(playMount, tp));
        }
    }

    /* Funcion para gestionar la ida */
    public void minusChip(TypeChips tp, int playMount) {
        for (Chips c : chips) {
            if (c.getType() == tp) {
                int quitar = Math.min(c.getAmount(), playMount);
                c.setAmount(c.getAmount() - quitar);
                playMount -= quitar;
                if (playMount == 0) break;
            }
        }
        //Limpieza
        /* RemoveIF, elimina elementos que conicidan con el predicado */
        chips.removeIf(c -> c.getType() == tp && c.getAmount() == 0); 
    }

    //TotalAculativo
    public int getPlayMount(TypeChips tp) {
        int total = 0;
        for (Chips c : chips) {
            if (c.getType() == tp) {
                total += c.getAmount();
            }
        }
        return total;
    }
}