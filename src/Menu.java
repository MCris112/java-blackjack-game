import Utilities.Table;

import java.util.Scanner;

    public class  Menu {

    //Constructor
    public  Menu (){
    }



    public static void Menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        Table.instance()
                .addRow("Opciones:")
                .addRow("1: Solicitar Carta")
                .addRow("2: Mantener y seguir")
                .addRow("3: Retirarse con lo ganado");

    }
}
