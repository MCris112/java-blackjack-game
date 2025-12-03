import Utilities.MC;
import Utilities.Name;
import Utilities.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Crupier crupier;
    private ArrayList<Player> players = new ArrayList<>();

    private Mazo mazo = new Mazo();

    static Scanner sc = new Scanner(System.in);


    public void showMenu()
    {
        Table.instance()
                .addRow("Opciones")
                .addRow("1", "Un solo jugador")
                .addRow("2", "Varios jugadores")
                .addRow("0", "Salir")
                .print();

        switch ( Integer.parseInt(sc.nextLine()) ) {
            case 0:
                MC.title.outline("MUCHAS GRACIAS");
                break;
            case 1:
                registerPlayer();

                this.init();
                break;
            case 2:
                System.out.println("Ingrese el numero de jugadores (max: 7)");
                int option = Integer.parseInt(sc.nextLine());

                if ( option > 7) {
                    System.out.println("Demasiados jugadores");
                    this.showMenu();
                }else{
                    for (int i = 0; i < option; i++) {
                        //Por cada jugador hay que pedir nombres y demas
                        registerPlayer();
                    }

                    this.init();
                }
                break;
            default:
                System.out.println("Opcion incorrecta, intente de nuevo");
                this.showMenu();
                break;
        }
    }



    private void registerPlayer()
    {
        if ( this.crupier == null ) {

            System.out.println("¿Quieres ser el Crupier? (Si, s para confirmar / enter o cualquiera para omitir)");
            String selected =  sc.nextLine().toUpperCase();
            if ( selected.equals("SI") || selected.equals("S") ) {
                System.out.println( "Ingrese el nombre del Crupier");
                this.crupier = new Crupier( sc.nextLine() );

                return;
            }

        }

        Player player = new Player();

        System.out.println( "Ingrese el nombre del jugador "+(this.players.size() + 1)+":" );
        player.setName(sc.nextLine());
        player.setIsBot(false);

        this.players.add( player );
    }

    private void init()
    {

        // En caso que sea un solo jugador, preguntar si quiere jugar con bots
        if ( this.players.size() == 1 )
        {
            System.out.println("¿Quieres jugar con bots? (Si,s / cualquier otro para cancelar)");
            String option = sc.nextLine().toUpperCase();

            if ( option.equals("SI") || option.equals("S")) {
                for (int i = 0; i < 5; i++) {
                    Player p = new Player();
                    p.setName( Name.generate() );
                    players.add( p );
                }
            }
        }

        if ( this.crupier == null )
        {
            this.crupier = new Crupier( Name.generate() );
        }
        //LLamadaFuncCantidadApuesta
        this.mazo.generate();
        this.startRound( 20 );
    }

    public void startRound(int bet)
    {
        this.checkPlayersMoney(bet);

        if (players.isEmpty())
        {
            MC.title.outline("MUCHAS GRACIAS POR JUGAR");
            this.showMenu();
            return;
        }


        Table table = Table.instance();

        for (Player p : players) {
            table.addRow( p.toString() );
        }

        table.print();
    }

    /**
     * Verifica si cada jugador tiene saldo suficiente para poder continuar
     * @param bet La cantidad apostada en el juego
     */
    private void checkPlayersMoney(int bet)
    {
        for (Player p : players) {
            if ( !p.hasEnoughMoney(bet) )
            {
                if ( !p.isBot() )
                {
                    // TODO: Funcion de pedir al banco, prestamista
                    System.out.println("PIDE AL BANCO");
                }
                this.players.remove( p );
            }
        }
    }

}
