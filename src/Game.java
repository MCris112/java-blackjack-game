import Contenido.Mazo;
import Entity.Crupier;
import Entity.Player;
import Utilities.MC;
import Utilities.Name;
import Utilities.Table;
import Wallet.WalletModel;

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

            System.out.println("¿Quieres ser el Entity.Crupier? (Si, s para confirmar / enter o cualquiera para omitir)");
            String selected =  sc.nextLine().toUpperCase();
            if ( selected.equals("SI") || selected.equals("S") ) {
                System.out.println( "Ingrese el nombre del Entity.Crupier");
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


        //Mostrar menu de cuanto dinero quiere apostar
        for ( Player p : this.players )
        {
             // pregunta a cada jugador cuanto quiere apostar
            if ( p.isBot() )
            {
                //generar automaticamente la apuesta
                p.generateBetBot();
            }else{
                p.getBetting().actionBetMenu();
            }
        }

        //Llamada para ejecutar betMenu     
//        int totalBet = 0; //Cantidad por defecto
//
//        //1er jugador que no sea un bot.
//        Optional<Entity.Player> humanPlayerOpt = players.stream().filter(p -> !p.isBot()).findFirst();
//
//        if (humanPlayerOpt.isPresent()) {
//            Entity.Player humanPlayer = humanPlayerOpt.get();
//            System.out.println("\n--- Turno de apuesta para " + humanPlayer.getName() + " ---");
//
//            //Entrada_datos
//            System.out.print("Introduce tu dinero inicial: ");
//            double money = Double.parseDouble(sc.nextLine());
//            humanPlayer.getWallet().startWallet(money);
//            humanPlayer.getWallet().eyeWallet();
//
//            //Nueva apuesta en creacion
//            Betting betting = new Betting();
//            betting.setWallet(humanPlayer.getWallet());
//            betting.actionBetMenu();
//            totalBet = betting.calcTotalBet();
//        } else {
//            System.out.println("Bots en la mesa. Iniciando con apuesta por defecto.");
//            totalBet = new Random().nextInt(91) + 10;
//        }
 
        this.mazo.generate();
        this.startRound();
    }

    public void startRound()
    {
//        this.checkPlayersMoney();

        // Si no hay más jugadores la partida termina
        if (players.isEmpty())
        {
            MC.title.outline("MUCHAS GRACIAS POR JUGAR");
            this.showMenu();
            return;
        }

        // Dar carta a cada jugador
        this.mazo.giveCardToPlayers( this.players );

        //Dar carta al crupier
        this.mazo.giveCardToCrupier(
                this.crupier );

        // Primera carta es AS?
        if ( this.crupier.isFirstAs() )
        {
            //TODO preguntar si quiere asegurar?
        }

        // Dar carta a cada jugador
        this.mazo.giveCardToPlayers( this.players );

        //Quieres carta?
        for ( Player p : this.players )
        {
            System.out.printf( "¿%s quires una carta? (si/no) \n", p.getName() );
            System.out.println("Cantidad actual es: "+ p.getCartasPuntosTotales());

            String option = sc.nextLine().toUpperCase();

            if ( option.equals("SI") || option.equals("S")) {
                this.mazo.giveCardTo( p );
            }

            if ( p.getCartasPuntosTotales() > 21 )
            {
                System.out.println("LO SENTIMOS PERDISTE LA RONDA");
                this.players.remove( p );
                p.resetBetting(); //reiniciamosapuesta
            }else{

                //dar cartas crupier hasta minimo 17 puntos
                do {
                    //ver si alguien tiene jackblack


                }while ( this.crupier.getPuntosTotales() < 17 );

            }
        }
    }

    /**
     * Verifica si cada jugador tiene saldo suficiente para poder continuar
     */
//    private void checkPlayersMoney()
//    {
//        for (Player p : players) {
//            if ( !p.hasEnoughMoney() )
//            {
//                if ( !p.isBot() )
//                {
//                    // TODO: Vender riñon o conea
//                    System.out.println("PIDE AL BANCO");
//                }
//                this.players.remove( p );
//            }
//        }
//    }

}
