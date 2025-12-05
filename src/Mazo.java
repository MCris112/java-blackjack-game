import Utilities.Table;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {

    private ArrayList<Carta> cartas = new ArrayList<>();

    /**
     * Generates the 13 cards for each rank of cards
     */
    public void generate()
    {
        //Al generar tenemos que limpiar las cartas que ya existen para generar desde 0
        this.cartas.clear();

        generateBySymbol( this.cartas, Crupier.Figure.CORAZONES );
        generateBySymbol( this.cartas, Crupier.Figure.PICAS );
        generateBySymbol( this.cartas, Crupier.Figure.TREBOLES );
        generateBySymbol( this.cartas, Crupier.Figure.DIAMANTES );
    }

    public static void generateBySymbol( ArrayList<Carta> cartas, Crupier.Figure symbol )
    {
        for (int i = 1; i < 14; i++) {
            cartas.add( new Carta(symbol, i, Carta.getRank(i)) );
        }
    }

    /**
     * Verificar la disponibilidad de cartas para los jugadores
     * En caso de que no haya suficientes cartas para repartir, vuelve a "barajar las cartas en la mesa" (genera un nuevo mazo)
     * @param totalToGive cartas que se necesita en el juego para poder brindarles una carta
     */
    public void checkCardAvailabilityForGive( int totalToGive )
    {
        if ( this.cartas.size() < totalToGive )
        {
            this.generate();
        }
    }

    public boolean isEmpty()
    {
        return this.cartas.isEmpty();
    }

    /**
     * Obtener una carta aleatoria del mazo
     * y luego la elimina automaticamente del mazo
     * @return Devuelve null si el mazo esta vacio, por lo que hay que comprobar si hay carta o no
     */
    private Carta getRandCard()
    {
        // Comprobamos si esta vacio, porque tira error
        if ( this.cartas.isEmpty() )
            return null;

        //barajamos
        Collections.shuffle( this.cartas );

        //obtenemos la prima carta barajada
        Carta card = this.cartas.getFirst();

        this.cartas.remove( card );
        return card;
    }


    public void giveCardToPlayers( ArrayList<Player> payers )
    {
        // verificar disponibilidad
        this.checkCardAvailabilityForGive( payers.size() );

        for ( Player player : payers )
        {
            Carta card = getRandCard();
            player.giveCard( card );
        }
    }

    public void giveCardToCrupier( Crupier crupier )
    {
        crupier.giveCard( this.getRandCard() );
    }

    public void show()
    {
        Table table = Table.instance()
                .addRow("Cartas");

        for (Carta carta : cartas) {
            table.addRow(carta.toString());
        }

        table.print();
    }

}
