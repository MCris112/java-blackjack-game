package Contenido;

import Entity.Crupier;
import Entity.Player;
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

        generateBySymbol( this.cartas, Figure.CORAZONES );
        generateBySymbol( this.cartas, Figure.PICAS );
        generateBySymbol( this.cartas, Figure.TREBOLES );
        generateBySymbol( this.cartas, Figure.DIAMANTES );
    }

    public static void generateBySymbol( ArrayList<Carta> cartas, Figure symbol )
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

        // TODO Mostrar mensaje de mazo nuevo
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

    public void giveCardTo( Player player )
    {
        Carta card = getRandCard();

        if (card != null) {

            player.giveCard( card );
        }

    }

    public void giveCardTo( Crupier crupier )
    {
        Carta card = getRandCard();

        if (card != null) {
            System.out.printf("CRUPIER: %s recibiste una carta: %s", crupier.getName(), card.toString());
            crupier.giveCard( card );
        }

    }

    public static boolean checkBlackjack( ArrayList<Carta> cartas )
    {
        // Solo se necesita 2 cartas para Blackjack
        if ( cartas.size() > 2)
        {
            return false;
        }

        Carta aux =  null;

        if ( cartas.get(0).getRank() == CardRank.AS )
        {
            aux = cartas.get(1);
        }else if ( cartas.get(1).getRank() == CardRank.AS )
        {
            aux = cartas.getFirst();
        }else {
            // / La primera o la segunda carta tiene que ser una as
            return false;
        }

        switch ( aux.getRank() )
        {
            case CardRank.JACK, CardRank.QUEEN, CardRank.KING -> {
                return true;
            }

            default -> {
                // Si no comprueba que es alguna de estas opciones, tampoco es blackjack
                return false;
            }
        }
    }

}
