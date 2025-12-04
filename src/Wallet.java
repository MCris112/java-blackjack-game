import java.util.ArrayList;
import java.util.Arrays;

public class Wallet {
    
    private ArrayList<Chips> chips; 
    public Wallet(){ chips = new ArrayList<>();}
    
    //Constructor

    public Wallet(ArrayList<Chips> chips) {
        this.chips = chips;
    }

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
        TypeChips[] types = TypeChips.values();
        Arrays.sort(types, (a, b)   ->  Double.compare(Chips.unitValueOf(b), 
                                        Chips.unitValueOf(a)));

        //Reparticion_Fichas
        for (TypeChips type : types) {
            //Variables Locales quantity, value
            double valueCoin = Chips.unitValueOf(type); 
            //Cuantas chips caben
            int quantity = (int)(money / valueCoin);
            //Un objeto por cada chip
            for (int i = 0; i < quantity; i++) {
                chips.add(new Chips(1, type));
            }
            money -= quantity * valueCoin;
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

    //plusChip
    public int plusChip(String option, int totalBet) {
        TypeChips tipo = parseType(option);

        //Llamada a funcion auxiliar de Chips
        if (!realColor(tipo)) {
            return totalBet;
        }

        //Se agrega y acumula una ficha
        chips.add(new Chips(1, tipo));
        totalBet += Chips.unitValueOf(tipo);

        //Impresiones
        eyeWallet();
        System.out.println("Total acumulado: " + totalBet);
        
        return totalBet;
    }

    //MinuChip
    public void minusChip(String color, int cantidad) {
    
        //Conversion
        TypeChips tipo = parseType(color);

        //Llamada a validacion
        if (!realColor(tipo)) {
        return;
        }
    
        //LLamda al contador
        int disponibles = contarFichas(tipo);

        if (disponibles < cantidad) {
            System.out.println("No tienes suficientes fichas de " + tipo + ". Tienes: " + disponibles);
        return;
        }

        //Quitanto fichas
        for (Chips c : chips) {
            if (c.getType() == tipo) {
                int quitar = Math.min(c.getAmount(), cantidad);
                c.setAmount(c.getAmount() - quitar);
                cantidad -= quitar;
                if (cantidad == 0) break;
            }
        }

        //Imprimir
        System.out.println("Se han quitado fichas de " + tipo);
        eyeWallet();
    }

    //Funcion Axiliar, convierte string a enum
    private TypeChips parseType(String option) {
        try {
            return TypeChips.valueOf(option.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    //Funcion Auxiliar, si es un colo real
    public static boolean realColor(TypeChips tipo) {
        if (tipo == null) {
            System.out.println("Ese color no existe.");
            return false;
        }
        return true;
    }

    //ContadorFichas-Existentes
    private int contarFichas(TypeChips tipo) {
        int contador = 0;
        for (Chips c : chips) {
            if (c.getType() == tipo) {
            contador++;
            }
        }
        return contador;
    }

    
    //ImprimirColeccion
    public void eyeWallet() {
        for (Chips c : chips) {
            System.out.println(c);
        }
    }

    @Override
    public String toString() {
        return "Wallet [chips=" + chips + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }

}