package Utilities;

import java.util.ArrayList;

public class TableRow {

    private String[] columns = {};

    public TableRow(String... columns) {
        this.columns = columns;
    }

    public int getWidth()
    {
        return this.columns.length*15+1;
    }

    /**
     * Get how many Columns exists
     * @return
     */
    public int count()
    {
        return this.columns.length;
    }

    /**
     * Width of each Column
     * @param pos
     * @return
     */
    public int getWidthAt(int pos)
    {
        return this.columns[pos].length();
    }

    public void print(ArrayList<Integer> widths, int expectedCols) {
        System.out.print("|");
        for (int i = 0; i < expectedCols; i++) {
            String cell = (i < columns.length) ? columns[i] : "";

            // Si es la última columna de esta fila (según lo que realmente tiene)
            if (i == columns.length - 1 && columns.length < expectedCols) {
                // calcular espacio restante
                int remaining = 0;
                for (int j = i; j < expectedCols; j++) {
                    remaining += widths.get(j) + 3; // espacio + contenido + espacio
                }
                System.out.printf(" %-" + (remaining - 3) + "s |", cell);
                break; // ya hemos llenado hasta el final
            } else {
                System.out.printf(" %-" + widths.get(i) + "s |", cell);
            }
        }
        System.out.println();
    }

}
