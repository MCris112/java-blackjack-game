package Wallet;

public class Chips {

    private int amount; //Cantidad de fichas de este tipo
    private TypeChips type; //Tipo Ficha

    //Constructor
    public Chips(TypeChips type, int  amount) {
        this.amount = amount;
        this.type = type;
    }

    //Getter and Setters

    public TypeChips getType() {
        return type;
    }

    public void setType(TypeChips type) {
        this.type = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    //Metodos

    /**
     * Añade cuantos chips quires agregar dentro de esta lista de chips
     * @param cantidad Si tienes 2 y pones cantidad 2, tendrás 4 chips
     */

    public void add(int cantidad)
    {
        this.amount += cantidad;
    }
    public double getTotalValue()
    {
        return this.amount * valueOfType(this.type);
    }
    /**
     * Remover la cantidad de fichas
     * @param cantRemove la cantidad a remover
     * @return si devuelve verdadero es que la lista quedo vacia
     */
    public boolean remove( int cantRemove )
    {
        this.amount -= cantRemove;

        System.out.println("Has quitado " + cantRemove + " fichas de " + this.type);

        //Simpleficable
        if ( amount < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Switch Case para asignar valor, ahora es int, me olvide cambiarlo tras
     * Debe ser estatico, ya que ncs calcular el valor de 1 ficha sin tener objeto
     * @param t el tipo al cual se quiere obtener el valor
     * @return
     */
    public static double valueOfType(TypeChips t) {
        switch (t) { //Rememplazable
            case BLANCO: return 1;
            // case Rosa: return 2.5; Suprimido para evitar complicaciones
            case ROJO: return 5;
            case AZUL: return 10;
            case VERDE: return 25;
            case NEGRO: return 100;
            case MORADO: return 500;
            case NARANJA: return 1000;
            default: return 0;
        }
    }

    /**
     * Funcion Aux, convierte string a enum
     * @param option el texto del color para usar
     * @return el tipo o devuelve null en caso de no encontrar
     */
    public static TypeChips parseType(String option) {
        try {
            return TypeChips.valueOf(option.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Chips{" +
                "type=" + type +
                ", cantidad=" + amount +
                ", valor total="+valueOfType(type)+
                " }";
    }
}
