public class Chips {

    private int amount;
    private TypeChips type; //Tipo Ficha

    //Constructor
    
    public Chips(int amount, TypeChips type) {
        this.amount = amount;
        this.type = type;
    }
    
    //gERt ans setters

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TypeChips getType() {
        return type;
    }

    public void setType(TypeChips type) {
        this.type = type;
    }

    public double getTotalValue() {
        return amount * unitValueOf(type);
    }

    public double getUnitValue() {
        return unitValueOf(type);
    }

    //Metodos

    //Switch Case para asignar valor
    //Debe ser estatico, ya que ncs calcular el valor de 1 ficha sin tener objeto
    public static double unitValueOf(TypeChips t) { 
        switch (t) {
            case Blanco: return 1;
            // case Rosa: return 2.5; Suprimido para evitar complicaciones NaranjaMoradoBlancoAzulRojoVerdeNegro
            case Rojo: return 5;
            case Azul: return 10;
            case Verde: return 25;
            case Negro: return 100;
            case Morado: return 500;
            case Naranja: return 1000;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return amount +
                ", fichas de: " + type +
                "y cada 1 vale: " + getUnitValue() + 
                ", total: " + getTotalValue() + ")";
    }
}
