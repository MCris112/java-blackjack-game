import java.util.ArrayList;

public class Crupier {

    public String name;

    //prueba del merge
    public Crupier(String name) {
        this.name = name;
    }

    private ArrayList<Carta> cartas = new ArrayList<>();


    public void giveCard( Carta card )
    {
        this.cartas.add( card );
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * Verificar si la primera carta es un AS
     * si esta vacio o hay mas de una carta devolvera vacio
     * @return Verdadero: la primera es un AS
     */
    public boolean isFirstAs()
    {
        if (this.cartas.size() != 1)
            return false;

        if ( this.cartas.getFirst().isRank( CardRank.AS ) )
            return true;

        return false;
    }
}
