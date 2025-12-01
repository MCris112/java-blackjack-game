package Utilities;

public class MCLine {

    static void outline(int size)
    {
        for (int i = 0; i < size; i++) {
            System.out.print("═");
        }
        System.out.println();
    }

    static void outlineCornered(int size, boolean isTop)
    {
        System.out.print( isTop ? "╔" : "╚");

        for (int i = 0; i < size; i++) {
            System.out.print("═");
        }

        System.out.print( (isTop ? "╗": "╝") +"\n");
    }

    static void outlineSides(int size, String content )
    {
        System.out.printf("║ %-"+(size-2)+"s ║\n", content);
    }

    static void slides(int size, String content)
    {
        System.out.printf(" %-"+(size-2)+"s \n", content);
    }
}
