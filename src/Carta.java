public class Carta {

    private Figure symbol;

    private int value;

    private CardRank rank;

    public Carta(Figure symbol, int value, CardRank rank) {
        this.symbol = symbol;
        this.value = value;
        this.rank = rank;

        if (rank == CardRank.KING || rank == CardRank.QUEEN || rank == CardRank.JACK)
        {
            this.value = 10;
        } else if (rank == CardRank.AS) {
        }
    }

    public Figure getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public CardRank getRank() {
        return rank;
    }

    public static CardRank getRank(int rank) {
        if ( rank == 1 )
        {
            return CardRank.AS;
        }

        if ( rank == 2 )
            return CardRank.TWO;

        if ( rank == 3 )
            return CardRank.THREE;

        if ( rank == 4 )
            return CardRank.FOUR;

        if ( rank == 5 )
            return CardRank.FIVE;

        if ( rank == 6 )
            return CardRank.SIX;

        if ( rank == 7 )
            return CardRank.SEVEN;

        if ( rank == 8 )
            return CardRank.EIGHT;

        if ( rank == 9 )
            return CardRank.NINE;

        if ( rank == 10 )
            return CardRank.TEN;

        if ( rank == 11 )
            return CardRank.JACK;

        return CardRank.AS;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "symbol=" + symbol +
                ", value=" + value +
                ", rank=" + rank +
                '}';
    }
}
