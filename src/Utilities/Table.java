package Utilities;

import java.util.ArrayList;

public class Table {

    private final ArrayList<TableRow> rows = new ArrayList<TableRow>();

    public Table() {

    }

    public Table(String title)
    {
    }

    public static Table instance()
    {
        return new Table();
    }

    public Table addRow(String... text)
    {
        rows.add( new TableRow(text) );

        return this;
    }


    private void printLine(int size)
    {
        System.out.print("|");
        for(int i = 0; i < size; i++)
            System.out.print("-");
        System.out.println("|");
    }

    public int getMaxColumns()
    {
        int max = 0;

        for (TableRow row : rows)
        {
            if (row.count() > max)
                max = row.count();
        }

        return max;
    }
    public void print()
    {


        if(this.rows.isEmpty())
        {
            return;
        }

        int baseWidth = 8; // smaller default
        ArrayList<Integer> columnWidths = new ArrayList<>();

        // initialize widths
        for (int i = 0; i < getMaxColumns(); i++) {
            columnWidths.add(baseWidth);
        }

        // compute max width per column
        for (TableRow row : this.rows) {
            for (int i = 0; i < row.count(); i++) {
                if (row.getWidthAt(i) > columnWidths.get(i)) {
                    columnWidths.set(i, row.getWidthAt(i));
                }
            }
        }

        this.printLine(totalWidth(columnWidths));
        for (TableRow row : this.rows) {
            row.print(columnWidths, columnWidths.size()); // pass widths so each col is padded correctly
            this.printLine(totalWidth(columnWidths));
        }
    }

    private int totalWidth(ArrayList<Integer> columnWidths) {
        int sum = 0; // starting '|'
        for (int w : columnWidths) {
            sum += w + 3; // space + content + space + border
        }

        return sum -1 ; // final border
    }

}
