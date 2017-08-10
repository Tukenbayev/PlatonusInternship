package kz.platonus.task3.table;


import java.util.*;

public class TableGenerator {

    private final List<Table> sortedTables = new ArrayList<>();
    List<Table> tables;

    public TableGenerator(List<Table> tables){
        this.tables = tables;
    }

    public void generateTables(){
        for (Table table : tables)
            sortInCreationOrder(table);

        for (Table table : sortedTables)
            table.generateTable();
    }

    private void sortInCreationOrder(Table table){
        if (sortedTables.contains(table)) return;

        for (TableField tableField : table.getTableFields()) {
            if (tableField.getForeignKey() != null) {
                Table referenceTable = getReferenceTable(tableField);
                sortInCreationOrder(referenceTable);
            }
        }

        sortedTables.add(table);
    }

    private Table getReferenceTable(TableField tableField){
        String tableName = tableField.getForeignKey().getReferenceTableName();
        for (Table table : tables){
            if (table.getTableName().equals(tableName))
                return table;
        }
        return null;
    }
}
