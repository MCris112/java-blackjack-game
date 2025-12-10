package Contenido;

import Entity.Crupier;


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

    public boolean isRank(CardRank cardRank )
    {
        return this.rank == cardRank;
    }

    @Override
    public String toString() {
        String simbolo = switch ( this.symbol ) {
            case Figure.PICAS -> "♠";
            case Figure.CORAZONES -> "♥";
            case Figure.DIAMANTES -> "♦";
            case Figure.TREBOLES -> "♣";
        };

        return """
           ┌─────────┐
           │ %-2d      │
           │         │
           │    %s    │
           │         │
           │      %-2d │
           └─────────┘
           """.formatted( parseRankIntoValue(this.rank) , simbolo, parseRankIntoValue(this.rank));
    }


    public static int parseRankIntoValue( CardRank cardRank )
    {
        switch( cardRank )
        {
            case CardRank.AS -> {
                return 1;
            }

            case CardRank.TWO ->  {
                return 2;
            }

            case CardRank.THREE ->  {
                return 3;
            }

            case CardRank.FOUR ->  {
                return 4;
            }

            case CardRank.FIVE ->  {
                return 5;
            }

            case CardRank.SIX ->  {
                return 6;
            }

            case CardRank.SEVEN ->  {
                return 7;
            }

            case CardRank.EIGHT ->  {
                return 8;
            }

            case CardRank.NINE ->  {
                return 9;
            }

            case CardRank.TEN ->  {
                return 10;
            }

            case CardRank.JACK ->  {
                return 11;
            }

            case CardRank.QUEEN -> {
                return 12;
            }

            case CardRank.KING -> {
                return 13;
            }

            default -> {
                return 0;
            }
        }
    }
}
