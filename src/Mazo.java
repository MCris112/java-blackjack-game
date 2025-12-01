import Utilities.Table;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class Mazo {

    private ArrayList<Carta> cartas = new ArrayList<>();

    /**
     * Generates the 13 cards for each rank of cards
     */
    public void generate()
    {
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
